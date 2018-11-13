import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main
{
    public static void main(String args[]) throws IOException
    {
        //
        //Initialize data
        //
        System.out.println("Initializing...");
        StopWatch watch = new StopWatch();
        ArrayList<String> mapData = Utilities.readFile("test2.txt");
        ArrayList<Integer> colors = Utilities.getcolors(mapData);   //TODO: may be useful?
        Grid solvedState = new Grid(mapData);
//        Testing
        Pairs pairs = new Pairs(solvedState,false);
        Set<Integer> keys=pairs.getKeys();
        List<Integer> list=pairs.wallPath(solvedState);
        int key = list.get(0);
          Path path=new Path(pairs.getStart(key),pairs.getEnd(key));
//        path.setPath(solvedState,path,pairs.getStart(key));
          path.findAllPaths(solvedState);
        System.out.println(path.allPath.size());
        solvedState.display();

        //A more efficient way to determine if solves...however, may make the code more difficult to understand...
        int filledCount = 0;

        System.out.println("Solving...");
        watch.start();

        //while (filledCount != grid.getHeight() * grid.getWidth())
        while (isFilled(solvedState) == false)
        {
            //filledCount = 0;

            //
            //iterate all nodes
            //
            /*
                TODO: optimize this, so we only traverse nodes that are movable
                eg. an empty space does not make any moves, so why visit it?
            */
            for (int y = 0; y < solvedState.getHeight(); y++)
            {
                for (int x = 0; x < solvedState.getWidth(); x++)
                {
                    Node currentNode = solvedState.getNodeAt(x, y);
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
                        //if neighbor is empty space, possible move
                        if(node.getValue() == Utilities.EMPTY_SPACE)
                        {
                            possibleNumberOfMoves++;
                            movement = node;
                        }
                    }

                    //if only one possible move, then force the move
                    if(possibleNumberOfMoves == 1) {
                        forceMove(solvedState, currentNode, movement);
                        solvedState.display();
                    }
                    //TODO: else we need to perform another method of determining a move
//                    else if(possibleNumberOfMoves!=1){
//                            Path path= new Path(currentNode,pairs.getEnd(currentNode.getValue()));
                            ArrayList<Node> test=path.getPath();
//                            path.setPath(solvedState,test,currentNode);
                        solvedState.display();
//                    }
                }

            }
        }

        watch.stop();
        System.out.println("Solved in:");
        watch.displayElapsedTime();
    }

    private static void forceMove(Grid grid, Node currentNode, Node movement)
    {
        //Update the grid
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

        //Display stuff
        System.out.println();
        System.out.println((char)currentNode.getValue());
        grid.display();
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
}
