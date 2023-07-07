/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.util.LinkedList;


public final class  JobQueueStore { //Class to maintain the job queue list
 
    private static final LinkedList JobQueue = new LinkedList();
    
    public static void addJob (JobQueue job){ // Used to add a job to the queue
        JobQueue.add(job);
    }
    public static String getqueue() { //Used to return the queue in a string type
        String jobqueue = JobQueue.toString();
        return jobqueue;
    }
    public static Object removeJob() { //Removes first job and returns it as a string type
        Object job = JobQueue.pop().toString();
        return job;
    }
    public static boolean isjobQueueEmpty() { //Checks if queue is empty and returns true or false
         return JobQueue.isEmpty();
     }
}
