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
public class Student {

    private int id;
    private String name;
    private String phone;
    private String address;
    private String rollNumber;

    public Student() {}

    public Student(String rollNumber, String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.rollNumber = rollNumber;
    }
    
    

    public Student(int id, String rollNumber, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.rollNumber = rollNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    // Hàm dùng để kiểm tra tính hợp lệ thông tin của sinh viên
    public boolean isValid() {
//        // Kiểm tra tên có độ dài lớn hơn 7
//        if (name == null || name.length() <= 7) {
//            return false;
//        }
//        // Kiểm tra độ dài số điện thoại.
//        if (phone == null || phone.length() != 11) {
//            return false;
//        }
//        // Kiểm tra địa chỉ có độ dài khác 0
//        if (address == null || address.length() == 0) {
//            return false;
//        }
//        // Kiểm tra mã sinh viên có độ dài là 8
//        if (rollNumber == null || rollNumber.length() != 8) {
//            return false;
//        }

        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", rollNumber=" + rollNumber + '}';
    }

    
    public void printInformation() {
        System.out.println("Tên sinh viên là: " + name);
        System.out.println("Mã số sinh viên: " + rollNumber);
        System.out.println("Số điện thoại: " + phone);
        System.out.println("Địa chỉ: " + address);
    }

}