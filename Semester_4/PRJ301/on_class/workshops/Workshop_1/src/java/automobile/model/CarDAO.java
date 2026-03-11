/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automobile.model;

import automobile.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author datto
 */
public class CarDAO implements Serializable {

    //SOLID
    List<CarDTO> cars;

    public List<CarDTO> getCar() {
        return cars;
    }

    public void listCar()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT CarID, CarName, Manufacturer, Price, ReleasedYear "
                        + "FROM Cars";
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    int CarID = rs.getInt("CarID");
                    String CarName = rs.getString("CarName");
                    String Manufacturer = rs.getString("Manufacturer");
                    float Price = rs.getFloat("Price");
                    int ReleasedYear = rs.getInt("ReleasedYear");
                    CarDTO dto = new CarDTO(CarID, CarName, Manufacturer, Price, ReleasedYear);
                    if (this.cars == null) {
                        cars = new ArrayList<>();
                    }
                    this.cars.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public CarDTO getCarByID(int CarID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT CarID, CarName, Manufacturer, Price, ReleasedYear "
                        + "FROM Cars "
                        + "WHERE CarID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, CarID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return new CarDTO(
                            rs.getInt("CarID"),
                            rs.getString("CarName"),
                            rs.getString("Manufacturer"),
                            rs.getFloat("Price"),
                            rs.getInt("ReleasedYear")
                    );
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createCar(CarDTO car)
            throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "INSERT INTO Cars("
                        + "CarID, CarName, Manufacturer, Price, ReleasedYear) "
                        + "VALUES(?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, car.getCarID());
                stm.setString(2, car.getCarName());
                stm.setString(3, car.getManufacturer());
                stm.setFloat(4, car.getPrice());
                stm.setInt(5, car.getReleasedYear());

                int effectedRows = stm.executeUpdate();

                if (effectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean deleteCar(int CarID)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "DELETE FROM Cars "
                        + "WHERE CarID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, CarID);

                int effectedRows = pstm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;

    }

    public boolean updateCar(CarDTO car)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Cars "
                        + "SET CarName = ?, "
                        + "Manufacturer = ?, "
                        + "Price = ?, "
                        + "ReleasedYear = ? "
                        + "WHERE CarID = ?";
                stm = con.prepareStatement(sql);
                
                stm.setString(1, car.getCarName());
                stm.setString(2, car.getManufacturer());
                stm.setFloat(3, car.getPrice());
                stm.setInt(4, car.getReleasedYear());
                stm.setInt(5, car.getCarID());
                
                int effectedRows = stm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;

    }

}
