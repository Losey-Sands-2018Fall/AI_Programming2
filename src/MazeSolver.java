public class MazeSolver {
    private Maze m;
    public MazeSolver(Maze m){
        this.m = m;
    }
    public String solveMaze(SolveMethod sm){
        String answer = sm.solve(m);
        return answer;
    }
}
