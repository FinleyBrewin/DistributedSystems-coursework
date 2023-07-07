/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.DatagramSocket;
import java.net.SocketException;

public class getBoundSocket { // Function that returns a datagram socket using a valid port from the list
    private static int[] getPortList() { //Returns the port list
        int[] portList = new int[]{4000, 4001, 4002, 4003, 4004, 4005, 4006, 4007, 4008, 4009, 4010};
        return portList;
    }

    public static DatagramSocket Socket() throws SocketException {
        for (int port : getPortList()) {
            try {
                System.out.println("Attempting to bind to socket port " + port);
                return new DatagramSocket(port);
            } catch (SocketException ignored) {
                continue;
            }
        }
        return new DatagramSocket(0);
    }
}
