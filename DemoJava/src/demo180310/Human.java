/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo180310;

import demo180315.Staff;
import demo180329Action.PositiveActivities;

/**
 *
 * @author Phuong Anh
 */
public class Human implements PositiveActivities{ 
    
    private String identityCard;
    private String name;
    private int age;
    private int gender;
    private String phone;

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }   
    
    public void setName (String name){
        this.name = name;
    }
    public void setAge (int age){
        this.age = age;
    }
    public void setGender (int gender){
        this.gender = gender;
    }
    public void setPhone (String phone){
        this.phone = phone;
    }
    
    
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public int getGender(){
        return this.gender;
    }
    public String getPhone(){
        return this.phone;
    }
    
//    @Override
//    public String toString(){
//        return "Tôi tên là " + this.name + ", tuổi: " + this.age +
//                ", giới tính là " + (this.gender == 1 ? "Nam" : this.gender == 0 ? "Nữ" : "khác")
//                + ", số điện thoại là " + this.phone ;
//    }
    
    // Ví dụ về constructor
    public Human (){}
    public Human (String name) {this.name = name;}
    public Human (String name, int age) {this.name = name; this.age = age;}
    public Human (String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public Human (String name, int age, int gender, String phone) {
        this.name =  name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }
    
    public static void main(String[] args) {
        // constructor default khởi tạo đối tượng cụ thể của một class nào đấy
        // constructor giống hàm, không có dữ liệu trả về, tên hàm trùng với tên class
        // Nếu khai báo mà chỉ khác tham số truyền vào thì constructor được sử dụng theo số cách khai báo
        Human h1 = new Human("Hùng", 30, 1, "0123");
//        h1.setName("Hùng");
//        h1.setAge(30);
//        h1.setGender(1);
//        h1.setPhone("0123");
//        System.out.println(h1.getName());
        System.out.println(h1.toString());
        
        Human h2 = new Student();
        Human h3 = new Staff();
        
        h2.playSports();
        h3.playSports();
        
        Student s1 = new Student();
        Staff s2 = new Staff();
        
        personPlaySports(s1);
        personPlaySports(s2);
    }
    
    public static void personPlaySports(Human h){
        h.playSports();
    }

    @Override
    public void readBooks() {
        System.out.println("Đọc sách.");
    }

    @Override
    public void playSports() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}