package ngordnet.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private HashMap<Integer, List<Integer>> graphHashmap;

    // create an empty graph using the hashmap data structure
    public Graph() {
        graphHashmap = new HashMap<>();
    }

    // create createNode method to add a vertex
    public void createNode(Integer v) {
        List<Integer> valList = new ArrayList<>();
        graphHashmap.put(v, valList);
    }

    // create addEdge method from v to w
    public void addEdge(Integer v, Integer w) {
        // check for duplicate keys so that values are added, not replaced
        if (!graphHashmap.containsKey(v)) {
            createNode(v);
        }
        if (!graphHashmap.containsKey(w)) {
            createNode(w);
        }
        graphHashmap.get(v).add(w);
    }

    // create hasChild method to identify whether a vertex has children
    public boolean hasChild(Integer v) {
        if (graphHashmap.get(v) != null) {
            return true;
        }
        return false;
    }

    // graph traversal to see all connected nodes
    public List<Integer> traverseGraph(int v) {
        List<Integer> allConnectedNodes = new ArrayList<>();
        helper(graphHashmap.get(v), allConnectedNodes);
        return allConnectedNodes;
    }

    // @Source: Raina Park gave me the idea for creating a helper method and using recursion.
    private void helper(List<Integer> currChild, List<Integer> connectedNodes) {
        if (!currChild.isEmpty()) {
            for (int ID : currChild) {
                connectedNodes.add(ID);
                helper(graphHashmap.get(ID), connectedNodes);
            }
        }
    }
}

//public class Graph {
//    private HashMap<Integer, List<Integer>> graphHashmap;
//
//    // create an empty graph using the hashmap data structure
//    public Graph() {
//        graphHashmap = new HashMap<>();
//    }
//
//    // create createNode method to add a vertex
//    public void createNode(int v) {
//        List<Integer> valList = new ArrayList<>();
//        graphHashmap.put(v, valList);
//    }
//
//    // create addEdge method from v to w
//    public void addEdge(int v, int w) {
//        // check for duplicate keys so that values are added, not replaced
//        List<Integer> wList = new ArrayList<>();
//        wList.add(w);
//        if (graphHashmap.containsKey(v)) {
//            graphHashmap.get(v).addAll(wList);
//        } else {
//            createNode(v);
//            addEdge(v, w);
//        }
//    }
//
//    // create isConnected method to identify all children of a given vertex
//    public boolean hasChild(int v) {
//        if (!graphHashmap.get(v).isEmpty()) {
//            return true;
//        }
//        return false;
//    }
//
//    // graph traversal to see all connected nodes
//    public Object traverseGraph(int id) {
//        List<Integer> allConnectedNodes = new ArrayList<>();
//        if (hasChild(id)) {
//            List<Integer> childrenList = graphHashmap.get(id);
//            allConnectedNodes.addAll(childrenList);
//            for (int i = 0; i < childrenList.size(); i++) {
//                int ithChild = childrenList.get(i);
//                if (graphHashmap.containsKey(ithChild)) {
//                    allConnectedNodes.addAll(graphHashmap.get(ithChild));
//                }
//            }
//            return allConnectedNodes;
//        } else {
//            return null;
//        }
//    }
//}

//        for (String key: graphHashmap.keySet()) {
//            if (key.contains(word)) {
//                allConnectedNodes.add(key);
//                allConnectedNodes.addAll(graphHashmap.get(key));
//            }
//        }

//    public ArrayList<String> traverseGraph(String word) {
//        // create new arraylist of all connected nodes to return
//        ArrayList<String> allConnectedNodes = new ArrayList<>();
//        allConnectedNodes.add(word);
//        if (hasChild(word)) {
//            List<String> childrenList = graphHashmap.get(word);
//            if (!childrenList.isEmpty()) {
//                allConnectedNodes.addAll(childrenList);
//                for (String child : childrenList) {
//                    if (graphHashmap.containsKey(child)) {
//                        allConnectedNodes.addAll(graphHashmap.get(child));
//                    }
//                }
//                return allConnectedNodes;
//            }
//            return null;
//        }
//        return null;
//    }