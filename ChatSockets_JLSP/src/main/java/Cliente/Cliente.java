/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author salas
 */
public class Cliente {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Cliente(String host, int puerto, String nickname) throws IOException {

        socket = new Socket(host, puerto);

        reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        writer = new PrintWriter(
                socket.getOutputStream(), true);

        // Primer mensaje = nickname
        writer.println(nickname);
    }

    public void enviarMensaje(String mensaje) {
        writer.println(mensaje);
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void cerrar() {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
}