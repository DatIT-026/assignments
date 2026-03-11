/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thu.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import thu.model.ProductCart.Cart;
import thu.utils.DBUtil;

/**
 *
 * @author C00kies
 */
public class OrderDAO implements Serializable {

    public boolean checkout(int userID, Cart cart) throws SQLException {
        Connection con = null;
        PreparedStatement pstmOrder = null;
        PreparedStatement pstmDetail = null;
        PreparedStatement pstmUpdateStock = null;
        PreparedStatement pstmUpdateStatus = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                // Tắt Auto Commit để bắt đầu Transaction
                con.setAutoCommit(false);

                // 1. Chèn vào bảng Orders
                String sqlOrder = "insert into Orders(userID, date, total, status) "
                        + "values(?, getdate(), ?, ?)";
                pstmOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                pstmOrder.setInt(1, userID);
                pstmOrder.setFloat(2, calculateTotal(cart));
                pstmOrder.setBoolean(3, true);

                int affectedRows = pstmOrder.executeUpdate();
                if (affectedRows > 0) {
                    rs = pstmOrder.getGeneratedKeys();
                    if (rs.next()) {
                        int orderID = rs.getInt(1);

                        String sqlDetail = "Insert into "
                                + "OrderDetails(orderID, productID, quantity, price) "
                                + "values(?, ?, ?, ?)";
                        String sqlUpdate = "Update Products "
                                + "SET stockQuantity = stockQuantity - ? "
                                + "WHERE productID = ?";
                        String sqlUpdateStatus = "Update Products "
                                + "SET status = 0 "
                                + "WHERE productID = ? "
                                + "AND stockQuantity = 0";
                        pstmDetail = con.prepareStatement(sqlDetail);
                        pstmUpdateStock = con.prepareStatement(sqlUpdate);
                        pstmUpdateStatus = con.prepareStatement(sqlUpdateStatus);

                        ProductDAO pDao = new ProductDAO();

                        // Duyệt qua giỏ hàng (Iterate through the cart)
                        for (ProductDTO dto : cart.getProducts().values()) {
                            // Kiểm tra kho lần cuối trước khi trừ
                            int currentStock = pDao.getStockQuantity(dto.getProductID());

                            if (currentStock < dto.getStockQuantity()) {
                                throw new Exception("Insufficient stock for: " + dto.getProductName());
                            }

                            // Thêm vào OrderDetails
                            pstmDetail.setInt(1, orderID);
                            pstmDetail.setInt(2, dto.getProductID());
                            pstmDetail.setInt(3, dto.getStockQuantity());
                            pstmDetail.setFloat(4, dto.getPrice());
                            pstmDetail.executeUpdate();

                            // Trừ Stock trong bảng Products
                            pstmUpdateStock.setInt(1, dto.getStockQuantity());
                            pstmUpdateStock.setInt(2, dto.getProductID());
                            pstmUpdateStock.executeUpdate();

                            // Update status if out of stocks in DB
                            pstmUpdateStatus.setInt(1, dto.getProductID());
                            pstmUpdateStatus.executeUpdate();
                        }

                        con.commit();
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            result = false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmUpdateStatus != null) {
                pstmUpdateStatus.close();
            }
            if (pstmUpdateStock != null) {
                pstmUpdateStock.close();
            }
            if (pstmDetail != null) {
                pstmDetail.close();
            }
            if (pstmOrder != null) {
                pstmOrder.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private float calculateTotal(Cart cart) {
        float total = 0;
        for (ProductDTO dto : cart.getProducts().values()) {
            total += dto.getStockQuantity() * dto.getPrice();
        }
        return total;
    }

}
