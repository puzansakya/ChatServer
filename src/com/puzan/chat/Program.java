/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.chat;

import com.puzan.chat.util.ClientHandler;
import com.puzan.chat.util.ClientListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author puzan
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int port = 9000;
            ServerSocket server = new ServerSocket(port);
            ClientHandler handler = new ClientHandler();
            System.out.println("server is running at " + port);
            while (true) {
                Socket socket = server.accept();
                //add log to db here
                System.out.println("Connection Request from" + socket.getInetAddress().getHostAddress());
                ClientListener listener = new ClientListener(socket,handler);
                listener.start();

            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

}
