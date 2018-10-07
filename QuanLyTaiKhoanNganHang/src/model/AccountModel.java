/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Phuong Anh
 */
public class AccountModel {

    public boolean insert(Account account) {
        try {
            // Tạo kết nối tới database MySQL với biến connnection thuộc lớp Connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlytaikhoannganhang?useUnicode=true&characterEncoding=utf-8", "root", "");
            // Tạo câu truy vấn của biến connection
            Statement stt = connection.createStatement();
            stt.execute("insert into accounts (username, password, createdAt, status) values ('"
                    + account.getUsername() + "','"
                    + account.getPassword() + "', 0, 1)");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Account searchByUsername(String username) {
        Account account = null;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlytaikhoannganhang?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM accounts WHERE username = '" + username + "'");
            while (rs.next()) {
                int id = rs.getInt("id");
                int balance = rs.getInt("balance");
                String accountUsername = rs.getString("username");
                String password = rs.getString("password");
                long createdAt = rs.getLong("createdAt");
                account = new Account(id, balance, accountUsername, password, createdAt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return account;
    }

    public boolean update(Account account) {
        Account account1 = new Account();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlytaikhoannganhang?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            stt.execute("UPDATE accounts SET balance =" + account.getBalance() + " WHERE username = '" + account.getUsername() + "'");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void delete(){}
}