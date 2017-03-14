/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.chat.util;

import com.puzan.webChatServer.entities.Client;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author puzan
 */
public class ClientHandler {

    private List<Client> clientList = new ArrayList<>();

    public void addClient(Client c) {
        clientList.add(c);
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public Client getByUsername(String username) {
        for (Client c : clientList) {
            if (c.getUsername().equalsIgnoreCase(username)) {
                return c;
            }

        }
        return null;
    }
}
