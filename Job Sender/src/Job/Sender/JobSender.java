/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Job.Sender;


import java.net.SocketException;


/**
 *
 * @author xzims
 */
public class JobSender {

    public static void main(String[] args) throws SocketException {
        JobSend mySender = new JobSend();
        mySender.run();
    }
}
