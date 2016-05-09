/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgos;

import java.util.ArrayList;

/**
 *
 * @author schoubey
 */
public class GraphAlgos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Graph gr = new Graph();
        gr.addVertex("zero");
        gr.addVertex("one");
        gr.addVertex("two");
        gr.addVertex("three");
        gr.addVertex("four");
        gr.addVertex("five");
        gr.addAdjacentVertex("zero", "one");
        gr.addAdjacentVertex("one", "two");
        gr.addAdjacentVertex("one", "three");
        gr.addAdjacentVertex("three", "four");
        gr.addAdjacentVertex("two", "five");
        gr.printGraph();
        ArrayList<Graph.BFSObject> bfsList = gr.BreadthFirstSearch("zero");
        for(Graph.BFSObject bfs: bfsList) {
            System.out.println(bfs.toString());
        }
    }
    
}
