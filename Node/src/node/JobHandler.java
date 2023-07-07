/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;

public class JobHandler extends Thread{
    //Retrieve sender socket from main class
    private DatagramSocket socket = null;
    private String LBaddress;
    private int LBport;
    public JobHandler(DatagramSocket Ssocket, String LBIPAddress, int LBPort) {
         socket = Ssocket;
         LBaddress = LBIPAddress;
         LBport = LBPort;
    }

    @Override
    public void run() {
        //main class
        System.out.println("Job Handler starting...");
        

        while (true) { //Loop to keep checking for jobs
            System.out.println("[HANDLER] Checking for available jobs...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception ex) {
            }

            //Checks for jobs in job queue
            if (JobQueueStore.isjobQueueEmpty() == false) {
                System.out.println("[HANDLER] " + JobQueueStore.getqueue().size() + " Job(s) Found");
                System.out.println("[HANDLER] Calculating total job time of jobs...");
                
                int JobTotal = 0;
                for (int i = 0; i < JobQueueStore.getqueue().size(); i++) {
                    String job = JobQueueStore.getqueue().get(i).toString();

                    String[] elements = job.trim().split(",");
                    int JobTime = Integer.parseInt(elements[1]);
                    JobTotal = JobTotal + JobTime;
                }
                TotalJobTimeStore.setTime(JobTotal);
                System.out.println("[HANDLER] Total job time is " + TotalJobTimeStore.getTotalTime() + " Seconds");

                // Code to manage first job
                Object job = JobQueueStore.removeJob();
                String StrJob = job.toString();
                System.out.println("[HANDLER] Retrieved job " + StrJob);
                String[] elements = StrJob.trim().split(",");
                int JobID = Integer.parseInt(elements[0]);
                int JobTime = Integer.parseInt(elements[1]);
                // Code to manage job
                System.out.println("[HANDLER] Starting job with ID " + JobID);
                int time = JobTime;
                for (int i = 0; i < JobTime; i++) {
                    time = time - 1;
                    TotalJobTimeStore.removeTime(1);
                    System.out.println("[HANDLER] " + time + " seconds left ");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        } catch (Exception ex) {
                        }
                }
                System.out.println("[HANDLER] Job Completed! Sending Finish message to LB");
                //Code to send message to LB
                String message = "FIN," + JobID;
                nodeSend.send(socket, message, LBaddress, LBport);
            }
            //if no jobs in the queue
            if (JobQueueStore.isjobQueueEmpty() == true) {
                System.out.println("[HANDLER] No current Jobs found");
            }
        }
    }
}
