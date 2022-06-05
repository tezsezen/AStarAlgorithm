package AStar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
    NodeGraph nodeGraph;

    public AStar(NodeGraph nodeGraph) {
        this.nodeGraph = nodeGraph;
    }

    public ArrayList<Node> findPath(Node start, Node end) {
        Node current = null;
        HashSet<Node> closedSet = new HashSet<Node>();
        PriorityQueue<Node> openSet = new PriorityQueue<Node>(100, new NodeComparator());
        
        openSet.add(start);

        start.fromDirection = Node.HORIZONTALY;
        start.gCost = 0;
        start.hCost = calcHCost(start, end);
        start.fCost = start.gCost + start.hCost;

        while(!openSet.isEmpty()) {
            current = openSet.poll();

            if(current == end) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for(Node neighbor : nodeGraph.getNodeNeighbors(current)) {
                
                if(neighbor == null || closedSet.contains(neighbor)) continue;
                
                double tentativeGCost = current.gCost + calcGCost(current, neighbor);

                if(!openSet.contains(neighbor) || Double.compare(tentativeGCost, neighbor.gCost) < 0) {
                    neighbor.parent = current;
                    neighbor.fromDirection = Node.toDirection(current, neighbor);
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = calcHCost(neighbor, end);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;

                    if(!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private ArrayList<Node> reconstructPath(Node end){
        ArrayList<Node> totalPath = new ArrayList<Node>();
        totalPath.add(end);

        while(end.parent != null) {
            end = end.parent;
            totalPath.add(end);
        }

        return totalPath;
    }
    
    private double calcHCost(Node current, Node end) {
        return Math.abs(current.col - end.col) + Math.abs(current.row - end.row);
    }

    public double calcGCost(Node current, Node neighbor) {
        if(current.fromDirection == Node.toDirection(current, neighbor)) {
            return 1;
        } else{
            return 1.2;
        }
    }
}
