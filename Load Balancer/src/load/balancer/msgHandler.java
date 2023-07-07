/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class msgHandler extends Thread { //Creates a new thread for this class

    private int LBport = 0;
    private DatagramSocket socket = null;

    @Override
    public void run() { // Main class of handler
        System.out.println("[MsgHan] Server has been started...");
        try {
            socket = getBoundSocket.Socket();
            socket.setSoTimeout(0);
            LBport = socket.getLocalPort();
            InetAddress ip = InetAddress.getLocalHost();
            String IP = ip.toString();
            String IP1 = IP.replaceAll(".+/", "");
            System.out.println("=====================================");
            System.out.println("Load Balancer details...");
            System.out.println("IP Address: " + IP1);
            System.out.println("Port number: " + LBport);
        } catch (UnknownHostException ex) {
        } catch (SocketException ex) {
        }
        System.out.println("=====================================");
        try {

            int nodeID = 0;

            while (true) { //Accepts all incoming messages and breaks it up into useable parts
                byte[] packetData = new byte[1024];
                DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
                socket.receive(packet);
                String message = new String(packetData);
                System.out.println("[MsgHan] Received message: " + message);

                String[] elements = message.trim().split(",");
                switch (elements[0]) { //Switch type to check type of message
                    case "REG":
                        // code here for reg message
                        System.out.println("--- Received REG Message ---");
                        nodeID = nodeID + 1;
                        String nodeIP = (elements[2]);
                        int nodePort = Integer.parseInt(elements[3]);
                        int nodeTime = Integer.parseInt(elements[4]);
                        System.out.println("--Node ID: " + nodeID + "--");
                        //System.out.println("--Node Address:" + nodeIP + "--");
                        //System.out.println("--Node Port:" + nodePort + "--");
                        //System.out.println("--Node job Time: " + nodeTime + "Seconds--");
                        String Node = nodeID + "," + nodeIP + "," + nodePort + "," + nodeTime;
                        NodeListStore.addNode(new NodeList(Node));
                        System.out.println("[MsgHan] Adding Node to Node list...");
                        System.out.println("--Current node list: " + NodeListStore.getNodeList());

                        break;
                    case "JOB":
                        // code here for job message
                        System.out.println("--- Received JOB Message ---");
                        int jobID = Integer.parseInt(elements[3]);
                        int jobTime = Integer.parseInt(elements[4]);
                        System.out.println("--Job ID: " + jobID + "--");
                        //System.out.println("--Job Time:" + jobTime + " Seconds--");
                        String Job = jobID + "," + jobTime;
                        JobQueueStore.addJob(new JobQueue(Job));
                        System.out.println("[MsgHan] Adding Job to Job Queue...");
                        System.out.println("--Current Job list: " + JobQueueStore.getqueue());
                        break;
                    case "FIN":
                        // code here for job finished message
                        System.out.println("--- Received JOB FINISHED Message ---");
                        int jobid = Integer.parseInt(elements[1]);
                        System.out.println("--Job ID: " + jobid + "--");
                        int index = 0;
                        String job = "";
                        //remove job from active job list
                        for (int i = 0; i < ActiveJobQueueStore.getQueue().size(); i++) {
                            job = ActiveJobQueueStore.getQueue().get(i).toString();
                            String[] Jelements = job.trim().split(",");
                            int IDjob = Integer.parseInt(Jelements[0]);
                            if (IDjob == jobid) {
                                index = i;
                                ActiveJobQueueStore.removeJob(index);
                                System.out.println("[MsgHan] Removing job from active queue...");
                                System.out.println("[MsgHan] Current Active job list: " + ActiveJobQueueStore.getQueue());
                                break;
                            }
                        }

                        String[] Jelements = job.trim().split(",");
                        //Retrieving node data from job fin message
                        int time = Integer.parseInt(Jelements[1]);
                        time = -time;
                        int nodesID = Integer.parseInt(Jelements[2]);
                        String nodesAddress = (Jelements[3]);
                        int nodesPort = Integer.parseInt(Jelements[4]);
                        //Gets the node time using the node ID and checking node list
                        for (int i = 0; i < NodeListStore.getONodeList().size(); i++) {
                            String cNode = NodeListStore.getONodeList().get(i).toString();
                            String[] Belements = cNode.trim().split(",");
                            int IDofNode = Integer.parseInt(Belements[0]);
                            if (IDofNode == nodesID) {
                                System.out.println("[MsgHan] Found node ID from the received job message: " + nodesID);
                                int Time = Integer.parseInt(Belements[3]);
                                String node = nodesID + "," + nodesAddress + "," + nodesPort + "," + Time;

                                String NewNode = NodeListStore.updateNode(node, time);
                                System.out.println("[MsgHan] Updating node time to: " + NewNode);
                                break;
                            }

                        }
                        break;
                    case "CHECK":
                        // code here for Check message
                        System.out.println("--- Received CHECK Message ---");
                        nodeID = Integer.parseInt(elements[1]);
                        String nodesIP = (elements[2]);
                        int nodesport = Integer.parseInt(elements[3]);
                        //for statement to get the node time using node ID
                        for (int i = 0; i < NodeListStore.getONodeList().size(); i++) {
                            String cNode = NodeListStore.getONodeList().get(i).toString();
                            String[] Belements = cNode.trim().split(",");
                            int IDofNode = Integer.parseInt(Belements[0]);
                            if (IDofNode == nodeID) {
                                System.out.println("[MsgHan] Found node ID from the received job message: " + nodeID);
                                int Time = Integer.parseInt(Belements[3]);
                                //add node to the checked list
                                String node = nodeID + "," + nodesIP + "," + nodesport + "," + Time;

                                System.out.println("--Node ID: " + nodeID + "--");
                                //System.out.println("--Node Address:" + nodesIP + "--");
                                //System.out.println("--Node Port:" + nodesport + "--");
                                //System.out.println("--Node job Time: " + Time + "Seconds--");
                                System.out.println("[MsgHan] Adding Node to checked node list");
                                CheckedNodeListStore.addNode(new CheckedNodeList(node));
                                System.out.println("--Current Checked node list: " + CheckedNodeListStore.getNodeList());
                            }
                        }

                        break;
                    case "SHUTDOWN":
                        // code here for shutdown message
                        break;
                    default:
                        //If no valid case is found
                        System.out.println("[MsgHan] Invalid instruction: " + elements[0]);
                }
            }

        } catch (IOException | NumberFormatException error) {
        } finally { //If an error occurs in the main function try to close socket gracefully
            try {
                System.out.println("[MsgHan] Closing socket...");
                socket.close();
            } catch (Exception error) {

            }
        }
    }
}
