/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class OrdersDao implements Serializable {
    //-----            your code here   --------------------------------
    List<OrdersDto> order;

    public List<OrdersDto> getOrder() {
        return order;
    }

    public void searchOrder(int status)
            throws SQLException, ClassNotFoundException {

        if (this.order == null) order = new ArrayList<>();
        else this.order.clear();

        String sql = "SELECT orderID, orderDate, customer, address, totalAmount, status "
                + "FROM tblOrders "
                + "WHERE status = ?";
        

        try (Connection con = DbUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setInt(1, status);

                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        String orderID = rs.getString("orderID");
                        Date orderDate = rs.getDate("orderDate");
                        String customer = rs.getString("customer");
                        String address = rs.getString("address");
                        int totalAmount = rs.getInt("totalAmount");

                        OrdersDto dto = new OrdersDto(orderID, orderDate, customer, address, totalAmount, status);

                        this.order.add(dto);
                    }
                }
            }
        }
    }
}