import java.util.ArrayList;
import java.util.HashMap;

public class DumbSolve implements SolveMethod{
    public String solve(Maze m) {
        HashMap<Character,MazePath> maze_paths = new HashMap<>();
        //Get maze nodes hash
        HashMap<Character,ArrayList<MazeNode>> maze_nodes = m.getMazeAsHash();
        //get all characters in the hash
        ArrayList<Character> characters = new ArrayList<>();
        characters.addAll(maze_nodes.keySet());
        for(Character k: characters) {
            //if character in the hash is not _
            if (!k.equals('_')) {
                //get that area of the hash
                ArrayList<MazeNode> nodes = maze_nodes.get(k);
                //Look at the coordinates of the color
                MazePath mp = new MazePath(k,nodes.get(0),nodes.get(1));
                maze_paths.put(k,mp);
            }
        }
        
        return "";
    }
}
