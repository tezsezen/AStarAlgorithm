package AStar;

public class Node {
    final static int HORIZONTALY = 0;
    final static int VERTICALY = 1;

    int col, row;
    boolean isTravelable;
    int fromDirection;
    double hCost;
    double gCost;
    double fCost;

    Node parent;

    public Node(int col, int row, boolean isTravelable) {
        this.col = col;
        this.row = row;
        this.isTravelable = isTravelable;
    }

    public static int toDirection(Node from, Node to) {
        int cameFrom;

        if(from.col != to.col) {
            cameFrom = Node.HORIZONTALY;
        }else {
            cameFrom = Node.VERTICALY;
        }
    
        return cameFrom;
    }
}