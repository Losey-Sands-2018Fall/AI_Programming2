import java.util.ArrayList;

public class MazePath {
    private ArrayList<MazeNode> pathList;
    private Character pathName;
    private MazeNode startNode;
    private MazeNode endNode;
    private MazeNode lastAdded;
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
        int good = 0;
        for(int x=0;x<pathList.size()-1;x++){
            MazeNode current = pathList.get(x);
            MazeNode next = pathList.get(x+1);
            for(MazeNode neighbor: current.getNeighbors()){
                if(next.equals(neighbor)){
                    good++;
                    break;
                }
            }
        }
        System.out.println(good);
        System.out.println(pathList.size());
        if(good  == pathList.size()-1){
            return true;
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
        lastAdded = startNode;
        pathList.add(startNode);
        pathList.add(endNode);
    }
    public void remove(MazeNode mn){
        for(int x = 0;x<pathList.size();x++){
            if(pathList.get(x).equals(mn)){
                pathList.get(x).setVal('_');
                pathList.remove(x);
                break;
            }
        }
    }
    public boolean isInPath(MazeNode mn){
        for(MazeNode mazeNode: pathList){
            if(mazeNode.equals(mn)){
                return true;
            }
        }
        return false;
    }
}
