import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
    public static void main(String args[]) throws IOException
    {
        //
        //Initialize data
        //

        System.out.println("Initializing...");
        ArrayList<String> mapData = Utilities.readFile("test.txt");
        ArrayList<Integer> colors = Utilities.getcolors(mapData);   //TODO: may be useful?
        Grid solvedState = new Grid(mapData);
        Pairs pairs = new Pairs(solvedState,false);
        Node test = new Node(0,1,79);
//            solvedState.sameXAxis(solvedState);
//            solvedState.sameYAxis(solvedState);
            solvedState.display();
        solve(solvedState);

    }

    private static void solve(Grid solvedState)
    {
        StopWatch watch = new StopWatch();
        System.out.println("Solving...");
        watch.start();

        //A more efficient way to determine if solves...however, may make the code more difficult to understand...
        int filledCount = 0;

        //while (filledCount != grid.getHeight() * grid.getWidth())

        while (isFilled(solvedState) == false)
        {

            while (true)
            {
                Grid previousState = new Grid(solvedState);
                forceMove(solvedState);

                if(previousState.equal(solvedState))
                    break;
            }

            solvedState.fillRegions();

            System.out.println("Main::solve(Grid) - sleeping for 2 sec");
            sleep(2000);
            System.out.println();
            solvedState.display();
            if (watch.elapsedSeconds()==13){
                diffsolve();
            }
        }

        System.out.println("\nCompleted: ");
        solvedState.display();
        watch.stop();
        System.out.println("Solved in:");
        watch.displayElapsedTime();
    }
    public static void diffsolve(){

    }

    private static void sleep(int time)
    {
        try {
            Thread.sleep(time);
        }
        catch (Exception e)
        {

        }
    }

    private static void forceMove(Grid grid)
    {
        //Update the grid
        for (int y = 0; y < grid.getHeight(); y++)
        {
            for (int x = 0; x < grid.getWidth(); x++)
            {
                Node currentNode = grid.getNodeAt(x, y);
                int possibleNumberOfMoves = 0;
                Node movement = null;
                //If node is empty, then no reason to continue
                if(currentNode.getValue() == Utilities.EMPTY_SPACE)
                    continue;
                //if node is completed, then we can move on to the next node
                if(currentNode.isCompleted())
                {
                    //filledCount++;
                    continue;
                }

                //iterate all neighbors of the current node
                for (Node node : currentNode.getNeighbors())
                {
                    if(grid.isCorner(node) && grid.getValueAt(node.getX(), node.getY()) == Utilities.EMPTY_SPACE)
                    {
                        movement = node;
                        possibleNumberOfMoves = 1;
                        break;
                    }

                    //if neighbor is empty space, possible move
                    if(node.getValue() == Utilities.EMPTY_SPACE)
                    {
                        possibleNumberOfMoves++;
                        movement = node;
                    }

                }

                //if only one possible move, then force the move
                if(possibleNumberOfMoves == 1)
                {
                    grid.setValueAt(movement.getX(), movement.getY(), currentNode.getValue());

                    //The number of neighbors that have the same value
                    int sameValueCount = 0;
                    for (Node node : movement.getNeighbors())
                    {
                        //if a neighbor has the same value, then we can make as completes and increment the number number of nodes of this kind
                        //a constraint, a node may only have a mx of 2 neighbors that are of the same kind
                        if(node.getValue() == movement.getValue())
                        {
                            node.setCompleted(true);
                            sameValueCount++;
                        }
                    }

                    if(sameValueCount == 2)
                        movement.setCompleted(true);
                }
                //TODO: else we need to perform another method of determining a move

            }
        }
//        Utilities.paths(grid);
//
//        for(int i=0 i<)
//        Utilities.BFS()
    }

    //returns true if any cell in the grid is empty
    public static boolean isFilled(Grid grid)
    {
        for (int y = 0; y < grid.getHeight(); y++)
        {
            for (int x = 0; x < grid.getWidth(); x++)
            {
                if(grid.getValueAt(x, y) == Utilities.EMPTY_SPACE)
                    return false;
            }

        }

        return true;
    }

    private static void Testing(ArrayList<Integer> colors, ArrayList<String> mapData)
    {
        HashMap<Integer, Grid> gridColors = new HashMap<>();

        for (int i : colors)
        {
            Grid grid = new Grid(mapData);
            gridColors.put(i, grid);
            HashMap<Integer, ArrayList<Node>> pairs = Utilities.getPairs(grid);

            ArrayList<Node> path = Utilities.BFS(pairs.get(i).get(0), pairs.get(i).get(1));

            System.out.println("Color: " + (char)i);
            for (Node node : path)
                grid.setValueAt(node.getX(), node.getY(), i);

            grid.display();
            System.out.println();

        }
    }
}
