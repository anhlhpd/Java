/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Phuong Anh
 */
public class DemoObjectHandle {
    // Ghi thông tin ra file: OutputStream, OutputStreamReader, BufferedWriter
    // Đọc thông tin từ file: InputStream, InputStreamReader, BufferedReader 

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        readFile();
    }

    public static void writeToFile() throws FileNotFoundException, IOException {
        Student student1 = new Student("Xuân Hùng", 20);
        Student student2 = new Student("Đào Linh", 10);
        FileOutputStream fos = new FileOutputStream("object.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(student1);
        oos.writeObject(student2);
        oos.close();
    }

    public static void readFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("object.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (true) {
            if (fis.available() == 0) {
                break;
            }
            Student student = (Student) ois.readObject();
            System.out.println(student.toString());
        }
    }
}
