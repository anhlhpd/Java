/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AccountController;
import entity.Account;
import java.util.Scanner;
import model.AccountModel;

/**
 *
 * @author Phuong Anh
 */
public class MainThread {

    public static void main(String[] args) {
        generateMenu1();
    }

    public static void generateMenu1() {
        AccountModel accountModel = new AccountModel();
        AccountController accCtrl = new AccountController();

        while (true) {
            System.out.println("Ngân hàng ABC");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng kí");
            System.out.println("Hãy chọn chức năng:");
            Scanner scn = new Scanner(System.in);
            int choice = scn.nextInt();
            switch (choice) {
                case 1:
                    Account acc = accCtrl.getInfo();
                    if (accCtrl.logIn(acc)) {
                        generateMenu2(acc);
                    }
                    break;
                case 2:
                    Account acc2 = accCtrl.getInfo();
                    if (accCtrl.register(acc2)) {
                        accountModel.insert(acc2);
                    }
                    ;
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng lựa chọn lại.");
                    break;
            }
        }
    }

    public static void generateMenu2(Account acc) {
        AccountModel accountModel = new AccountModel();
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("1.Kiểm tra số dư");
            System.out.println("2.Rút tiền");
            System.out.println("3.Gửi tiên");
            System.out.println("Vui lòng nhập lựa chọn của bạn");

            int choice = scn.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Số dư tài khoản của bạn là " + accountModel.searchByUsername(acc.getUsername()).getBalance());
                    break;
                    
                case 2:
                    System.out.println("Số tiền bạn muốn rút là ");
                    int money1 = scn.nextInt();
                    if (money1 > accountModel.searchByUsername(acc.getUsername()).getBalance()) {
                        System.out.println("Số tiền không đủ để rút.");
                    } else {
                        acc.setBalance(accountModel.searchByUsername(acc.getUsername()).getBalance() - money1);
                        accountModel.update(acc);
                        System.out.println("Bạn đã rút tiền thành công. Số dư của bạn là " + accountModel.searchByUsername(acc.getUsername()).getBalance());
                    }
                    break;
                    
                case 3:
                    System.out.println("Số tiền bạn muốn gửi là ");
                    int money2 = scn.nextInt();
                    if (money2 <= 0) {
                        System.out.println("Số tiền nhập sai");
                    } else {
                        acc.setBalance(accountModel.searchByUsername(acc.getUsername()).getBalance() + money2);
                        accountModel.update(acc);
                        System.out.println("Bạn đã gửi tiền thành công. Số dư của bạn là " + accountModel.searchByUsername(acc.getUsername()).getBalance());
                    }
                    break;
                    
                default:
                    System.out.println("Lựa chọn sai. Vui lòng lựa chọn lại.");
                    break;
            }
        }
    }
}
