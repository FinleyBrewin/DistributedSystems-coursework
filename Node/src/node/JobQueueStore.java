/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.util.LinkedList;


public class JobQueueStore { //Function to maintain job queue list
 
    private static final LinkedList JobQueue = new LinkedList();
    
    public static void addJob (JobQueue job){ //Function to add a job to the list
        JobQueue.add(job);
    }
    public static String getStrqueue() { //Function to return job queue as a string type
        String jobqueue = JobQueue.toString();
        return jobqueue;
    }
    public static LinkedList getqueue() { //Function to return job queue as an object type (linked list)
        return JobQueue;
    }
    public static Object removeJob() { //Function to remove the first job from queue and return it
        Object job = JobQueue.pop().toString();
        return job;
    }
    public static boolean isjobQueueEmpty() {//Checks if job queue is empty and returns true or false
         return JobQueue.isEmpty();
     }
}
