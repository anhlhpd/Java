/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

/**
 *
 * @author Phuong Anh
 */
public class Human {

    String name;
    int age;
    int gender;
    String phone;
    
    void listen() {
        System.out.println("Nghe nhạc.");
    }
    
    void shopping(String place) {
        System.out.println("Đi shopping tại " + place + ".");
    }
    ;
    
    public static void main(String[] args) {
        Human Object1 = new Human();
        Object1.name = "A";
        Object1.age = 20;
        Object1.gender = 0;
        Object1.phone = "0123456789";

        System.out.println("Tên là " + Object1.name + ". Tuổi là " + Object1.age + ". Giới tính là "
                + (Object1.gender == 0 ? "Nữ. " : Object1.gender == 1 ? "Nam. " : "LGBT"));
        Object1.shopping("Vincom");
    }
}
