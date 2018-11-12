import java.util.*;

public class Pairs
{

    //This is a class that defines compare method for the sort method
    private static class sort implements Comparator<Node>
    {
        @Override
        public int compare(Node node1, Node node2)
        {
            int moveCount1 = 0;
            int moveCount2 = 0;

            for (Node neighbor : node1.getNeighbors())
            {
                if(neighbor.getValue() == Utilities.EMPTY_SPACE)
                    moveCount1++;
            }

            for (Node neighbor : node2.getNeighbors())
            {

                if(neighbor.getValue() == Utilities.EMPTY_SPACE)
                    moveCount2++;
            }

            if(moveCount1 > moveCount2)
                return 1;
            else
                return -1;
        }
    }


    private HashMap<Integer, List<Node>> pairs;
    public Pairs(Grid grid, boolean isSorted)
    {
        pairs = new HashMap<>();

        //Iterates the grid and looks for the pairs
        for (int y = 0; y < grid.getHeight(); y++)
        {
            for (int x = 0; x < grid.getWidth(); x++)
            {
                Node node = grid.getNodeAt(x, y);
                int value = node.getValue();

                if(value == Utilities.EMPTY_SPACE)
                    continue;

                if(pairs.containsKey(value) == false)
                    pairs.put(value, new ArrayList<>());

                pairs.get(value).add(node);
            }

            if(isSorted)
            {
                for (Integer key : getKeys())
                {
                    //This is where I sort
                    //Looking at this now, it only sorts the start and end nodes. So start is the one
                    //with less spaces to move
                    //However, the order of the pairs is the same as they are read in...
                    getValues(key).sort(new sort());
                }
            }
        }
    }
    private List wallkeys = new ArrayList();
    public List wallPath(Grid grid){
        for (Integer key : getKeys()){
            if(getStart(key).getY()==grid.getHeight()-1||getStart(key).getY()==0||getStart(key).getX()==grid.getWidth()-1||getStart(key).getX()==0){
                if(getEnd(key).getY()==grid.getHeight()-1||getEnd(key).getY()==0||getEnd(key).getX()==grid.getWidth()-1||getEnd(key).getX()==0){
                    wallkeys.add(key);
                }
            }
        }

        return wallkeys;
    }

    public Node getStart(int key)
    {
        return pairs.get(key).get(0);
    }

    public Node getEnd(int key)
    {
        return pairs.get(key).get(1);
    }

    public Set<Integer> getKeys()
    {
        return pairs.keySet();
    }

    private ArrayList<Node> getValues(int key)
    {
        return (ArrayList<Node>) pairs.get(key);
    }

    public Integer getKey(Set<Integer> set,Integer colorValue){
        for (int i=0;i<set.size();i++){
            if(set.contains(colorValue))
                return colorValue;
        }
        return 0;
    }

}
