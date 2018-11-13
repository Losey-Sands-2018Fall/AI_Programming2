import java.util.ArrayList;
import java.util.HashMap;

public class DumbSolve implements SolveMethod{
    public void solve(Maze m) {
        HashMap<Character,MazePath> maze_paths = new HashMap<>();
        //Get maze nodes hash
        HashMap<Character,ArrayList<MazeNode>> maze_nodes = m.getMazeAsHash();
        //get all characters in the hash
        ArrayList<Character> characters = new ArrayList<>();
        characters.addAll(maze_nodes.keySet());
        for(Character k: characters) {
            //if character in the hash is not '_'
            if (!k.equals('_')) {
                //get that area of the hash
                ArrayList<MazeNode> nodes = maze_nodes.get(k);
                //make a mazePath that has a start and end node
                MazePath mp = new MazePath(k,nodes.get(0),nodes.get(1));
                maze_paths.put(k,mp);
            }
        }
        characters.remove(characters.size()-2);//removes underscore, probably a better way to do this
        for(Character ch:characters){
            System.out.println(ch);
            MazePath currentPath = maze_paths.get(ch);
            MazeNode currentNode = currentPath.getLastNode();
            int count = 0;
            letterPathDone:
            while(count<300) {
                count++;
                //If we find the endpoint, we finished this character
                for (MazeNode mn : currentNode.getNeighbors()) {
                    if (mn.getxCoord() == currentPath.getEndNode().getxCoord() && mn.getyCoord() == currentPath.getEndNode().getyCoord()) {
                        break letterPathDone;
                    }
                }
                //If we dont, we pick a spot
                for (MazeNode mn : currentNode.getNeighbors()) {
                    if (mn.getVal().equals('_')) {
                        currentPath.addNode(mn);
                        currentNode = currentPath.getLastNode();
                    } else if (!mn.getVal().equals('_') && !mn.isProtected()) {
                        //take but then check if the other path is still valid
                        Character replacing = mn.getVal();
                        currentPath.addNode(mn);
                        maze_paths.get(replacing).remove(mn);
                        if(!maze_paths.get(replacing).canGetToEnd()){
                            currentPath.removeLastNode();
                            maze_paths.get(replacing).undoRemove();
                            mn.setVal(mn.getLastVal());
                            System.out.println("Unsafe assignment, stepping back");
                        }
                        currentNode = currentPath.getLastNode();
                    }
                }
            }
        }
    }
}
