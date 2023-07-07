/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Job.Sender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author xzims
 */
public class JobSend {

    private DatagramSocket socket = null;

    public static String jobcreater(int ID) {
        int min = 10;
        int max = 60;
        int length = (int) Math.floor(Math.random() * (max - min + 1) + min);
        String contents = ID + "," + length;
        return contents;
    }

    public void run() throws SocketException {
        String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
        int id = 0;
        socket = getBoundSocket.Socket();
        int port = socket.getLocalPort();
        String type = "JOB";

        //Using REGEX to get LB valid ip address
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Load Balancer IP Address");
        while (!sc.hasNext(IPV4_REGEX)) {
            System.out.println("That's not a valid IP Address");
            sc.next();
        }
        String LBIPAddress = sc.next();
        System.out.println("Thank you! Got Address: " + LBIPAddress);

        //Using a range to get LB valid Port number
        Scanner sc2 = new Scanner(System.in);
        boolean incorrectInput = true;
        int LBPort = -1;

        System.out.println("Please Enter Load Balancer Port Number");
        while (incorrectInput) {
            if (sc2.hasNextInt()) {
                LBPort = sc2.nextInt();

                if (3999 >= LBPort || LBPort >= 4020) {
                    System.out.println("Invalid Port number, must be 4000-4020 ");

                } else {
                    incorrectInput = false;
                }
            } else {
                sc2.next();
                System.out.println("Invalid Port number, must be 4000-4020 ");
            }
        }

        while (true) {
            try {
                InetAddress address = InetAddress.getByName(LBIPAddress);
                id = id + 1;
                String contents = jobcreater(id);
                String message = MessageBuilder.jobBuild(type, port, contents);
                System.out.println("Message: " + message);

                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, LBPort);
                System.out.println("Packet: " + packet);
                socket.send(packet);
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception ex) {
                }
            } catch (Exception ex) {
            }
        }

    }
}
