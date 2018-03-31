/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.StudentController;
import entity.Student;
import java.util.ArrayList;
import java.util.Scanner;
import model.StudentModel;

/**
 *
 * @author Phuong Anh
 */
public class MainThread {

    public static void main(String[] args) {
        generateMenu();
//        testModel();
    }

    public static void testModel(){
        StudentModel stModel = new StudentModel();
        Student student = new Student();
        student.setName("Linh");
        student.setAddress("Aptech");
        student.setPhone("0123456789");
        student.setRollNumber("A0003");
        stModel.insert(student);
    }
    
    public static void generateMenu() {
        Scanner scanner = new Scanner(System.in);
        StudentModel stModel = new StudentModel();
        StudentController studentController = new StudentController();

        while (true) {
            System.out.println("Quản lý sinh viên");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Sửa thông tin sinh viên");
            System.out.println("4. Tìm kiếm sinh viên theo tên");
            System.out.println("5. Thoát");
            System.out.println("Vui lòng nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Student st = studentController.getStudentInformation();
                    if(st.isValid()){
                        stModel.insert(st);
                    }
                    break;

                case 2:
                    ArrayList<Student> listStudent = stModel.getList();
                    studentController.printListStudent(listStudent);
                    break;
                    
                case 3:
                    break;

                case 4:
                    break;
                
                case 5:
                    System.out.println("Thoát.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Lựa chọn sai. Vui lòng lựa chọn lại.");
                    break;
            }
        }
    }
}