package AStar;

import java.util.ArrayList;

public class NodeGraph {
    ArrayList<ArrayList<Node>> graph;

    int cols;
    int rows;
    
    public NodeGraph(int cols, int rows) {
        graph = new ArrayList<ArrayList<Node>>();

        for(int y = 0; y < rows; y++) {
            graph.add(new ArrayList<Node>());
            
            for(int x = 0; x < cols; x++) {
                graph.get(y).add(new Node(x,y,true));
            }
        }
    }

    public ArrayList<Node> getNodeNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<Node>();

        if(node.row - 1 >= 0) {
            Node neighbor = graph.get(node.row - 1).get(node.col);
            if(neighbor.isTravelable) {
                neighbors.add(neighbor);
            }
        }

        if(node.col + 1 <= graph.size() - 1) {
            Node neighbor = graph.get(node.row).get(node.col + 1);
            if(neighbor.isTravelable) {
                System.out.println(String.format("Rigth: col:%d, row:%d", neighbor.col, neighbor.row));
                neighbors.add(neighbor);
            }
        }

        if(node.row + 1 <= graph.get(0).size() - 1) {
            Node neighbor = graph.get(node.row + 1).get(node.col);
            if(neighbor.isTravelable) {
                System.out.println(String.format("Down: col:%d, row:%d", neighbor.col, neighbor.row));
                neighbors.add(neighbor);
            }
        }

        if(node.col - 1 >= 0) {
            Node neighbor = graph.get(node.row).get(node.col - 1);
            if(neighbor.isTravelable) {
                System.out.println(String.format("Left: col:%d, row:%d", neighbor.col, neighbor.row));
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }
}
