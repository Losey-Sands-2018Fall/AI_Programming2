import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Path implements Iterable<Node>
{
    //Stores the path data
    private ArrayList<Node> data;

    //Construct an instance of path
    //start = the start of this path
    //end = the end of this path
    public Path(Node start, Node end)
    {
        //Constructs the path - provided the start and end nodes
        data = Utilities.BFS(start, end);
        System.out.println("Path::Path(Node,Node) - Completed path construction");
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
    public Grid setPath(Grid grid,Path path,Node current){
        for (Node c : path) {
            Node node = (Node) c;
            c.setCompleted(true);
            grid.setValueAt(node.getX(), node.getY(), current.getValue());
        }
        return grid;
    }
    @Override
    public Iterator<Node> iterator()
    {
        return data.iterator();
    }
}
