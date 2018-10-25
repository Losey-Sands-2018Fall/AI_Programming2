public class Maze {
    public Character[] rawMaze;
    public Maze(String filename){
        Character[][] raw = MazeReader.readMazeAsCharArray(filename);
        if(raw != null){
            printRawMaze(raw);
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
}
