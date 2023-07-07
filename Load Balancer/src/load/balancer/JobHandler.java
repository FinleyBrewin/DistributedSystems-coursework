/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;


public class JobHandler extends Thread { //Class to handle jobs in the job queue on a seperate thread

    private DatagramSocket SENDsocket = null;

    @Override
    public void run() { //Main function
        System.out.println("[JOB] Job Handler starting...");

        try {
            SENDsocket = getBoundSocket.Socket();
        } catch (SocketException ex) {
        }

        while (true) { //Main loop to repeat checking for jobs in the queue
            System.out.println("[JOB] Checking for available jobs...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception ex) {
            }

            //Checks if a job is in the queue
            if (JobQueueStore.isjobQueueEmpty() == false) {
                //Checks for a node in the node list
                if (NodeListStore.isNodeListEmpty() == false) {
                    //Gets the top job from queue
                    System.out.println("[JOB] Job Found");
                    Object job = JobQueueStore.removeJob();
                    String StrJob = job.toString();
                    System.out.println("[JOB] Retrieved job: " + StrJob);
                    // gets the top node from ordered list (by total job time)
                    Object node = NodeListStore.getTopNodeList();
                    System.out.println("[JOB] Retrieved node: " + node);
                    String Type = "JOB";
                    //update node with job time
                    String[] Jelements = StrJob.trim().split(",");
                    int time = Integer.parseInt(Jelements[1]);
                    String NewNode = NodeListStore.updateNode(node, time);
                    System.out.println("[JOB] Updating node time to: " + NewNode);
                    //send job to node
                    String[] Nelements = NewNode.trim().split(",");
                    int nodeID = Integer.parseInt(Nelements[0]);
                    String nodeIP = (Nelements[1]);
                    int nodePort = Integer.parseInt(Nelements[2]);
                    String NewNode1 = nodeID + "," + nodeIP + "," + nodePort;
                    String message = Type + "," + StrJob;
                    System.out.println("[JOB] Sending message:  " + message + " To node: " + nodeID + "...");
                    LBSend.send(SENDsocket, message, nodeIP, nodePort);
                    //NodeListStore.sendToBack(); //Used when only want round robin
                    //add job and node to active queue list
                    ActiveJobQueueStore.addJob(new JobQueue(StrJob + "," + NewNode1));
                    NodeListStore.orderNodes(); //orders Node list
                    System.out.println("[JOB] Current active jobs:  " + ActiveJobQueueStore.getQueue());
                }
                else {
                    System.out.println("[JOB] No Nodes found to allocate job"); //returns if no job is found
                }
            } else {
                System.out.println("[JOB] Job list is  currently empty"); //returns if a job is found but no node to complete job
            }

        }
    }
}
