/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatsockets_jlsp;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author salas
 */
public class Server {
 
    private static final int PORT = 5000;
    private static final List<ClientHandler> clients = new ArrayList<>();
    private final ServerGUI gui;
 
    public Server(ServerGUI gui) {
        this.gui = gui;
    }
 
    public void start() {
        gui.log("Servidor iniciado en puerto " + PORT);
 
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            gui.log("Esperando conexiones...");
 
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket, gui);
                addClient(handler);
 
                // Cada cliente corre en su propio hilo
                Thread t = new Thread(handler);
                t.setDaemon(true);
                t.start();
            }
 
        } catch (IOException e) {
            gui.log("Error en el servidor: " + e.getMessage());
        }
    }
 
    // ── Métodos estáticos para gestionar clientes 
 
    public static synchronized void addClient(ClientHandler c) {
        clients.add(c);
    }
 
    public static synchronized void removeClient(ClientHandler c) {
        clients.remove(c);
    }
 
    public static synchronized void broadcast(String message, ClientHandler sender) {
        for (ClientHandler c : clients) {
            if (c != sender) {
                c.sendMessage(message);
            }
        }
    }
 
    public static synchronized int getClientCount() {
        return clients.size();
    }
}