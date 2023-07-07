/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class nodeReceive extends Thread { //Class to receive messages on a seperate thread
    private DatagramSocket socket = null;
    private DatagramSocket SendSocket = null;
    private String LBAddress = "";
    private int LBPort = 0;
    
    public nodeReceive(DatagramSocket Rsocket, DatagramSocket Ssocket, String LBaddress, int LBport) {
         socket = Rsocket;
         SendSocket = Ssocket;
         LBAddress = LBaddress;
         LBPort = LBport;
    }
    @Override
    public void run() { // Main run function
        try {
            while(true) { //Loop to keep checking for messages
                System.out.println("[RECEIVER] Waiting for messages");
                byte[] packetData = new byte[1024];
                DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
                socket.receive(packet); // Receive all messages
                String message = new String(packetData);
                System.out.println("[RECEIVER] Received message: " + message);
                
                String []elements = message.trim().split(","); // Chop up message to usuable chunks
                switch(elements[0]) {
                    case "JOB":
                        // code here for job message
                        System.out.println("--- Received JOB Message ---");
                        int jobID = Integer.parseInt(elements[1]);
                        int jobTime = Integer.parseInt(elements[2]);
                        System.out.println("--Job ID: " + jobID + "--");
                        System.out.println("--Job Time:" + jobTime + " Seconds--");
                        // add job to job queeu
                        String Job = jobID + "," + jobTime;
                        JobQueueStore.addJob(new JobQueue(Job));
                        System.out.println("Adding Job to Job Queue...");
                        System.out.println("--Current Job list: " + JobQueueStore.getqueue());
                        break;
                    case "CHECK":
                        // code here for check message
                        System.out.println("--- Received CHECK Message ---");
                        int nodeID = Integer.parseInt(elements[1]);
                        NodeIDStore.setID(nodeID);
                        int port = socket.getLocalPort();
                        String Type = "CHECK";
                        // Creates check message and sends it back to the LB
                        String Smessage = MessageBuilder.CheckBuild(Type , port);
                        nodeSend.send(SendSocket, Smessage, LBAddress, LBPort);

                        break;
                    default: // If no recognised message is found
                        System.out.println("[RECEIVER] Invalid instruction: " + elements[0]);
                }
            }
               
        }catch(IOException | NumberFormatException error) {
        } catch (Exception ex) {
        } finally { // used to try and gracefully close the socket if an error occurs
            try {
                System.out.println("[RECEIVER] Closing socket...");
                socket.close();
            }catch(Exception error) {
                
            }
        }
    }
}

