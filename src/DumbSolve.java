import java.util.ArrayList;
import java.util.HashMap;

public class DumbSolve implements SolveMethod{
    public void solve(Maze m) {
       ArrayList<MazePath> maze_paths = new ArrayList<>();
        //Get maze nodes hash
        HashMap<Character,ArrayList<MazeNode>> maze_nodes = m.getMazeAsHash();
        //get all characters in the hash
        ArrayList<Character> characters = new ArrayList<>();
        characters.addAll(maze_nodes.keySet());
        characters.remove(characters.size()-2);//removes underscore, probably a better way to do this

        for(Character k:characters){
            MazePath mp = new MazePath(k,maze_nodes.get(k).get(0),maze_nodes.get(k).get(1));
            System.out.println(k + "start" + maze_nodes.get(k).get(0).getxCoord() + "-" + maze_nodes.get(k).get(0).getyCoord());
            System.out.println(k + "end" + maze_nodes.get(k).get(1).getxCoord() + "-" + maze_nodes.get(k).get(1).getyCoord());
            maze_paths.add(mp);
        }
            int trying = 0;
            while (trying < 100) {
                for (MazePath currentPath : maze_paths) {
                    trying++;
                    if(currentPath.canGetToEnd()){
                        m.printNodes();
                        continue;
                    }
                    MazeNode lastNode = currentPath.getLastNode();
                    int random = (int)(Math.random()*lastNode.getNeighbors().size());
                    MazeNode choice = lastNode.getNeighbors().get(random);
                        if (!choice.isProtected()) {
                            if(!currentPath.isInPath(choice)){
                                currentPath.addNode(choice);
                                continue;
                            }
                        }
                    System.out.println();
                    m.printNodes();
                    if (currentPath.canGetToEnd()) {
                        System.out.println("Broke");
                        break;
                    }

                }
                System.out.println();
                int canGetToEnd = 0;
                for (MazePath currentPath : maze_paths) {
                    if (currentPath.canGetToEnd()){
                        canGetToEnd++;
                    }
                }
                System.out.println(canGetToEnd);
            }
    }
}
