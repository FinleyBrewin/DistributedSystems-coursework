/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

/**
 *
 * @author xzims
 */
public class NodeIDStore {
    private static int NodeID = 0;
    
    public static void setID (int ID){ //Sets the node iD
        NodeID = ID;
    }
   
    public static int getID() { // Returns ID int type
        return NodeID;
    }
}
