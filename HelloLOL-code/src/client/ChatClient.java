/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Phuong Anh
 */
public class ChatClient {

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;

    public ChatClient(Socket socket) {
        System.out.println("Kết nối thành công đến server.");
        this.socket = socket;
        try {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ChatClientReaderThread ccrt = new ChatClientReaderThread();
            ccrt.start();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("bye".equals(line)) {
                    break;
                }
                this.bw.write(line);
                this.bw.newLine();
                this.bw.flush();
            }
            this.bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    class ChatClientReaderThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    String line = br.readLine();
                    if (null != line) {
                        System.out.println("Server said: " + line);
                    }
                } catch (IOException e) {
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatClient(new Socket("10.22.185.219", 6000));
    }
}
