/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Phuong Anh
 */
public class ArticleModel {
    public boolean insert(Article article) {
        try {
            // Tạo kết nối tới database MySQL với biến connnection thuộc lớp Connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/article?useUnicode=true&characterEncoding=utf-8", "root", "");
            // Tạo câu truy vấn của biến connection
            Statement stt = connection.createStatement();
            stt.execute("insert into articles (url, title, status) values ('"
                    + article.getUrl()+ "','"
                    + article.getTitle()+ "', 0)");
        } catch (SQLException ex) {
            System.err.println("Không lưu được vào database.");
            return false;
        }
        return true;
    }
}