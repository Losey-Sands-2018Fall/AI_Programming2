import java.util.ArrayList;

public class MazePath {
    private ArrayList<MazeNode> pathList;
    private Character pathName;
    private MazeNode startNode;
    private MazeNode endNode;
    private MazeNode lastRemoved;
    private int lastRemovedIndex;
    public MazePath(Character pathName, MazeNode end, MazeNode start){
        startNode = start;
        endNode = end;
        startNode.setProtected(true);
        endNode.setProtected(true);
        this.pathName = pathName;
        pathList = new ArrayList<>();
    }
    public void resetPath(){
        pathList.clear();
    }
    public boolean canGetToEnd(){
        MazeNode current = startNode;
        //Checking neighbors for end node
        for(MazeNode lastNodeCheck:current.getNeighbors()) {
            if (lastNodeCheck == endNode) {
                return true;
            }
        }
        for(MazeNode mn: pathList){
            for(MazeNode lastNodeCheck:mn.getNeighbors()) {
                if (lastNodeCheck == endNode) {
                    return true;
                }
            }
            if(current.getxCoord()-1 == mn.getxCoord() ^ current.getyCoord() -1 == mn.getyCoord() ^ current.getyCoord() +1 == mn.getyCoord() ^ current.getxCoord()+1 == mn.getxCoord()) {
                System.out.println("Valid");
            }else if(current.getVal() != mn.getVal()){
                System.out.println("Not the same letter");
                return false;
            }else{
                System.out.println("Invalid");
                return false;
            }
            current = mn;
        }
        //Checking connection to final node
        if(pathList.size() > 0){
            current = endNode;
            MazeNode mn = pathList.get(pathList.size()-1);
            if(current.getxCoord()-1 == mn.getxCoord() ^ current.getyCoord() -1 == mn.getyCoord() ^ current.getyCoord() +1 == mn.getyCoord() ^ current.getxCoord()+1 == mn.getxCoord()) {
                System.out.println("Valid");
            }else if(current.getVal() != mn.getVal()){
                System.out.println("Not the same letter");
                return false;
            }else{
                System.out.println("Invalid");
                return false;
            }
        }

        return true;
    }
    public MazeNode getStartNode() {
        return startNode;
    }

    public MazeNode getEndNode() {
        return endNode;
    }

    public void undoRemove(){
        pathList.add(lastRemovedIndex,lastRemoved);
    }

    public boolean addNode(MazeNode mn){
        mn.setVal(pathName);
        if(mn.isProtected()){
            return false;
        }
        if(mn.getyCoord() == endNode.getyCoord() && mn.getxCoord() == endNode.getxCoord()){
            System.out.println("Yep" + endNode.getxCoord() + "-" + endNode.getyCoord());
            return false;
        }else{
            pathList.add(mn);
            return true;
        }
    }
    public MazeNode getLastNode(){
        if(pathList.size() < 1){
            return startNode;
        }else{
            return pathList.get(pathList.size()-1);
        }

    }
    public void removeLastNode() {
        pathList.remove(pathList.size() - 1);
    }
    public void remove(MazeNode mn){
        for(int x = 0;x<pathList.size();x++){
            if(pathList.get(x) == mn){
                lastRemoved = mn;
                lastRemovedIndex = x;
                pathList.remove(x);
                break;
            }
        }
    }
    public boolean isInPath(MazeNode mn){
        for(MazeNode mazeNode: pathList){
            if(mn == mazeNode){
                return true;
            }
        }
        return false;
    }
    public ArrayList<MazeNode> getPathAsArray(){
        return pathList;
    }
}
