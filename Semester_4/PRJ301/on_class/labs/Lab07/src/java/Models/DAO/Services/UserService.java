/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DAO.Services;

import Models.DAO.DaoFactory;
import Models.DAO.IUserDao;
import Models.DTO.User;
import java.util.List;
/**
 *
 * @author datto
 */
public class UserService implements IService<User>{
    IUserDao userDao;
    
    public UserService() {
        this.userDao = new DaoFactory().userDao();
    }
    
    @Override
    public User checkAccount(String userName, String password) throws Exception {
        return userDao.login(userName, password);
    }
    
    @Override
    public User getUserByUserName(String userName) throws Exception {
        return userDao.getUserByUserName(userName);
    }
    
    @Override
    public List<User> getUsersByKeywordOfLastName(String value) throws Exception {
        return userDao.searchUserByLastName(value);
    }
    
    @Override
    public boolean addNewUser(User user) throws Exception {
        return userDao.addUser(user);
    }
    
    @Override
    public boolean removeUser(String userName) throws Exception {
        return userDao.deleteUser(userName);
    }
    
    @Override
    public boolean updateUser(User user) throws Exception {
        return userDao.updateUser(user);
    }
}