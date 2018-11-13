import java.util.ArrayList;
import java.util.HashMap;

public class Maze {
    public Character[][] rawMaze;
    private HashMap<Character,ArrayList<MazeNode>> hMap;
    public Maze(String filename){
        Character[][] raw = MazeReader.readMazeAsCharArray(filename);
        if(raw != null){
            printRawMaze(raw);
            rawMaze = raw;
            hMap = new HashMap<>();
            for(int x =0;x<rawMaze.length;x++){
                for(int y = 0;y<rawMaze.length;y++){
                    ArrayList<MazeNode> result = hMap.get(rawMaze[x][y]);
                    if(result != null){
                        result.add(new MazeNode(x,y,rawMaze[x][y]));
                    }else{
                        ArrayList<MazeNode> temp = new ArrayList<>();
                        temp.add(new MazeNode(x,y,rawMaze[x][y]));
                        hMap.put(rawMaze[x][y],temp);
                    }
                }
            }
        }
    }
    private void printRawMaze(Character[][] maze){
        for(int x = 0;x<maze.length;x++){
            for(int y = 0;y<maze[0].length;y++){
                System.out.print(maze[x][y]);
            }
            System.out.println();
        }
    }
    public String solveMaze(SolveMethod m){
        return m.solve(this);
    }
    public Character[][] getRawMaze() {
        return rawMaze;
    }
    public HashMap<Character,ArrayList<MazeNode>> getMazeAsHash(){
        return hMap;
    }
}
