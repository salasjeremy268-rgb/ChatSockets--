/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatsockets_jlsp;

/**
 *
 * @author salas
 */
public class ChatSockets_JLSP {
 
    public static void main(String[] args) {
        // Lanzar la GUI del servidor en el hilo de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            ServerGUI gui = new ServerGUI();
            gui.setVisible(true);
 

            Thread serverThread = new Thread(() -> {
                Server server = new Server(gui);
                server.start();
            });
            serverThread.setDaemon(true);
            serverThread.start();
        });
    }
}
 
