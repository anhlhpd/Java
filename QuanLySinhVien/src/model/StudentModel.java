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
import java.sql.PreparedStatement;
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
    public boolean insert(Student student) throws SQLException {
        try {
            // Tạo kết nối tới database MySQL với biến connnection thuộc lớp Connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            
            // Cách đẩy tham số vào câu lệnh
            String sql = "insert into students (name, address, phone, rollNumber) values (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getAddress());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getRollNumber());
            
            ps.execute();
            
            // Tạo câu truy vấn của biến connection
//            Statement stt = connection.createStatement();
//            stt.execute("insert into students (name, address, phone, rollNumber) values ('"
//                    + student.getName() + "'," + "'"
//                    + student.getAddress() + "','"
//                    + student.getPhone() + "', '"
//                    + student.getRollNumber() + "')");
        } catch (SQLException ex) {
            System.out.println("Không thêm được sinh viên mới vào. Vui lòng thử lại.");
            return false;
        }
        return false;

//        if(student.getName().equals("xuanhung")){
//            throw new SQLException("Lỗi");
//        }
//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
//        // Tạo câu truy vấn của biến connection
//        Statement stt = connection.createStatement();
//        stt.execute("insert into students (name, address, phone, rollNumber) values ('"
//                + student.getName() + "'," + "'"
//                + student.getAddress() + "','"
//                + student.getPhone() + "', '"
//                + student.getRollNumber() + "')");
//        return true;
        
    }

    // Lấy danh sách sinh viên từ trong database
    public ArrayList<Student> getList() {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&characterEncoding=utf-8", "root", "");
            String sql = "SELECT * FROM students";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
//            Statement stt = connection.createStatement();
//            ResultSet rs = stt.executeQuery("SELECT * FROM students");
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
            System.out.println("Không lấy được danh sách sinh viên. Vui lòng thử lại.");
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
            
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM students WHERE id = ");
            stringBuilder.append(id);
            
            PreparedStatement ps = connection.prepareStatement(stringBuilder.toString());
            ResultSet rs = ps.executeQuery();
            
//            Statement stt = connection.createStatement();
//            ResultSet rs = stt.executeQuery("SELECT * FROM students WHERE id = " + id);
            if (rs.next()) {
                int studentId = id;
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                student = new Student(studentId, rollNumber, name, address, phone);
            }
        } catch (SQLException ex) {
            System.out.println("Không tìm được sinh viên có Id như trên. Vui lòng thử lại.");
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
                String studentRollNumber = rollNumber;
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Student studentUpdate = new Student(id, studentRollNumber, name, phone, address);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        StudentController st = new StudentController();
        StudentModel model = new StudentModel();
        
        System.out.println(model.getList());
        
//        // Kiểm tra hàm getById()
//        Student student1 = model.getById(2);
//        if (null == student1) {
//            System.out.println("Không tồn tại sinh viên.");
//        } else {
//            System.out.println(student1.toString());
//        }
//
//        // Kiểm tra hàm searchByName()
//        ArrayList<Student> list = model.searchByName("Hùng");
//        if (list == null || list.size() == 0) {
//            System.out.println("Không tồn tại sinh viên");
//            return;
//        } else {
//            System.out.printf("%18s %18s %18s %18s \n", "Mã sinh viên", "Tên sinh viên", "Số điện thoại", "Địa chỉ");
//            for (int i = 0; i < list.size(); i++) {
//                Student student2 = list.get(i);
//                System.out.printf("%18s %18s %18s %18s \n", student2.getRollNumber(), student2.getName(),
//                        student2.getPhone(), student2.getAddress());
//            }
//        }
//        
//        // Try catch
//        try {
//            model.insert(new Student("A005", "Xuân Hùng", "12345", "Cầu Giấy"));
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        demoStringJava();
        
    }
    
    // Sử dụng để thay thế cho việc nối chuỗi làm tăng tính performance cho app
    public static void demoStringJava(){
        long startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tôi là ");
        stringBuilder.append("Xuân Hùng.");
        System.out.println(stringBuilder.toString());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
    // 24/3/2018 : Tại sao null == student?
    // Tùng tìm hiểu searchbyname - fulltextsearch

}