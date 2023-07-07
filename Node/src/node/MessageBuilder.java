/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.InetAddress;


public class MessageBuilder { // Simple function that builds the message before its sent to ensure it fits the template
    
    public static String jobBuild(String type, int port, String contents) throws Exception{
        InetAddress ip = InetAddress.getLocalHost();
            String IP = ip.toString();
            String IP1 = IP.replaceAll(".+/" , "");

       int Port = port;
       int ID = NodeIDStore.getID();
       String Type = type;
       int time = TotalJobTimeStore.getTotalTime();
       String Contents = contents;
       
       String Message = Type + ","+ ID + "," + IP1 + "," + Port + "," + time + "," +  Contents;
        
        return Message; // returns formatted message
    }
    
    public static String CheckBuild(String type, int port) throws Exception{
        InetAddress ip = InetAddress.getLocalHost();
            String IP = ip.toString();
            String IP1 = IP.replaceAll(".+/" , "");

       int Port = port;
       int ID = NodeIDStore.getID();
       String Type = type;
      
       
       String Message = Type + ","+ ID + "," + IP1 + "," + Port + ",";
        
        return Message; // returns formatted message
    }
}

