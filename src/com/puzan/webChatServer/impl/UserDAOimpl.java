/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.webChatServer.impl;

import com.puzan.webChatServer.Constant.SqlConstant;
import com.puzan.webChatServer.DAO.userDAO;
import com.puzan.webChatServer.DBUtil.DBConnection;
import com.puzan.webChatServer.entities.Message;
import com.puzan.webChatServer.entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author puzan
 */
public class UserDAOimpl implements userDAO {

    private DBConnection db = new DBConnection();

    @Override
    public int insert(User u) throws SQLException, ClassNotFoundException {
        db.connect();
        PreparedStatement stmt = db.initStatement(SqlConstant.INSERT_USER);
        stmt.setString(1, u.getUsername());
        stmt.setString(2, u.getPassword());
        stmt.setBoolean(3, u.isStatus());
        int res = db.update();
        db.close();
        return res;
    }

    @Override
    public User login(String username, String password) throws ClassNotFoundException, SQLException {
        User user = null;
        db.connect();
        PreparedStatement stmt = db.initStatement(SqlConstant.LOGIN);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = db.query();
        if (rs.next()) {
            user = mapData(rs);
        }
        db.close();
        return user;
    }

    @Override
    public User getByUsername(String username) throws ClassNotFoundException, SQLException {
        User user = null;
        db.connect();
        PreparedStatement stmt = db.initStatement(SqlConstant.GetByUsername);
        stmt.setString(1, username);
        ResultSet rs = db.query();
        if (rs.next()) {
            user = mapData(rs);
        }
        db.close();
        return user;
    }

    private User mapData(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setAdded_date(rs.getDate("added_date"));
        user.setStatus(rs.getBoolean("status"));
        return user;
    }

    @Override
    public void insertMessage(Message msg) throws SQLException, ClassNotFoundException {
        db.connect();
        PreparedStatement stmt = db.initStatement(SqlConstant.INSERT_MESSAGE );
        stmt.setString(1, msg.getUsername());
        stmt.setString(2, msg.getMessage());
        db.update();
        db.close();

    }

}
