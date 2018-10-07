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
 * @author honghung
 */
public class DemoObjectHandle {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        writeToFile();
        readFile();
    }

    public static void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("jarvan.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (true) {
            if (fis.available() == 0) {
                break;
            }
            Student st = (Student) ois.readObject();
            System.out.println(st.toString());
        }
    }

    public static void writeToFile() throws FileNotFoundException, IOException {
        Student st1 = new Student("daolinh", 10);
        Student st2 = new Student("xuanhung", 20);

        FileOutputStream fos = new FileOutputStream("jarvan.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(st1);
        oos.writeObject(st2);
        oos.close();
    }
}
