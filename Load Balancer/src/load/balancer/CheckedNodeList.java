/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;


public class CheckedNodeList { // Class for node linkedlist
    // override to convert object to something readable
    public String toString() {
        return getNode();
    }
    private String node = "";
    
    public CheckedNodeList ( String node) {
        this.node = node;
    }
    public String getNode() {
        return node;
    }
}
