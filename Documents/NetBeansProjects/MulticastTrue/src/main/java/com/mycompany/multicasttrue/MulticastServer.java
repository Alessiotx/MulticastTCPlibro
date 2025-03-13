/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.multicasttrue;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;
/**
 *
 * @author Alessio
 */
public class MulticastServer {
 public static void main(String[] args) throws IOException {
     int count=20;
     boolean attivo=true;
     DatagramSocket socket = new DatagramSocket();
     InetAddress group = InetAddress.getByName("225.4.5.6");
     String message="ATTENZIONE: POSSIBILI ALLUVIONI!! "+count;
     byte[] msg = message.getBytes();
     while (attivo) {
         DatagramPacket packet = new DatagramPacket(msg, msg.length, group, 6789);
         socket.send(packet);
         System.out.println("inviato messaggio ALERT countdown: "+count);
         count--;
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
         if(count<=0){
             attivo=false;
             socket.close();
         }
     }
 }
}

