/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Phuong Anh
 */
public class Article {
    private String url;
    private String title;
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(String url, String title) {
        this.url = url;
        this.title = title;
    }
    
    public Article(){}
    
    @Override
    public boolean equals(Object obj) {
        Article article = (Article) obj;
        return (this.url == article.getUrl() && this.title.equals(article.getUrl()))
    }
}