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
public class Account {

    private int id;
    private int balance;
    private String username;
    private String password;
    private long createdAt;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account(int id, int balance, String username, String password, long createdAt) {
        this.id = id;
        this.balance = balance;
        this.username = username;
        this.password = password;
        this.createdAt = System.currentTimeMillis();
    }

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.createdAt = System.currentTimeMillis();
    }

}
