import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter in the maze filename:");
        Maze m5 = new Maze(in.nextLine());
        System.out.println("\n\n");
        m5.solveMaze(new DumbSolve());
    }
}
