/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class nodeSend extends Thread { // Function to send all messages on a seperate thread
    

    public static void send(DatagramSocket socket, String message, String LBaddress, int LBport) {
        try {
            InetAddress address = InetAddress.getByName(LBaddress);
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, LBport);
            socket.send(packet);
    } catch (Exception ex) {
            }
    }
}
