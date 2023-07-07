/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package node;


import java.net.SocketException;


public class Node { // Main Function that is used to start the node

    /**
     * @param args the command line arguments
     * @throws java.net.SocketException
     */
    public static void main(String[] args) throws SocketException, Exception  {
        nodeStart myNode = new nodeStart();
        myNode.run();

    }
 
}