/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author honghung
 */
public class ChatServer {

    private static ArrayList<ClientThread> listClient;

    public static void main(String[] args) throws IOException {
        listClient = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server đã mở, chờ client kết nối.");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client với ip " + socket.getInetAddress().getHostAddress() + " đã kết nối.");
            ClientThread ct = new ClientThread(socket);
            ct.start();
            listClient.add(ct);
        }
    }

    public static void broadCast(String content) {
        System.out.println("Broadcasting...");
        try {
            for (int i = 0; i < listClient.size(); i++) {
                ClientThread ct = listClient.get(i);
                if (ct.getSocket().isConnected()) {
                    ct.getBw().write(content);
                    ct.getBw().newLine();
                    ct.getBw().flush();
                }
            }
        } catch (IOException e) {
        }
    }
}
