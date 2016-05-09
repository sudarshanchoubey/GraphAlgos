/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgos;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author schoubey
 */
public class Graph {
    ArrayList<AdjacencyList> adjacencyListArray;
    HashMap<String, Integer> nameToIndex;
    
    public static class BFSObject {
        private String color;
        private String ancestor;
        private int distance;

        public BFSObject() {
            this.color = "white";
            this.ancestor = null;
            this.distance = Integer.MAX_VALUE;
        }
        /**
         * @return the color
         */
        public String getColor() {
            return color;
        }

        /**
         * @param color the color to set
         */
        public void setColor(String color) {
            this.color = color;
        }

        /**
         * @return the ancestor
         */
        public String getAncestor() {
            return ancestor;
        }

        /**
         * @param ancestor the ancestor to set
         */
        public void setAncestor(String ancestor) {
            this.ancestor = ancestor;
        }

        /**
         * @return the distance
         */
        public int getDistance() {
            return distance;
        }

        /**
         * @param distance the distance to set
         */
        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
    public void printGraph() {
        for (int i = 0; i < adjacencyListArray.size(); i++) {
            System.out.println("adjacent to " + Integer.toString(i));
            AdjacencyList.printList(adjacencyListArray.get(i));
        }
    }
    
    public ArrayList<BFSObject> BreadthFirstSearch() {
        
    }
    public Graph() {
        this.adjacencyListArray = new ArrayList<>();
        this.nameToIndex = new HashMap<>();
    }
    
    public String[] getVertices() {
        return (String[]) nameToIndex.keySet().toArray();
    }
    
    public void addVertex(String name) {
        int index = adjacencyListArray.size();
        adjacencyListArray.add(null);
        nameToIndex.put(name, index);
    }
    
    public void addAdjacentVertex(String vertexName, String adjVertexName)
            throws RuntimeException{
        if(nameToIndex.containsKey(adjVertexName)) {
            adjacencyListArray.set(nameToIndex.get(vertexName),
                                   AdjacencyList.add(adjacencyListArray.get(
                                            nameToIndex.get(vertexName)),
                                        new AdjacencyList(
                                              nameToIndex.get(adjVertexName))));
            System.out.println("Added " +
                    Integer.toString(nameToIndex.get(adjVertexName)) + " to " +
                    Integer.toString(nameToIndex.get(vertexName)));
        } else {
            throw new RuntimeException(adjVertexName + " does not exist");
        }
    }

    private static class AdjacencyList {
        int index;
        AdjacencyList next;
        public AdjacencyList(int index) {
            this.index = index;
            this.next = null;
        }
        
        public static void printList(AdjacencyList al) {
            AdjacencyList tmp = al;
            while(tmp != null) {
                System.out.print("[" + Integer.toString(tmp.index) + "] -> ");
                tmp = tmp.next;
            }
            System.out.println("[Null]");
        }
        
        public static AdjacencyList add(AdjacencyList a, AdjacencyList b) {
            if(a == null) {
                return b;
            }
            System.out.println("adding " + Integer.toString(b.index) + " to " +
                    Integer.toString(a.index));
            AdjacencyList tmp = a;
            while(tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = b;
            return a;            
        }
    }
}
