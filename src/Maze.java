import java.util.ArrayList;
import java.util.HashMap;

public class Maze {
    public Character[][] rawMaze;
    public MazeNode[][] mazeNodes;
    private HashMap<Character,ArrayList<MazeNode>> hMap;
    public Maze(String filename){
        Character[][] raw = MazeReader.readMazeAsCharArray(filename);
        if(raw != null && raw[0] != null){
            mazeNodes = new MazeNode[raw.length][raw[0].length];
            rawMaze = raw;
            makeNeighbors();
            printRawMaze(raw);
            hMap = new HashMap<>();
            for(int x =0;x<mazeNodes.length;x++){
                for(int y = 0;y<mazeNodes.length;y++){
                    ArrayList<MazeNode> result = hMap.get(mazeNodes[x][y].getVal());
                    if(result != null){
                        result.add(mazeNodes[x][y]);
                    }else{
                        ArrayList<MazeNode> temp = new ArrayList<>();
                        temp.add(mazeNodes[x][y]);
                        hMap.put(raw[x][y],temp);
                    }
                }
            }
        }
    }
    private void makeNeighbors(){
        for(int x = 0;x<rawMaze.length;x++){
            for(int y = 0;y<rawMaze[0].length;y++){
                mazeNodes[x][y] = new MazeNode(x,y,rawMaze[x][y]);
            }
        }
        for(int x = 0;x<rawMaze.length;x++){
            for(int y = 0;y<rawMaze[0].length;y++){
                //Ensuring that everything is within bounds and available to take
                if((y-1 >= 0)){
                    mazeNodes[x][y].setNeighbor(mazeNodes[x][y-1]);
                }
                if(x+1 < rawMaze.length) {
                    mazeNodes[x][y].setNeighbor(mazeNodes[x + 1][y]);
                }
                if(y+1 < rawMaze[0].length){
                    mazeNodes[x][y].setNeighbor(mazeNodes[x][y+1]);
                }
                if(x-1 >= 0){
                    mazeNodes[x][y].setNeighbor(mazeNodes[x-1][y]);
                }
            }
        }
    }
    public MazeNode[][] getMazeNodes() {
        return mazeNodes;
    }
    private void printRawMaze(Character[][] maze){
        for(int x = 0;x<maze.length;x++){
            for(int y = 0;y<maze[0].length;y++){
                System.out.print(maze[x][y]);
            }
            System.out.println();
        }
    }
    public void solveMaze(SolveMethod m){
        m.solve(this);
        printNodes();
    }
    public void printNodes(){
        for(int x =0;x<mazeNodes.length;x++){
            for(int y = 0;y<mazeNodes.length;y++){
                System.out.print(mazeNodes[x][y].getVal());
            }
            System.out.println();
        }
    }
    public Character[][] getRawMaze() {
        return rawMaze;
    }
    public HashMap<Character,ArrayList<MazeNode>> getMazeAsHash(){
        return hMap;
    }
}
