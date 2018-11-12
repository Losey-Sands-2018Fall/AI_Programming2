import java.util.ArrayList;

public class Grid
{
    private int width;
    private  int height;

    private Node[][] nodes;

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

}