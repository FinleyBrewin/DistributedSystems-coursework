/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class LBSend { // Function that sends a datagram message to the address
    

    public static void send(DatagramSocket socket, String message, String DESaddress, int DESport) {
        try {
            InetAddress address = InetAddress.getByName(DESaddress);
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, DESport);
            socket.send(packet);
    } catch (Exception ex) {
            }
    }
}
