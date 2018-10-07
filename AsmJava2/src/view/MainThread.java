/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ArticleController;

/**
 *
 * @author Phuong Anh
 */
public class MainThread {
    public static void main(String[] args) throws InterruptedException {
        ArticleController controller = new ArticleController();
        controller.getArticles();
    }
}