import java.util.ArrayList;

public class MazePath {
    private ArrayList<MazeNode> pathList;
    private Character pathName;
    private MazeNode startNode;
    private MazeNode endNode;
    public MazePath(Character pathName, MazeNode end, MazeNode start){
        startNode = start;
        endNode = end;
        this.pathName = pathName;
    }
    public boolean addNode(MazeNode mn){
        if(mn == endNode){
            return false;
        }else{
            pathList.add(mn);
            return true;
        }
    }
    public void removeLastNode() {
        pathList.remove(pathList.size() - 1);
    }
    public boolean isInPath(int x,int y){
        for(MazeNode mn: pathList){
            if(mn.getxCoord() == x && mn.getyCoord() == y){
                return true;
            }
        }
        return false;
    }
    public MazeNode[] getPathAsArray(){
        return pathList.toArray(new MazeNode[0]);
    }
}
