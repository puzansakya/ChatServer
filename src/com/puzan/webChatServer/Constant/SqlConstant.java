/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.webChatServer.Constant;

/**
 *
 * @author puzan
 */
public class SqlConstant {

    public static String INSERT_USER = "INSERT into tbl_user(username,password,status) values(?,?,?)";
    public static String LOGIN = "SELECT * FROM tbl_user WHERE username = ? AND password = ?";
    public static String GetByUsername = "SELECT * FROM tbl_user WHERE username = ?";
    public static String INSERT_MESSAGE = "INSERT into tbl_message(username,message) values(?,?)";

}
