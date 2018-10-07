/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author honghung
 */
public class ChatClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 6666);
        System.out.println("Kết nối thành công đến máy chủ.");
//        client.getInputStream();
        BufferedWriter  bw = new BufferedWriter (new OutputStreamWriter(client.getOutputStream(), Charset.forName("UTF-8")));
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String content = scanner.nextLine();
            bw.write(content);
            bw.newLine();
            bw.flush();
            if ("bye".equals(content)) {
                break;
            }
        }
        client.close();
        System.out.println("Đóng kết nối.");
    }
}
