/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 *
 * @author salas
 */
public class ClienteGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    private Cliente cliente;

    public ClienteGUI() {

        String nombre = JOptionPane.showInputDialog(
                this,
                "Introduce tu nombre:");

        if (nombre == null || nombre.trim().isEmpty()) {
            System.exit(0);
        }

        try {

            cliente = new Cliente(
                    "localhost",
                    5000,
                    nombre);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar al servidor");

            System.exit(0);
        }

        setTitle("Chat - " + nombre);

        setSize(500, 400);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        initComponents();

        iniciarEscucha();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        chatArea = new JTextArea();

        chatArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(chatArea);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        messageField =
                new JTextField();

        sendButton =
                new JButton("Enviar");

        bottomPanel.add(
                messageField,
                BorderLayout.CENTER);

        bottomPanel.add(
                sendButton,
                BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> enviar());

        messageField.addActionListener(e -> enviar());
    }

    private void enviar() {

        String mensaje =
                messageField.getText().trim();

        if (!mensaje.isEmpty()) {

            cliente.enviarMensaje(mensaje);

            chatArea.append(
                    "[Yo]: " + mensaje + "\n");

            messageField.setText("");
        }
    }

    private void iniciarEscucha() {

        Thread listener = new Thread(() -> {

            try {

                String mensaje;

                while ((mensaje =
                        cliente.getReader().readLine())
                        != null) {

                    chatArea.append(
                            mensaje + "\n");
                }

            } catch (IOException e) {

                chatArea.append(
                        "Conexión cerrada.\n");
            }

        });

        listener.setDaemon(true);

        listener.start();
    }
}