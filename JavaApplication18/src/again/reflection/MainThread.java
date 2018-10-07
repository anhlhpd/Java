/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package again.reflection;

import code.xin.hon.mot.ti.Model;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class MainThread {

    public static void main(String[] args) throws SQLException {
//        Model<Student> model = new Model<>(Student.class);
////        model.save(new Student("Phương anh", "A213"));
//        ArrayList<Student> listStudent = model.getList();
//        for (Student student : listStudent) {
//            System.out.println(student.toString());
//        }
        Model<Customer> model = new Model<>(Customer.class);
//        model.save(new Customer("Hùng Đào"));
//        model.save(new Customer("Đào Hùng"));
//        model.save(new Customer("Hùng Hùng Đào"));
//        model.save(new Customer("Đào Đào Hùng"));
        ArrayList<Customer> list = model.getList();
        for (Customer customer : list) {
            System.out.println(customer.getFullName());
        }
    }
}
