/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Job.Sender;

import java.net.InetAddress;

/**
 *
 * @author xzims
 */
public class MessageBuilder {
    
    public static String jobBuild(String type, int port, String contents) throws Exception{
        InetAddress ip = InetAddress.getLocalHost();
            String IP = ip.toString();
            String IP1 = IP.replaceAll(".+/" , "");

       int Port = port;
       String Type = type;
       String Contents = contents;
       
       String Message = Type + "," + IP1 + "," + Port + "," +  Contents;
        
        return Message;
    }
    
}

