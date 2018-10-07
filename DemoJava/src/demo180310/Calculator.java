/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo180310;

import java.util.Scanner;

/**
 *
 * @author Phuong Anh
 */
public class Calculator {
    void add (int a, int b){
        System.out.println("Tổng 2 số là " + (a + b));
    };
    
    void sub (int a, int b){
        System.out.println("Hiệu 2 số là " + (a - b));
    };
    
    void mul (int a, int b){
        System.out.println("Tích 2 số là " + (a * b));
    };
    
    void div (int a, int b){
        if (b == 0){
            System.out.println("Phép chia vô nghĩa.");
        } else {
            System.out.println("Thương 2 số là " + (a / b));
        }
    };
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Hãy nhập số a: ");
        int a = scanner.nextInt();
        System.out.println("Hãy nhập số b: ");
        int b = scanner.nextInt();
        Calculator calculator1 = new Calculator();
        calculator1.add(a, b);
        calculator1.sub(a, b);
        calculator1.mul(a, b);
        calculator1.div(a, b);
    }
}
