/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;


/**
 *
 * @author schoubey
 */
public class Graph {
    ArrayList<Vertex> adjacencyListArray;
    HashMap<String, Integer> nameToIndex;
    HashMap<Integer, String> indexToName;
    
    
    public void printGraph() {
        for (int i = 0; i < adjacencyListArray.size(); i++) {
            System.out.println("adjacent to " + Integer.toString(i));
            Vertex.printList(adjacencyListArray.get(i));
        }
    }
    
    public Graph() {
        this.adjacencyListArray = new ArrayList<>();
        this.nameToIndex = new HashMap<>();
        this.indexToName = new HashMap<>();
    }
    
    public String[] getVertices() {
        return (String[]) nameToIndex.keySet().toArray();
    }
    
    public void addVertex(String name) {
        int index = adjacencyListArray.size();
        adjacencyListArray.add(null);
        nameToIndex.put(name, index);
        indexToName.put(index, name);
    }
    
    public void addAdjacentVertex(String vertexName, String adjVertexName)
            throws RuntimeException{
        if(nameToIndex.containsKey(adjVertexName)) {
            adjacencyListArray.set(nameToIndex.get(vertexName),
                                   Vertex.add(adjacencyListArray.get(
                                            nameToIndex.get(vertexName)),
                                        new Vertex(
                                              nameToIndex.get(adjVertexName))));
            System.out.println("Added " +
                    Integer.toString(nameToIndex.get(adjVertexName)) + " to " +
                    Integer.toString(nameToIndex.get(vertexName)));
        } else {
            throw new RuntimeException(adjVertexName + " does not exist");
        }
    }

    private static class Vertex {
        int index;
        Vertex next;
        public Vertex(int index) {
            this.index = index;
            this.next = null;
        }
        
        public static void printList(Vertex al) {
            Vertex tmp = al;
            while(tmp != null) {
                System.out.print("[" + Integer.toString(tmp.index) + "] -> ");
                tmp = tmp.next;
            }
            System.out.println("[Null]");
        }
        
        public static Vertex add(Vertex a, Vertex b) {
            if(a == null) {
                return b;
            }
            System.out.println("adding " + Integer.toString(b.index) + " to " +
                    Integer.toString(a.index));
            Vertex tmp = a;
            while(tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = b;
            return a;            
        }
    }
    
    public static class BFSObject {
        private String color;
        private Integer ancestor;
        private int distance;
        private String name;

        @Override
        public String toString() {
            return "name: " + this.getName() + " color: " + this.color +
                    " ancestor: " + this.ancestor +
                    " distance: " + this.distance;
        }
        public BFSObject() {
            this.color = "white";
            this.ancestor = null;
            this.distance = Integer.MAX_VALUE;
            this.name = null;
        }
        
        public String getColor() {
            return color;
        }

        
        public void setColor(String color) {
            this.color = color;
        }

        /**
         * @return the ancestor
         */
        public Integer getAncestor() {
            return ancestor;
        }

        /**
         * @param ancestor the ancestor to set
         */
        public void setAncestor(int ancestor) {
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

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
    /**
     * @param startVertex the vertex from which BFS should be started
     * @return an ArrayList containing BFSobjects for all vertices
     * @throws java.lang.InterruptedException
     */
    public ArrayList<BFSObject> BreadthFirstSearch 
            (String startVertex) throws InterruptedException {
        int startIndex = this.nameToIndex.get(startVertex);
        ArrayList<BFSObject> bfs  = 
                new ArrayList<>(this.adjacencyListArray.size());
        for(int i = 0 ; i < this.adjacencyListArray.size(); i++) {
            BFSObject bfsO = new BFSObject();
            bfsO.setName(this.indexToName.get(i));
            System.out.println("setting name to " + this.indexToName.get(i));
            bfs.add(bfsO);
        }
        bfs.get(startIndex).distance = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(startIndex);
        while(!q.isEmpty()) {
            int curIndex = q.removeFirst();
            for(Vertex tmp = adjacencyListArray.get(curIndex);
                    tmp != null;
                    tmp = tmp.next) {
                if( bfs.get(tmp.index).getColor().contentEquals("white")){
                    bfs.get(tmp.index).setColor("gray");
                    bfs.get(tmp.index).setDistance(
                            bfs.get(curIndex).getDistance() + 1);
                    bfs.get(tmp.index).ancestor = curIndex;
                    q.add(tmp.index);
                }
            }
            bfs.get(curIndex).setColor("black");
        }
        return bfs;
    }
}
