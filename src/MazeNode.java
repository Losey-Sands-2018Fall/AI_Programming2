import java.util.ArrayList;

public class MazeNode {
    private int xCoord;
    private int yCoord;
    private ArrayList<MazeNode> neighbors;
    private Character val;
    private boolean isProtected;
    private Character lastVal;
    public MazeNode(int x, int y, Character value){
        xCoord = x;
        yCoord = y;
        val = value;
        neighbors = new ArrayList<>();
        isProtected = false;
    }
    public void setNeighbor(MazeNode m){
        neighbors.add(m);
    }
    public ArrayList<MazeNode> getNeighbors(){
        return neighbors;
    }
    public void setProtected(boolean is){
        isProtected = is;
    }
    public boolean isProtected(){
        return isProtected;
    }
    public void setVal(Character val) {
        lastVal = this.val;
        this.val = val;
    }

    public Character getVal() {
        return val;
    }
    public Character getLastVal(){
        return lastVal;
    }

    public int getxCoord() {
        return xCoord;
    }
    public int getyCoord() {
        return yCoord;
    }
}
