/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.multicasttrue;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.InetAddress;
/**
 *
 * @author Alessio
 */
public class MulticastClient {
    public static void main(String[] args) throws java.io.IOException {
        // buffer di ricezione
        byte[] bufferIN = new byte[1024];
        //parameri del server
        int porta = 6789;
        String gruppo = "225.4.5.6";
        //creazione del socket sulla porta
        MulticastSocket socket = new MulticastSocket(porta);
        // mi aggiungo al gruppo Multicast
        InetAddress inetAddress=InetAddress.getByName(gruppo);
        socket.joinGroup(inetAddress);

        // creo il DatagramPacket e mi metto in ricezione
        DatagramPacket packetIN = new DatagramPacket(bufferIN, bufferIN.length);
        socket.receive(packetIN);
        // Visualizzo i parametri ed i dati ricevuti
        System.out.println("Ho ricevuto dati di lunghezza: " + packetIN.getLength()
                + " da: " + packetIN.getAddress().toString()
                + " porta :" + packetIN.getPort());
        System.out.write(packetIN.getData(), 0, packetIN.getLength());
        System.out.println();
        // al termine della ricezione lascio il gruppo
        socket.leaveGroup(InetAddress.getByName(gruppo));
        // chiudo i il socket
        socket.close();
    }
}