/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import java.util.Scanner;
import model.AccountModel;

/**
 *
 * @author Phuong Anh
 */
public class AccountController {

    AccountModel accountModel = new AccountModel();

    public Account getInfo() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Vui lòng nhập username");
        String username = scn.nextLine();
        System.out.println("Vui lòng nhập password");
        String password = scn.nextLine();
        return new Account(username, password);
    }

    public boolean logIn(Account acc) {
        Account acc2 = accountModel.searchByUsername(acc.getUsername());

        if (acc2 == null) {
            System.err.println("Tài khoản không tồn tại");
            return false;
        } else {
            if (acc.getPassword().equals (acc2.getPassword())) {
                System.out.println("Bạn đã đăng nhập thành công");
                return true;

            } else {
                System.out.println("Bạn đã nhập sai mật khẩu. Vui lòng nhập lại");
                return false;
            }
        }
    }

    public boolean register(Account acc) {      
        Account acc1 = accountModel.searchByUsername(acc.getUsername());
        if (null == acc1) {
            System.out.println("Bạn đã đăng kí thành công");
            return true;
        } else {
            System.out.println("Tên tài khoàn đã tồn tại. Vui lòng chọn tên khác");
            return false;
        }
    }
}