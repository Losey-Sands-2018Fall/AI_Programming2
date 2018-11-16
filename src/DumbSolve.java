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
        int count = 0;
        while(count < 5){
            for(Character ch:characters){
                MazePath currentPath = maze_paths.get(ch);
                complete:
                while(true){
                    MazeNode currentNode = currentPath.getLastNode();
                    int rand = (int)(Math.random()*currentNode.getNeighbors().size());
                    MazeNode testNode = currentNode.getNeighbors().get(rand);
                    for(MazeNode mn : testNode.getNeighbors()){
                        if(!(mn.getxCoord() == currentPath.getEndNode().getxCoord())){
                            if(!mn.isProtected()){
                                currentPath.addNode(testNode);
                            }
                        }else{
                            break complete;
                        }
                    }
                }
            }
            int goodCount = 0;
            for(Character ch:characters){
                if(maze_paths.get(ch).canGetToEnd()){
                    goodCount++;
                }
            }
            count++;
            System.out.println(goodCount);
        }

    }
}
