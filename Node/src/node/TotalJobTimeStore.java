/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;


public class TotalJobTimeStore { //Function that maintains the node total completion time
    
    private static int TotalJobTime = 0;
    
    public static void setTime (int time){ //Sets the total time from the passed int variable
        TotalJobTime = time;
    }
    public static void removeTime (int time){ //reduces time by the passed variable
        TotalJobTime =- time;
    }
    public static int getTotalTime() { // Returns time int type
        return TotalJobTime;
    }
}
