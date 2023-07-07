/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class PulseHandler extends Thread { // Function to send check messages to all nodes

    private DatagramSocket SENDsocket = null;

    @Override
    public void run() { //Main function
        System.out.println("[Pulse] Pulse Handler starting...");

        try {
            SENDsocket = getBoundSocket.Socket();
        } catch (SocketException ex) {
        }

        while (true) { //Main loop to repeat sending check messages
            String Type = "CHECK";
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (Exception ex) {
            }
            if (NodeListStore.isNodeListEmpty() == false) {
                System.out.println("[Pulse] Sending pulse to all nodes...");
                // Code to go through the node list and send a Check message to them all
                for (int v = 0; v < NodeListStore.getONodeList().size(); v++) {
                    String vNode = NodeListStore.getONodeList().get(v).toString();
                    String[] elements = vNode.trim().split(",");
                    int IDNode = Integer.parseInt(elements[0]);
                    String IPNode = (elements[1]);
                    int PortNode = Integer.parseInt(elements[2]);
                    String message = Type + "," + IDNode;
                    System.out.println("[Pulse] Sending pulse to  node " + vNode);
                    LBSend.send(SENDsocket, message, IPNode, PortNode);

                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (Exception ex) {
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(300*NodeListStore.getONodeList().size());
                } catch (Exception ex) {
                }
                if (NodeListStore.getONodeList().size() == CheckedNodeListStore.getONodeList().size()) { //Check if the two node lists are equal
                    System.out.println("[Pulse] All nodes are operating correctly");
                } else {
                    System.out.println("[Pulse] One or more nodes are not responding");
                    for (int i = 0; i < NodeListStore.getONodeList().size(); i++) {
                        String Node = NodeListStore.getONodeList().get(i).toString();
                        //re-formats node to remove time
                        System.out.println("Node = " + Node);
                        String NotInCheckList = "";
                        String[] Nelements = Node.trim().split(",");
                        int IDNode = Integer.parseInt(Nelements[0]);
                        String IPNode = (Nelements[1]);
                        int PortNode = Integer.parseInt(Nelements[2]);
                        String fNode = IDNode + "," + IPNode + "," + PortNode;
                        
                        //checks if any nodes responded
                        if (CheckedNodeListStore.getONodeList().isEmpty() == true) {
                            System.out.println("[Pulse] No Nodes replied to pulse");
                            ActiveJobQueueStore.removeJobNode(Node);
                            break;
                        }
                        for (int l = 0; l < CheckedNodeListStore.getONodeList().size(); l++) {
                            String cNode = CheckedNodeListStore.getONodeList().get(l).toString();
                             //re-formats node to remove time
                            System.out.println("cNode = " + cNode);
                            String[] Celements = cNode.trim().split(",");
                            int cIDNode = Integer.parseInt(Celements[0]);
                            String cIPNode = (Celements[1]);
                            int cPortNode = Integer.parseInt(Celements[2]);
                            String fcNode = cIDNode + "," + cIPNode + "," + cPortNode;
                            
                            if (fNode.equals(fcNode)) {
                                //System.out.println("[Pulse] Found Node checked node in node list");
                                NotInCheckList = "";
                                break;
                            } else {
                                //System.out.println("[Pulse] Node didn't match");
                                NotInCheckList = Node;

                            }
                        }
                        if(NotInCheckList.isBlank() == false) {
                            System.out.println("[Pulse] Removing the node " + Node + " and sending allocated jobs to the job queue");
                            ActiveJobQueueStore.removeJobNode(Node);
                        }
                    }
                    if (NodeListStore.isNodeListEmpty() == true) {
                        System.out.println("[Pulse] No nodes found");
                    }
                }

                System.out.println("[Pulse] Resetting Checked queue");
                CheckedNodeListStore.ClearList();

            } else {
                System.out.println("[Pulse] no nodes to send pulse to");
            }
        }
    }
}
