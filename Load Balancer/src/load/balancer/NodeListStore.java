/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package load.balancer;

import java.util.LinkedList;


public final class NodeListStore { //Class to maintain Node List

    private static LinkedList NodeList = new LinkedList();

    public static void addNode(NodeList node) { //Function to add node to the list
        NodeList.add(node);
    }

    public static void sendToBack() { //Function that sends the node to the back of the queue when it receives a job (round robin)
        Object node = NodeList.removeFirst();
        NodeList.addLast(node);
    }

    public static String getNodeList() { //Function that returns node list in string type
        String nodelist = NodeList.toString();
        return nodelist;
    }

    public static LinkedList getONodeList() { //Function that returns node list in object type (linked list)
        return NodeList;
    }

    public static Object getTopNodeList() { //Function that returns the first node in the list as object type
        Object node = NodeList.getFirst();
        return node;
    }

    public static Object removeNode(int index) { //Function that removes a node from the list using index send through and returns the node in object type
        Object node = NodeList.remove(index);
        return node;
    }

    public static String updateNode(Object node, int time) { //updates the node total job time
        int index = NodeList.indexOf(node);
        String Strnode = node.toString();
        String[] elements = Strnode.trim().split(",");
        int nodeID = Integer.parseInt(elements[0]);
        String nodeAddress = (elements[1]);
        int nodePort = Integer.parseInt(elements[2]);
        int nodeTime = Integer.parseInt(elements[3]) + time;
        String newNode = nodeID + "," + nodeAddress + "," + nodePort + "," + nodeTime;
        NodeList.set(index, newNode);
        return newNode;
    }

    public static void orderNodes() { //Class to order node list by total job time
        LinkedList OrderedQueue = new LinkedList();
        LinkedList QueueCopy = new LinkedList();
        QueueCopy = NodeListStore.getONodeList();
        boolean isEmpty = QueueCopy.isEmpty();
        int SmallestTime = -1;
        Object SmallestNode = null;

        while (isEmpty == false) {
            for (int i = 0; i < QueueCopy.size(); i++) {

                Object node = QueueCopy.get(i);

                String[] elements = node.toString().trim().split(",");
                int nodeTime = Integer.parseInt(elements[3]);
                if (SmallestTime == -1) {
                    SmallestTime = nodeTime;
                }
                if (nodeTime <= SmallestTime) {
                    SmallestNode = node;
                    SmallestTime = nodeTime;

                } else {
                }

            }
            OrderedQueue.add(SmallestNode);
            QueueCopy.remove(SmallestNode);
            SmallestTime = -1;
            if (isEmpty = QueueCopy.isEmpty()) {
                System.out.println("[ORDERING] Finished ordering queue, replacing old queue");   
                NodeList = OrderedQueue;
                System.out.println("[ORDERING] ordered queue = " + NodeList);
            }
        }
    }

    public static boolean isNodeListEmpty() { //Function that returns if the node list is empty
        return NodeList.isEmpty();
    }
}
