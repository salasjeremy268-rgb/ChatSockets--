/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatsockets_jlsp;

import java.io.*;
import java.net.*;
/**
 *
 * @author salas
 */
public class ClientHandler implements Runnable {
 
    private final Socket socket;
    private final ServerGUI gui;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nickname;
 
    public ClientHandler(Socket socket, ServerGUI gui) {
        this.socket = socket;
        this.gui = gui;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            gui.log("Error al abrir streams: " + e.getMessage());
        }
    }
 
    @Override
    public void run() {
        try {
            // Primer mensaje = nickname
            nickname = reader.readLine();
            gui.log("[+] Conectado: " + nickname + " (" + socket.getInetAddress() + ")");
            gui.log("Clientes activos: " + Server.getClientCount());
 
            Server.broadcast("*** " + nickname + " se ha unido al chat ***", this);
            sendMessage("*** Bienvenido, " + nickname + "! ***");
 
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("/salir")) break;
                String msg = "[" + nickname + "]: " + line;
                gui.log(msg);
                Server.broadcast(msg, this);
            }
 
        } catch (IOException e) {
            gui.log("Conexión perdida: " + (nickname != null ? nickname : "desconocido"));
        } finally {
            desconectar();
        }
    }
 
    public void sendMessage(String msg) {
        if (writer != null) writer.println(msg);
    }
 
    private void desconectar() {
        Server.removeClient(this);
        if (nickname != null) {
            Server.broadcast("*** " + nickname + " ha salido del chat ***", this);
            gui.log("[-] Desconectado: " + nickname);
            gui.log("Clientes activos: " + Server.getClientCount());
        }
        try { socket.close(); } catch (IOException ignored) {}
    }
}