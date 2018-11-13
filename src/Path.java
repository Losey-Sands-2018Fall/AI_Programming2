import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Path implements Iterable<Node>
{
    //Stores the path data
    private ArrayList<Node> data;
    // Hash for the paths and their respective color
    public HashMap<Integer,ArrayList<Node>> paths=  new HashMap<Integer, ArrayList<Node>>();

    HashMap<Path,Integer> allPath;

    //Construct an instance of path
    //start = the start of this path
    //end = the end of this path
    public Path(Node start, Node end)
    {
        //Constructs the path - provided the start and end nodes
        data = Utilities.BFS(start, end);
        paths.put(start.getValue(),data);
//        System.out.println("Path::Path(Node,Node) - Completed path construction");
    }
    public void findAllPaths(Grid grid){
        Pairs pairs = new Pairs(grid,false);
        Set<Integer> keys= pairs.getKeys();
        for (Integer key:keys) {
            Path allPaths =new Path(pairs.getStart(key),pairs.getEnd(key));
            if (allPaths!=null) {

            }
        }
    }
    //Does this path contain any nodes from another path
    public Node contains(Path path)
    {
        for(Node checkNode : path)
        {
            for (Node thisNode : this)
            {
                if(checkNode.getX() == thisNode.getX() && checkNode.getY() == thisNode.getY())
                    return thisNode;
            }
        }

        return null;
    }

    public ArrayList<Node> getPath() {
        if(data == null)
            return  null;
        else
            return data;
    }
    public void setPath(Grid grid,ArrayList<Node> path,Node current){
        for (Node c : path) {
            if (c==null){
                return;
            }
            Node node = c;
            current.setCompleted(true);
            grid.setValueAt(node.getX(), node.getY(), current.getValue());
        }
    }
    @Override
    public Iterator<Node> iterator()
    {
        return data.iterator();
    }
}
