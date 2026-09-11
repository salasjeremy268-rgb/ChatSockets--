/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatsockets_jlsp;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author salas
 */
public class ServerGUI extends JFrame {
 
    private JTextArea logArea;
 
    public ServerGUI() {
        setTitle("Servidor de Chat  –  Puerto 5000");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
 
    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
 
        JLabel statusLabel = new JLabel("  ESTADO: ACTIVO  |  Puerto: 5000");
        statusLabel.setBackground(new Color(40, 167, 69));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setOpaque(true);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        add(statusLabel, BorderLayout.NORTH);
 
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setBackground(new Color(30, 30, 30));
        logArea.setForeground(new Color(0, 220, 0));
        logArea.setMargin(new Insets(5, 8, 5, 8));
 
        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Log del servidor"));
        add(scroll, BorderLayout.CENTER);
 
        JButton clearBtn = new JButton("Limpiar log");
        clearBtn.addActionListener(e -> logArea.setText(""));
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(clearBtn);
        add(bottom, BorderLayout.SOUTH);
    }
 
    public void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
}
 