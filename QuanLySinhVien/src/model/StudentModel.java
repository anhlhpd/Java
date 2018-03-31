/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.StudentController;
import entity.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Phuong Anh
 */
public class StudentModel {
    // Làm việc với database

    // Thêm thông tin
    public boolean insert(Student student) {
        try {
            // Tạo kết nối tới database MySQL với biến connnection thuộc lớp Connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            // Tạo câu truy vấn của biến connection
            Statement stt = connection.createStatement();
            stt.execute("insert into students (name, address, phone, rollNumber) values ('"
                    + student.getName() + "'," + "'"
                    + student.getAddress() + "','"
                    + student.getPhone() + "', '"
                    + student.getRollNumber() + "')");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    // Lấy danh sách sinh viên từ trong database
    public ArrayList<Student> getList() {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Student studentGetList = new Student(id, rollNumber, name, phone, address);
                listStudent.add(studentGetList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listStudent;
    }

    // Lấy thông tin sinh viên có id truyền vào, trả về đối tượng Student.
    // Nếu trong db không tồn tại bản ghi thì trả về null.
    // Test: File db mẫu nằm trong link. Khi truyền tham số id là 1 thì trâ về ...
    public Student getById(int id) {
        Student student = null;

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM students WHERE id = " + id);
            if (rs.next()) {
                int studentId = id;
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                student = new Student(studentId, rollNumber, name, address, phone);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return student;
    }

    // Lấy thông tin sinh viên có name truyền vào
    public ArrayList<Student> searchByName(String name) {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM students WHERE name like '%" + name + "%'");
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String rollNumber = rs.getString("rollNumber");
                String studentName = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Student student = new Student(studentId, rollNumber, name, address, phone);
                listStudent.add(student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listStudent;
    }

    // Kiểm tra sự tồn tại của sinh viên theo rollNumber truyền vào và kiểm tra sự tồn tại của rollNumber
    // Trả về true nếu tồn tại, false nếu không tồn tại
    public boolean checkExistRollNumber(String rollNumber) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM students WHERE rollNumber = " + rollNumber);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void update(String rollNumber) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();

            ResultSet rs = stt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Student studentUpdate = new Student(id, rollNumber, name, phone, address);
                listStudent.add(studentUpdate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        StudentController st = new StudentController();
        StudentModel model = new StudentModel();
        
        // Kiểm tra hàm getById()
        Student student1 = model.getById(2);
        if(null == student1){
            System.out.println("Không tồn tại sinh viên.");
        } else {
            System.out.println(student1.toString());
        }
        
        // Kiểm tra hàm searchByName()
        ArrayList<Student> list = model.searchByName("Hùng");
        if (list == null || list.size() == 0) {
            System.out.println("Không tồn tại sinh viên");
            return;
        } else {
            System.out.printf("%18s %18s %18s %18s \n", "Mã sinh viên", "Tên sinh viên", "Số điện thoại", "Địa chỉ");
            for (int i = 0; i < list.size(); i++) {
                Student student2 = list.get(i);
                System.out.printf("%18s %18s %18s %18s \n", student2.getRollNumber(), student2.getName(),
                        student2.getPhone(), student2.getAddress());
            }
        }
    }

    // 24/3/2018 : Tại sao null == student?
    // Tùng tìm hiểu searchbyname - fulltextsearch
}
