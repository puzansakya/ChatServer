/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.webChatServer.DAO;

import com.puzan.webChatServer.entities.Message;
import com.puzan.webChatServer.entities.User;
import java.sql.SQLException;

/**
 *
 * @author puzan
 */
public interface userDAO {

    int insert(User u) throws SQLException, ClassNotFoundException;

    User login(String username, String password) throws ClassNotFoundException, SQLException;

    User getByUsername(String username) throws ClassNotFoundException, SQLException;

    void insertMessage(Message msg) throws SQLException, ClassNotFoundException;

}
