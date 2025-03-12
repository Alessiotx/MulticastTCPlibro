/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.multicast;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Alessio
 */
public class MulticastServer {
     public static void main(String[] args) throws IOException {
        boolean attivo = true; // Flag per controllare lo stato del server
        byte[] bufferOUT = new byte[1024]; // Buffer per l'invio dei dati
        int conta = 20; // Contatore per il tempo di attività del server
        int porta = 6789; // Porta su cui il server invierà i pacchetti multicast

        // Indirizzo IP del gruppo multicast
        InetAddress group = InetAddress.getByName("239.255.0.1");

        // Creazione della socket multicast sulla porta specificata
        MulticastSocket socket = new MulticastSocket(porta);

        // Stringa che conterrà la data e l'ora da inviare
        String dString;

        // Ciclo principale del server, che invia dati finché è attivo
        while (attivo) {
            // Ottiene la data e l'ora attuali in formato stringa
            dString = new Date().toString();
            // Converte la stringa in un array di byte per l'invio
            bufferOUT = dString.getBytes();

            // Creazione del pacchetto da inviare al gruppo multicast
            DatagramPacket packet = new DatagramPacket(bufferOUT, bufferOUT.length, group, porta);

            // Invio del pacchetto ai client connessi al gruppo multicast
            socket.send(packet);

            // Attende 1 secondo prima di inviare il prossimo pacchetto
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Errore durante l'attesa: " + e.getMessage());
            }

            // Decrementa il contatore
            conta--;
            // Controlla se il tempo di attività è terminato
            if (conta == 0) {
                System.out.println("SERVER IN CHIUSURA. Buona serata.");
                attivo = false;
            } else {
                // Stampa il numero di secondi rimanenti prima della chiusura
                System.out.println("SERVER attivo per altri " + conta + " secondi.");
            }
        }

        // Chiude la socket multicast alla fine del ciclo
        socket.close();
    }
}
