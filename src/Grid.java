import java.util.ArrayList;
import java.util.HashMap;

public class Grid
{
    private int width;
    private  int height;

    private Node[][] nodes;

    private HashMap<Integer, ArrayList<Node>> pairs;

    public Grid(int width, int height)
    {
        this.width = width;
        this.height = height;
        initializeNodes();
    }

    public Grid(ArrayList<String> fileData)
    {
        this.height = fileData.size();
        this.width = fileData.get(0).length();
        initializeNodes(fileData);
        pairs = Utilities.getPairs(this);
    }

    public Grid(Grid grid)
    {
        this.height = grid.height;
        this.width = grid.width;

        nodes = new Node[width][height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int value = grid.getValueAt(x, y);
                nodes[x][y] = new Node(x, y, value);
            }
        }

        initializeNeighbors();
        pairs = Utilities.getPairs(this);
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    private void initializeNodes(ArrayList<String> fileData)
    {
        //initialize local variables
        nodes = new Node[width][height];

        //
        //setup the nodes
        //
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int value = fileData.get(y).charAt(x);
                nodes[x][y] = new Node(x, y, value);
            }
        }

        initializeNeighbors();
    }

    private void initializeNeighbors()
    {
        //
        //setup neighbors
        //
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                setNeighbor(nodes[x][y]);
            }
        }
    }

    private void initializeNodes()
    {
        //initialize local variables
        nodes = new Node[width][height];

        //
        //setup the nodes
        //
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                nodes[x][y] = new Node(x, y, Utilities.EMPTY_SPACE);
            }
        }

        initializeNeighbors();
    }

    private void setNeighbor(Node currentNode)
    {
        //initialize local variables
        int currentX = currentNode.getX();
        int currentY = currentNode.getY();

        //left
        if(isBounded(currentX - 1, currentY))
            currentNode.addNeighbor(nodes[currentX - 1][currentY]);
        //right
        if(isBounded(currentX + 1, currentY))
            currentNode.addNeighbor(nodes[currentX + 1][currentY]);
        //up
        if(isBounded(currentX, currentY - 1))
            currentNode.addNeighbor(nodes[currentX][currentY - 1]);
        //down
        if(isBounded(currentX, currentY + 1))
            currentNode.addNeighbor(nodes[currentX][currentY + 1]);
    }


    public void setValueAt(int x, int y, int value)
    {
        if(isBounded(x, y))
            nodes[x][y].setValue(value);
        else
            throw new IndexOutOfBoundsException("Grid::setValueAt(int, int, int)");
    }

    public int getValueAt(int x, int y)
    {
        if(isBounded(x, y))
            return nodes[x][y].getValue();
        else
            throw new IndexOutOfBoundsException("Grid::getValueAt(int, int)");
    }

    public boolean isBounded(int x, int y)
    {
        if(x < 0 || x >= width)
            return false;

        if(y < 0 || y >= height)
            return false;

        return true;
    }

    public Node getNodeAt(int x, int y)
    {
        if(isBounded(x, y))
            return nodes[x][y];
        else
            throw new IndexOutOfBoundsException("Grid::getNodeAt(int, int)");
    }

    public boolean isCorner(Node node)
    {
        int x = node.getX();
        int y = node.getY();

        //upperRight
        if(isBounded(x + 1, y) == false && isBounded(x, y - 1) == false)
            return true;
        //lowerRight
        if(isBounded(x + 1, y) == false && isBounded(x, y + 1) == false)
            return true;

        //upperLeft
        if(isBounded(x - 1, y) == false && isBounded(x, y - 1) == false)
            return true;
        //lowerLeft
        if(isBounded(x - 1, y) == false && isBounded(x, y + 1) == false)
            return true;

        return false;
    }

    public void display()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                System.out.print(" " + (char)nodes[x][y].getValue());
            }

            System.out.println();
        }
    }

    public void displayTest()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                String value = Integer.toString(nodes[x][y].getValue());

                if(value.length() == 1)
                    System.out.print("  " + value);
                else
                    System.out.print(" " + value);

            }

            System.out.println();
        }
    }

    public void fillRegions()
    {
        //copy the current state
        Grid currentState = new Grid(this);

        //change groupGrid to have 0s where there are empty spaces
        int index = 1;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int thisValue = getValueAt(x, y);

                //number all nun empty spaces
                if(thisValue == Utilities.EMPTY_SPACE)
                    currentState.setValueAt(x, y, index++);
                    //else set the empty space to 0
                else
                    currentState.setValueAt(x, y, 0);
            }
        }

        //while the previousState does not equal the currentState
        //Find all groups/regions
        while (true)
        {
            Grid previousState = new Grid(currentState);

            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    //get the node at (x,y)
                    Node currentNode = currentState.getNodeAt(x, y);

                    //if the node value is 0 - try the next node
                    if(currentNode.getValue() == 0)
                        continue;

                    //look at all the neighbors of the current node
                    for (Node neighbor : currentNode.getNeighbors())
                    {
                        //if the neighbor is 0 - try the next node
                        if(neighbor.getValue() == 0)
                            continue;
                        //if the current node's value is less than its neighbor - set its value to it's neighbor
                        if(currentNode.getValue() < neighbor.getValue())
                            currentNode.setValue(neighbor.getValue());
                    }
                }
            }
            //if the currentState has not been changes - break out of loop
            if(previousState.equal(currentState))
                break;
        }

        //populate a hashmap with all the groups
        HashMap<Integer, ArrayList<Node>> groups = new HashMap<>();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                //get value of node at (x,y)
                int value = currentState.getValueAt(x, y);
                //if the value is 0 - try next node
                if(value == 0)
                    continue;

                //if the hashmap does not contain a key for this value - initialize it
                if(groups.containsKey(value) == false)
                    groups.put(value, new ArrayList<>());
                //add the node at (x,y)
                groups.get(value).add(currentState.getNodeAt(x, y));
            }
        }


        //get all nodes that are neighboring the nodes in groups
        HashMap<Integer, ArrayList<Node>> connectedNodes = new HashMap<>();
        for (Integer key : groups.keySet())
        {
            //get the group at the current key
            ArrayList<Node> group = groups.get(key.intValue());

            //look at all the nodes in the group
            for (Node node : group)
            {
                //look at the current node's neighbors in the group
                for (Node neighbor : node.getNeighbors())
                {
                    //if the neighbor is not 0 - try next neighbor
                    if (neighbor.getValue() != 0)
                        continue;


                    if(connectedNodes.containsKey(key) == false)
                        connectedNodes.put(key, new ArrayList<>());

                    if(connectedNodes.get(key).contains(neighbor))
                        connectedNodes.get(key).remove(neighbor);

                    connectedNodes.get(key).add(neighbor);
                }
            }
        }


        for (Integer nodeID : connectedNodes.keySet())
        {
            ArrayList<Node> group = connectedNodes.get(nodeID);

            if(group.size() <= 1)
                continue;

            Node start = group.get(0);
            Node end = group.get(group.size() - 1);

            if(getValueAt(start.getX(), start.getY()) == getValueAt(end.getX(), end.getY()))
            {
                getNodeAt(start.getX(), start.getY()).setCompleted(true);
                getNodeAt(end.getX(), end.getY()).setCompleted(true);

                for (Node node : groups.get(nodeID))
                {
                    getNodeAt(node.getX(), node.getY()).setCompleted(true);
                    setValueAt(node.getX(), node.getY(), getValueAt(start.getX(), start.getY()));
                }
            }
        }
    }

    public boolean equal(Grid grid)
    {
        for (int y = 0; y < width; y++)
        {
            for (int x = 0; x < width; x++)
            {

                int thisGridValue = getValueAt(x, y);
                int testingGridValue = grid.getValueAt(x, y);

                if(thisGridValue != testingGridValue)
                    return false;

            }
        }

        return true;
    }

}