public class Main {
    public static void main(String[] args) {
        Maze m5 = new Maze("7x7maze.txt");
        System.out.println("\n\n");
        m5.solveMaze(new DumbSolve());
    }
}
