/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;


import javax.swing.SwingUtilities;

/**
 *
 * @author salas
 */

public class ClienteMain {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ClienteGUI gui =
                    new ClienteGUI();

            gui.setVisible(true);

        });
    }
}