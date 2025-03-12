/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.multicast;
import java.net.*;
import java.io.*;
/**
 *
 * @author Alessio
 */
public class MulticastClient {
     public static void main(String[] args) throws IOException {
        byte[] bufferIN = new byte[1024];

        // Parametri del server
        int porta = 6789;
        String gruppo = "239.255.0.1"; // Usa un indirizzo multicast valido

        InetAddress group = InetAddress.getByName(gruppo);
        NetworkInterface netIf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

        try (MulticastSocket socket = new MulticastSocket(porta)) {
            socket.joinGroup(new InetSocketAddress(group, porta), netIf);

            System.out.println("Client in ascolto...");

            while (true) { // Ciclo infinito per ricevere più messaggi
                DatagramPacket packetIN = new DatagramPacket(bufferIN, bufferIN.length);
                socket.receive(packetIN);

                String messaggio = new String(packetIN.getData(), 0, packetIN.getLength());
                System.out.println("Messaggio ricevuto: " + messaggio + " da " + packetIN.getAddress().toString() + " porta: " + packetIN.getPort());
            }

        } catch (IOException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }
}
