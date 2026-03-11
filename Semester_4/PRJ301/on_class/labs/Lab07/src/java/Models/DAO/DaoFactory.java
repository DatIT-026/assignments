/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DAO;

/**
 *
 * @author datto
 */
public class DaoFactory implements IDaoFactory {
    public DaoFactory() {
    }
    @Override
    public IUserDao userDao() {
        return new UserDao();
    }
}
