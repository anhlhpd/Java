/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Student;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Phuong Anh
 */
public class StudentController {
    // Tương tự một biến to gồm nhiều biến con
    
    private ArrayList<Student> listStudent = new ArrayList<>();
//     private ArrayList<Student> listStudent;
    
    public Student getStudentInformation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên sinh viên: ");
        String name = sc.nextLine();
        System.out.println("Nhập số điện thoại: ");
        String phone = sc.nextLine();
        System.out.println("Nhập địa chỉ: ");
        String address = sc.nextLine();
        System.out.println("Nhập mã sinh viên: ");
        String rollNumber = sc.nextLine();
        
        return new Student(rollNumber, name, address, phone);
    }
    
    public ArrayList<Student> getListStudent(){
        return listStudent;
    }
    
    public void printListStudent(ArrayList<Student> listStudent){
        System.out.printf("%18s %18s %18s %18s \n", "Mã sinh viên", "Tên sinh viên", "Số điện thoại", "Địa chỉ");
        for (int i = 0; i < listStudent.size(); i++) {
            Student student = listStudent.get(i);
            System.out.printf("%18s %18s %18s %18s \n", student.getRollNumber(), student.getName(),
                    student.getPhone(), student.getAddress());
        }
    }
    
    public void addStudent(Student student){
        // if(listStudent == null){ArrayList<Student> listStudent = new ArrayList<>()};
        listStudent.add(student);
    }
}