public class MazeNode {
    private int xCoord;
    private int yCoord;
    private Character val;
    public MazeNode(int x, int y, Character value){
        xCoord = x;
        yCoord = y;
        val = value;
    }

    public Character getVal() {
        return val;
    }

    public int getxCoord() {
        return xCoord;
    }
    public int getyCoord() {
        return yCoord;
    }
}
