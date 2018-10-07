/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 *
 * @author honghung
 */
public class ClientThread extends Thread {

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
  
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

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), Charset.forName("UTF-8")));
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = this.br.readLine();
                if (null == line || "bye".equals(line) || "null".equals(line)) {
                    System.err.println(this.socket.getInetAddress().getHostAddress() + " đóng kết nối.");
                    break;
                }
                System.out.println(this.socket.getInetAddress().getHostAddress() + " gửi lên là " + line);
                ChatServer.broadCast(line);
            } catch (IOException ex) {
                System.err.println(this.socket.getInetAddress().getHostAddress() + " đã thoát.");
                break;
            }
        }
        System.err.println("Kết thúc luồng xử lý socket với ip là " + this.socket.getInetAddress().getHostAddress());
    }

}
