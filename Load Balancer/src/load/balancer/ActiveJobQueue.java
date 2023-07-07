/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;


public class ActiveJobQueue { //Class to implement active job to the linkedlist
    private String job = "";
    
    public ActiveJobQueue ( String job) {
        this.job = job;
    }
    public String getJob() {
        return job;
    }
}
