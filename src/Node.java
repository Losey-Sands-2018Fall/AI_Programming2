import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node
{
    //private members

    //The x position of this node
    private int x;
    //The y postion of this node
    private int y;
    //the value of this node
    private int value;
    //A collection of neighbors of this node
    private ArrayList<Node> neighbors;
    private int gValue;
    private int hValue;
    //determines if this node has been completed or not
    //eg. no more paths available for this node
    private boolean isCompleted;


    //The parent of this node...not sure if this will still be needed
    private Node parent;

    //Construct an instance of a node
    //x = the x postion of this node
    //y = the y postion of this node
    //value = the value of this node
    public Node(int x, int y, int value)
    {
        this.x = x;
        this.y = y;
        this.value = value;
        neighbors = new ArrayList<>(4);
        isCompleted = false;
    }

    //Provides access to adding a neighbor to this node
    public void addNeighbor(Node neighbor)
    {
        this.neighbors.add(neighbor);
    }


    //Returns an readonly list of neighbors
    public List<Node> getNeighbors() {
        return Collections.unmodifiableList(neighbors);
    }

    //returns the x position of this node
    public int getX() {
        return x;
    }

    //returns the y value of this node
    public int getY() {
        return y;
    }

    //return the value of this node
    public int getValue() {
        return value;
    }

    //provides access to setting the value of this node
    public void setValue(int value) {
        this.value = value;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    public int getG()
    {
        return this.gValue;
    }

    public void setG(int gValue)
    {
        this.gValue = gValue;
    }

    public int getH()
    {
        return this.hValue;
    }

    public void setH(int hValue)
    {
        this.hValue = hValue;
    }

    public int getF()
    {
        return hValue + gValue;
    }

}
