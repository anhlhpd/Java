/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Article;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import model.ArticleModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Phuong Anh
 */
public class ArticleController {
    
    

    HashSet<Article> listArticles = new HashSet<>();
    ArticleModel model = new ArticleModel();

    public void getArticles() {
        try {
            Document doc = Jsoup.connect("http://cafebiz.vn/chuyen-cua-3-con-ca-doc-va-ngam-nhieu-nguoi-trong-chung-ta-se-thay-ngam-ngui-20180412162344398.chn").get();
            Elements elements = doc.select("a[href]");
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                if (!element.attr("rel").equals("nofollow")) {
                    Article article = new Article(element.absUrl("href"), element.text());
                    System.out.println(article.getTitle());
                    listArticles.add(article);
                    model.insert(article);
                }
            }
        } catch (IOException ex) {
            System.err.println("Không lấy được tin.");
        }
    }

    public static void getArticleDetail(HashSet<Article> listArticles) {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/articles?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE status = 0");
            while (rs.next()) {
                String url = rs.getString("url");
                String title = rs.getString("title");
                for (Article article : listArticles) {
                    try {
                        Document doc = Jsoup.connect(article.getUrl()).get();
                        Elements element = doc.select(".detail-content p");
                        article.setContent(element.text());
                        System.out.println(article.getTitle());
                        System.out.println(article.getContent());
                    } catch (IOException ex) {
                        System.out.println("Không lấy được thông tin bài báo.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}