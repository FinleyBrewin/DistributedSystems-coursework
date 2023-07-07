/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class nodeStart { // Function to start the node

    private DatagramSocket Rsocket = null;

    public void run() throws SocketException, Exception { // Main function
        String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
        System.out.println("=====================================");
        System.out.println("Server has been started...");
        try {
            Rsocket = getBoundSocket.Socket(); //Gets a valid socket
            Rsocket.setSoTimeout(0);
            System.out.println("Port number: " + Rsocket.getLocalPort());
            InetAddress ip = InetAddress.getLocalHost();
            String IP = ip.toString();
            String IP1 = IP.replaceAll(".+/", "");
            System.out.println("IP Address: " + IP1);
        } catch (UnknownHostException ex) {
        }
        System.out.println("=====================================");
        String type = "REG";

        //Using REGEX to get LB valid ip address
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Load Balancer IP Address");
        while (!sc.hasNext(IPV4_REGEX)) {
            System.out.println("That's not a valid IP Address");
            sc.next();
        }
        String LBIPAddress = sc.next();
        System.out.println("Thank you! Got Address: " + LBIPAddress);

        //Using a range to get LB valid Port number
        Scanner sc2 = new Scanner(System.in);
        boolean incorrectInput = true;
        int LBPort = -1;
         
        System.out.println("Please Enter Load Balancer Port Number");
        while (incorrectInput) {
            if (sc2.hasNextInt()) {
                LBPort = sc2.nextInt();

                if (3999 >= LBPort || LBPort >= 4020) {
                    System.out.println("Invalid Port number, must be 4000-4020 ");

                } else {
                    incorrectInput = false;
                }
            } else {
                sc2.next();
                System.out.println("Invalid Port number, must be 4000-4020 ");
            }
        }
        DatagramSocket Ssocket = getBoundSocket.Socket(); //gets a socket to send packets
        int port = Rsocket.getLocalPort();
        String message = MessageBuilder.jobBuild(type, port, "");
        System.out.println("[NODE] Sending REG message to LB...");

        nodeSend.send(Ssocket, message, LBIPAddress, LBPort); //sends message (Reg MESSAGE)

        nodeReceive mySystem = new nodeReceive(Rsocket, Ssocket, LBIPAddress, LBPort);
        mySystem.start(); //Starts receive thread

        JobHandler myNodeHan = new JobHandler(Ssocket, LBIPAddress, LBPort);
        myNodeHan.start();//Starts job handler thread
    }
}
