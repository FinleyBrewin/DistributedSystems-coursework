/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package load.balancer;

import java.net.SocketException;



public class LoadBalancer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException { // Main Load Balancer class
    
    msgHandler Receive = new msgHandler();
    Receive.start(); //Starts message handler thread
    
    JobHandler jobHandler = new JobHandler();
    jobHandler.start(); //Starts Job handler thread
    
    PulseHandler Pulse = new PulseHandler();
    Pulse.start(); // Starts the pulse handler thread
   }
}
