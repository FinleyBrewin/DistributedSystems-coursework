/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.util.LinkedList;

public final class ActiveJobQueueStore { //Class for active job queue

    private static LinkedList JobQueue = new LinkedList();

    public static void addJob(JobQueue job) { //Function to add a job to the queue
        JobQueue.add(job);
    }

    public static LinkedList getQueue() { //Function to return job queue
        return JobQueue;
    }

    public static void removeJob(int Index) { //Function to remove job using index
        JobQueue.remove(Index);

    }

    public static void removeJobNode(String node) { //Function to remove all jobs sent to a specific node
        LinkedList JobQueueCopy = JobQueue;
        String[] elements = node.trim().split(",");
        int NodeID = Integer.parseInt(elements[0]);
        String NodeIP = (elements[1]);
        int NodePort = Integer.parseInt(elements[2]);
        String NoTimeNode = NodeID + "," + NodeIP + "," + NodePort;
        
        //Go through active job list to check if any jobs are run by broken node
        for (int i = 0; i < ActiveJobQueueStore.getQueue().size(); i++) {
            String job = ActiveJobQueueStore.getQueue().get(i).toString();
            String[] Jelements = job.trim().split(",");
            int jJobID = Integer.parseInt(Jelements[0]);
            int jJobTime = Integer.parseInt(Jelements[1]);
            int jNodeID = Integer.parseInt(Jelements[2]);
            String jNodeIP = (Jelements[3]);
            int jNodePort = Integer.parseInt(Jelements[4]);
            String jNode = jNodeID + "," + jNodeIP + "," + jNodePort;
            String newJob = jJobID + "," + jJobTime;
            //System.out.println("[msgHan] Checking job " + job);
            //If job is run by node remove it and add it to job list
            if (jNode.equals(NoTimeNode)) {
                System.out.println("[Pulse] job  " + job + " was sent to " + node);
                JobQueueCopy.remove(i);
                JobQueueStore.addJob(new JobQueue(newJob));
            } else {
                //System.out.println("[msgHan] Job isn't run by " + node);
            }
        }
        System.out.println("[Pulse] Current jobs list " + JobQueueStore.getqueue());
        //Finds nodes that aren't in checked node list and remove them from the node list
        for (int i = 0; i < NodeListStore.getONodeList().size(); i++) {
            Object Node = NodeListStore.getONodeList().get(i);
            if (Node.toString().equals(node)) {
                NodeListStore.removeNode(NodeListStore.getONodeList().indexOf(Node));
                System.out.println("[Pulse] Removing node from node list");
                break;
            }
        }
        //Updates the job queue to the correct one
        JobQueue = JobQueueCopy;
    }
}
