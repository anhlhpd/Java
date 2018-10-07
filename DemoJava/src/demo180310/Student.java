/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo180310;

import demo180329Action.PositiveActivities;

/**
 *
 * @author Phuong Anh
 */
public class Student extends Human implements PositiveActivities{
    String name;
    int age;
    int gender;
    String phone;
    String rollNumber;

    Student() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void listen() {
        System.out.println("Nghe nhạc.");
    }
    
    void shopping(String place) {
        System.out.println("Đi shopping tại " + place + ".");
    }
    
    @Override
    public void readBooks() {
        System.out.println("Đọc sách.");
    }

    @Override
    public void playSports() {
        System.out.println("Chơi thể thao điện tử.");
    }
    
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "A";
        s1.age = 20;
        s1.gender = 0;
        s1.phone = "0123456789";

        System.out.println("Tên là " + s1.name + ". Tuổi là " + s1.age + ". Giới tính là "
                + (s1.gender == 0 ? "Nữ. " : s1.gender == 1 ? "Nam. " : "LGBT"));
        s1.shopping("Vincom");
    }
}
