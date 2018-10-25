import java.util.ArrayList;

public class MazePath {
    private ArrayList<MazeNode> pathList;
    private String pathName;
    public MazePath(String pathName){
        this.pathName = pathName;
    }
    public void addNode(MazeNode mn){
        pathList.add(mn);
    }
    public void removeLastNode(){
        pathList.remove(pathList.size()-1);
    }
    public MazeNode[] getPathAsArray(){
        return pathList.toArray(new MazeNode[0]);
    }
}
