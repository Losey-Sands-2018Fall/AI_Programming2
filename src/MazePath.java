import java.util.ArrayList;

public class MazePath {
    private ArrayList<MazeNode> pathList;
    private Character pathName;
    private MazeNode startNode;
    private MazeNode endNode;
    private MazeNode lastAdded;
    private MazeNode lastRemoved;
    private int lastRemovedIndex;
    public MazePath(Character pathName, MazeNode end, MazeNode start){
        startNode = start;
        endNode = end;
        startNode.setProtected(true);
        endNode.setProtected(true);
        this.pathName = pathName;
        pathList = new ArrayList<>();
        pathList.add(start);
        pathList.add(end);
        lastAdded = start;
    }
    public boolean canGetToEnd(){
        boolean bad = false;
        for(int x = 0;x<pathList.size()-1;x++){
            MazeNode current = pathList.get(x);
            for(int y = 0;y<current.getNeighbors().size();y++){
                if(!pathList.get(x+1).equals(current.getNeighbors().get(y))){
                    bad = true;
                }
            }
        }
        System.out.println(bad);
        System.out.println(pathList.size());
        if(!bad){
            System.out.println("TRUE!!! with " + pathName);
            return true;
        }else{

        }
        return false;
    }
    public MazeNode getStartNode() {
        return startNode;
    }

    public MazeNode getEndNode() {
        return endNode;
    }

    public void addNode(MazeNode mn){
        mn.setVal(pathName);
        pathList.add(pathList.size()-2,mn);
        lastAdded = mn;
    }
    public MazeNode getLastNode(){
        return lastAdded;
    }
    public void resetPath(){
        for(int x = 0;x<pathList.size();x++){
            pathList.remove(0);
        }
        pathList.add(startNode);
        pathList.add(endNode);
    }
    public void remove(MazeNode mn){
        for(int x = 0;x<pathList.size();x++){
            if(pathList.get(x).equals(mn)){
                if(!mn.isProtected())pathList.get(x).setVal('_');
                lastRemoved = mn;
                lastRemovedIndex = x;
                pathList.remove(x);
                break;
            }
        }
    }
    public boolean isInPath(MazeNode mn){
        for(MazeNode mazeNode: pathList){
            if(mn.getxCoord() == mazeNode.getxCoord() && mn.getyCoord() == mazeNode.getyCoord()){
                return true;
            }
        }
        return false;
    }
}
