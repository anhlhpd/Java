/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Phuong Anh
 */
public class Test {

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {
        FileWriter fw = new FileWriter("D://sinhvien.txt", true);
        File f = new File("D://sinhvien.txt");
        Scanner scn = new Scanner(System.in);

        System.out.println("==========================");
        System.out.println("1. Danh sách sinh viên.");
        System.out.println("2. Thêm sinh viên.");
        System.out.println("Vui lòng nhập lựa chọn:");
        int opt = scn.nextInt();

        while (true) {
            switch (opt) {
                case 1:

                    BufferedReader br = new BufferedReader(new FileReader(f));
                    StringBuilder contentBuilder = new StringBuilder();
                    String inputLine;

                    while ((inputLine = br.readLine()) != null) {
                        contentBuilder.append("\n");
                    }
                    System.out.println(contentBuilder.toString());

                    break;
                case 2:
                    System.out.println("Vui lòng nhập tên sinh viên:");
                    String name = scn.nextLine();
                    BufferedReader br1 = new BufferedReader(new FileReader(f));
                    StringBuilder contentBuilder1 = new StringBuilder();
                    String inputLine1;

                    while ((inputLine = br1.readLine()) != null) {
                        contentBuilder1.append("\n");
                    }

                    break;
                default:
                    break;
            }
        }
    }

}