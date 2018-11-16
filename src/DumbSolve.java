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
        boolean solved = false;
        while(!solved){
            for(MazePath currentPath :maze_paths){
                outer:
                while(true){
                    for(MazeNode endTest :currentPath.getEndNode().getNeighbors()){
                        if(currentPath.getLastNode().equals(endTest)){
                            System.out.println("Found end");
                            m.printNodes();
                            System.out.println();
                            break outer;
                        }
                    }
                    int random = (int)(Math.random()*currentPath.getLastNode().getNeighbors().size());
                    MazeNode randomPick = currentPath.getLastNode().getNeighbors().get(random);
                    if(!randomPick.isProtected()){
                        for(MazePath testingPath :maze_paths){
                            if(testingPath.isInPath(randomPick)){
                                testingPath.remove(randomPick);
                            }
                        }
                        currentPath.addNode(randomPick);
                    }
                }
            }
            int count = 0;
            for(MazePath currentPath :maze_paths){
                if(currentPath.canGetToEnd()){
                    count++;
                }
            }
            if(count == maze_paths.size()){
                solved = true;
            }else{
                for(MazePath currentPath :maze_paths) {
                    currentPath.resetPath();
                    System.out.println("Path Reset");
                }
            }
            System.out.println(count);
        }

    }
}
