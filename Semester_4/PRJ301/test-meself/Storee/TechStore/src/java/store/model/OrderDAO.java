/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package store.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import store.utils.DBUtils;

/**
 *
 * @author datto
 */
public class OrderDAO implements Serializable {

    public boolean checkout(int userID, Cart cart) throws Exception {
        Connection con = null;
        PreparedStatement pstmOrder = null;
        PreparedStatement pstmDetail = null;
        PreparedStatement pstmUpdateStock = null;
        PreparedStatement pstmUpdateStatus = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);

                String sqlOrder = "INSERT INTO Orders(UserID, OrderDate, Total, Status) "
                        + "VALUES(?, GETDATE(), ?, 1)";
                pstmOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                pstmOrder.setInt(1, userID);
                pstmOrder.setDouble(2, cart.getTotal());
                
                int rows = pstmOrder.executeUpdate();
                if (rows > 0) {
                    rs = pstmOrder.getGeneratedKeys();
                    if (rs.next()) {
                        int orderID = rs.getInt(1);
                        String sqlDetail = "INSERT INTO OrderDetails(OrderID, ProductID, Quantity, Price) "
                                + "VALUES(?, ?, ?, ?)";
                        String sqlUpdateStock = "UPDATE Products "
                                + "SET StockQuantity = StockQuantity - ? "
                                + "WHERE ProductID = ?";
                        String sqlUpdateStatus = "UPDATE Products "
                                + "SET Status = 0 "
                                + "WHERE ProductID = ? "
                                + "AND StockQuantity <= 0";

                        pstmDetail = con.prepareStatement(sqlDetail);
                        pstmUpdateStock = con.prepareStatement(sqlUpdateStock);
                        pstmUpdateStatus = con.prepareStatement(sqlUpdateStatus);

                        ProductDAO pDao = new ProductDAO();
                        for (CartItem item : cart.getItems().values()) {
                            int pid = item.getProduct().getProductID();
                            int buyQty = item.getQuantity();
                            double price = item.getProduct().getPrice();
                            int currentStock = pDao.getStockQuantity(pid);
                            if (currentStock < buyQty) {
                                throw new Exception("Insufficient stock for: " + item.getProduct().getProductName());
                            }
                            pstmDetail.setInt(1, orderID);
                            pstmDetail.setInt(2, pid);
                            pstmDetail.setInt(3, buyQty);
                            pstmDetail.setDouble(4, price);
                            pstmDetail.executeUpdate();
                            
                            pstmUpdateStock.setInt(1, buyQty);
                            pstmUpdateStock.setInt(2, pid);
                            pstmUpdateStock.executeUpdate();
                            
                            pstmUpdateStatus.setInt(1, pid);
                            pstmUpdateStatus.executeUpdate();
                        }
                        con.commit();
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            if (con != null) con.rollback();
            e.printStackTrace();
            result = false;
        } finally {
            if (rs != null) rs.close();
            if (pstmOrder != null) pstmOrder.close();
            if (pstmDetail != null) pstmDetail.close();
            if (pstmUpdateStock != null) pstmUpdateStock.close();
            if (pstmUpdateStatus != null) pstmUpdateStatus.close();
            if (con != null) con.close();
        }
        return result;
    }
}