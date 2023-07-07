/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.util.LinkedList;


public final class CheckedNodeListStore { //Class to maintain checked Node List

    private static LinkedList CheckedNodeList = new LinkedList();

    public static void addNode(CheckedNodeList node) { //Function to add node to the list
        CheckedNodeList.add(node);
    }

 
    public static String getNodeList() { //Function that returns node list in string type
        String nodelist = CheckedNodeList.toString();
        return nodelist;
    }

    public static LinkedList getONodeList() { //Function that returns node list in object type (linked list)
        return CheckedNodeList;
    }


    public static void ClearList() { //Function that clears the list
        CheckedNodeList.clear();
    }

    
    public static boolean isNodeListEmpty() { //Function that returns if the node list is empty
        return CheckedNodeList.isEmpty();
    }
}
