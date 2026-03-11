/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DAO.Services;

import Models.DTO.User;
import java.util.List;

/**
 *
 * @author datto
 */
public interface IService<T> {
    User checkAccount(String userName, String password) throws Exception ;
    User getUserByUserName(String userName) throws Exception ;
    List<User> getUsersByKeywordOfLastName(String value) throws Exception ;
    boolean addNewUser(User user) throws Exception ;
    boolean removeUser(String userName) throws Exception ;
    boolean updateUser(User user) throws Exception;
}