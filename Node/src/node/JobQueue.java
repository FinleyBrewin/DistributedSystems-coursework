/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;


public class JobQueue { // Function for job linkedlist
    // override to convert object to something readable
    public String toString() {
        return getJob();
    }
    private String job = "";
    
    public JobQueue ( String job) {
        this.job = job;
    }
    public String getJob() {
        return job;
    }
}
