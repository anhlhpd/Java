/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Phuong Anh
 */
public class JavaApplication {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://vnexpress.net");
            URLConnection conn = url.openConnection();
            StringBuilder contentBuilder = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));) {
                String inputLine;

                while ((inputLine = br.readLine()) != null) {
                    contentBuilder.append("\n");
                }
                System.out.println(contentBuilder.toString());

                String directoryName = "saved_pages";
                File directory = new File(directoryName);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                File[] listOfFiles = directory.listFiles();
                int count = 0;
                for (File f : listOfFiles) {
                    String strCount = f.getName().replace(".html", "")
                            .replace("index", "");
                    try {
                        int currentNumber = Integer.parseInt(strCount);
                        if (currentNumber > count) {
                            count = currentNumber;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                String fileName = "index" + (count + 1) + ".html";
                File file = new File(directoryName + "/" + fileName);

                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contentBuilder.toString());
                bw.close();
                fw.close();

            } catch (Exception e) {
            }

            FileWriter fw = new FileWriter("myfile.html");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contentBuilder.toString());
            bw.newLine();
            bw.close();

            FileOutputStream fos = new FileOutputStream("ten1file.html");
            fos.write(contentBuilder.toString().getBytes());
            fos.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
        // Bài thầy gửi
//        try {
//            URL url = new URL("https://vnexpress.net");
//        URLConnection conn = url.openConnection();
//        StringBuilder contentBuilder = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(conn.getInputStream())) {
//
//            String line;
//            while ((line  = br.readLine()
//
//            
//                ) != null) {
//                    contentBuilder.append(line);
//                contentBuilder.append("\n");
//            }
//        } 
//            String directoryName = "saved_pages";
//            File directory = new File(directoryName);
//            if (!directory.exists()) {
//                directory.mkdir();
//            }
//
//            File[] listOfFiles = directory.listFiles();
//            int count = 0;
//            for (File f : listOfFiles) {
//                String strCount = f.getName().replace(".html", "").replace("index", "");
//                try {
//                    int currentNumber = Integer.parseInt(strCount);
//                    if (currentNumber > count) {
//                        count = currentNumber;
//                    }
//                } catch (NumberFormatException e) {
//                }
//            }
//            String fileName = "index" + (count + 1) + ".html";
//            File file = new File(directoryName + "/" + fileName);
//
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(contentBuilder.toString());
//            bw.close();
//            fw.close();
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
        
        
        
    }

    
        

//    private static final String FILENAME = "D://index.html";
//
//    public static void main(String[] args) throws Exception {
//        URL oracle = new URL("https://vnexpress.net");
//        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
//
//        String inputLine;
//        FileOutputStream out = new FileOutputStream("D://index.html");
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println(inputLine);
//            FileWriter fw = new FileWriter(FILENAME);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(inputLine);
//
//            out.write(inputLine.getBytes());
//            in.lines();
//        }
//        out.close();
//        in.close();
//    }
    }
