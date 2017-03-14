/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.chat.util;

import com.puzan.webChatServer.DAO.userDAO;
import com.puzan.webChatServer.entities.Client;
import com.puzan.webChatServer.entities.Message;
import com.puzan.webChatServer.entities.User;
import com.puzan.webChatServer.impl.UserDAOimpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author puzan
 */
public class ClientListener extends Thread {

    private userDAO userdao;
    private Socket socket;
    private PrintStream out;
    private BufferedReader reader;
    private ClientHandler handler;
    private Client client;
    private Message msg;

    public ClientListener(Socket socket, ClientHandler handler) throws IOException {
        this.socket = socket;
        out = new PrintStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        userdao = new UserDAOimpl();
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            while (!login()) {
                out.println("invalid username and password.");
            }
            while (!isInterrupted()) {
                out.println("me says>");
                String line = reader.readLine();
                broadcastMessage(client, client.getUsername() + " says> " + line);
                msg = new Message(client.getUsername(), line);
                userdao.insertMessage(msg);
            }
        } catch (Exception ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private void broadcastMessage(Client client, String msg) throws IOException {
        for (Client c : handler.getClientList()) {
            if (!c.equals(client)) {
                PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
                ps.println(msg);
            }
        }

    }

    private boolean login() throws ClassNotFoundException, IOException, SQLException {
        out.println("Enter username:");
        String username = reader.readLine();
        out.println("Enter password:");
        String password = reader.readLine();
        System.out.println(username + " " + password);
        User user = userdao.login(username, password);
        if (user == null) {

            return false;
        }
        client = new Client(socket, username);
        handler.addClient(client);
        broadcastMessage(client, username + " has joined the room");
        return true;

    }

}
