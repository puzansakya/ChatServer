/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.webChatServer.DBUtil;

import com.puzan.webChatServer.Constant.DBConstant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author puzan
 */
public class DBConnection {

    
    //facade pattern...
    
    
    private Connection conn = null;
    private PreparedStatement stmt = null;

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName(DBConstant.DB_DRIVER);
        conn = DriverManager.getConnection(DBConstant.DB_URL, DBConstant.DB_USER, DBConstant.DB_PASSWORD);
    }

    public PreparedStatement initStatement(String sql) throws SQLException {
        return stmt = conn.prepareStatement(sql);
    }

    public int update() throws SQLException {
        return stmt.executeUpdate();
    }

    public ResultSet query() throws SQLException {
        return stmt.executeQuery();
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            conn = null;
            stmt = null; 
        }
    }

}
