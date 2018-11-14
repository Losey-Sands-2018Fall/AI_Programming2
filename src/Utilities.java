//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.*;
//
//public class Utilities
//{
//    //Gives us global control for data assignment
//    //Changing these will have effects over the program beyond this class.
//    //pros and cons, but if we wanted to change what an empty space is, we can do so here, and everything will
//    //get updated. The FILE_EMPTY_SPACE, should not be changed, unless the definition of an empty space in the file changes.
//    private static final int FILE_EMPTY_SPACE = '_';
//    public static final int EMPTY_SPACE = ' ';
//    public static final int TRAVERSED_NODE = '.';
//    public static final int BLOCK = '!';
//
//    //returns an array of strings from the chosen file
//    public static ArrayList<String> readFile(String path) throws FileNotFoundException
//    {
//        File file = new File(path);
//        Scanner scanner = new Scanner(file);
//
//        ArrayList<String> result = new ArrayList<>();
//
//        while (scanner.hasNextLine())
//        {
//            String line = scanner.nextLine();
//            line = line.replace((char)FILE_EMPTY_SPACE, (char)EMPTY_SPACE);
//
//            if(line.length() == 0)
//                continue;
//
//            result.add(line);
//        }
//
//        return result;
//    }
//
//
//    //Just added this, so it will store the keys, without needing to do weird stuff, like in main, the for look that breaks after one iteration
//    //Still working on it so we can order it appropriately.
//    public static ArrayList<Integer> getcolors(ArrayList<String> fileData)
//    {
//        ArrayList<Integer> result = new ArrayList<>();
//
//        for (String line : fileData)
//        {
//            for(int character : line.toCharArray())
//            {
//                if(character == EMPTY_SPACE)
//                    continue;
//
//                if(result.contains(character))
//                    continue;
//                else
//                    result.add(character);
//            }
//        }
//
//        return result;
//    }
//
//    //Reterns a list of nodes that are colored
//    public static ArrayList<Node> getPoints(Grid grid)
//    {
//        ArrayList<Node> result = new ArrayList<>();
//
//        for (int y = 0; y < grid.getHeight(); y++)
//        {
//            for (int x = 0; x < grid.getWidth(); x++)
//            {
//                Node node = grid.getNodeAt(x, y);
//
//                if(node.getValue() == EMPTY_SPACE)
//                    continue;
//
//                result.add(node);
//            }
//        }
//        return result;
//    }
//    public static ArrayList<Node> BFS(Node start, Node end)
//    {
//        HashSet<Node> closedSet = new HashSet<>();
//        Queue<Node> openSet = new LinkedList<>();
//        openSet.add(start);
//
//        System.out.println("Utilities::BFS(Node,Node) - Entering the loop");
//        while (openSet.isEmpty() == false)
//        {
//            Node currentNode = openSet.poll();
//
//            if (closedSet.contains(currentNode))
//                continue;
//
//            if(currentNode == end)
//                return backtrackPath(currentNode);
//
////            System.out.println("Utilities::BFS(Node,Node) - Passed initial checks");
//            closedSet.add(currentNode);
//            for(Node neighbor : currentNode.getNeighbors())
//            {
////                System.out.println("Utilities::BFS(Node,Node) - Has Neighbors");
//                if(closedSet.contains(neighbor) == false)
//                {
//                    if(EMPTY_SPACE == neighbor.getValue() || end.getValue() == neighbor.getValue())
//                    {
//                        if (neighbor.isChecked()!=true) {
//                            openSet.add(neighbor);
//                            if (openSet.size() == 1) {
//                                neighbor.setChecked(true);
//                            }
//                            neighbor.setParent(currentNode);
//                        }
//
//                    }
//                }
//            }
//        }
//        return null;
//    }
//    private static ArrayList<Node> backtrackPath(Node start) {
//
//        ArrayList<Node> path = new ArrayList<>();
//        Node current = start;
//
//        while (current != null)
//        {
//            path.add(current);
//            current = current.getParent();
//        }
//        return path;
//    }
//
//}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utilities
{
    //Gives us global control for data assignment
    //Changing these will have effects over the program beyond this class.
    //pros and cons, but if we wanted to change what an empty space is, we can do so here, and everything will
    //get updated. The FILE_EMPTY_SPACE, should not be changed, unless the definition of an empty space in the file changes.
    private static final int FILE_EMPTY_SPACE = '_';
    public static final int EMPTY_SPACE = ' ';
    public static final int TRAVERSED_NODE = '.';
    public static final int BLOCK = '!';

    //returns an array of strings from the chosen file
    public static ArrayList<String> readFile(String path) throws FileNotFoundException
    {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        ArrayList<String> result = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            line = line.replace((char)FILE_EMPTY_SPACE, (char)EMPTY_SPACE);

            if(line.length() == 0)
                continue;

            result.add(line);
        }

        return result;
    }


    //Just added this, so it will store the keys, without needing to do weird stuff, like in main, the for look that breaks after one iteration
    //Still working on it so we can order it appropriately.
    public static ArrayList<Integer> getcolors(ArrayList<String> fileData)
    {
        ArrayList<Integer> result = new ArrayList<>();

        for (String line : fileData)
        {
            for(int character : line.toCharArray())
            {
                if(character == EMPTY_SPACE)
                    continue;

                if(result.contains(character))
                    continue;
                else
                    result.add(character);
            }
        }

        return result;
    }




    //Reterns a list of nodes that are colored
    public static HashMap<Integer, ArrayList<Node>> getPairs(Grid grid)
    {
        HashMap<Integer, ArrayList<Node>> result = new HashMap<>();
        for (int y = 0; y < grid.getHeight(); y++)
        {
            for (int x = 0; x < grid.getWidth(); x++)
            {
                Node node = grid.getNodeAt(x, y);

                if(node.getValue() == EMPTY_SPACE)
                    continue;


                if(result.containsKey(node.getValue()) == false)
                    result.put(node.getValue(), new ArrayList<>());

                result.get(node.getValue()).add(node);
            }
        }
        return result;
    }






    public static ArrayList<Node> BFS(Node start, Node end)
    {
        HashSet<Node> closedSet = new HashSet<>();
        Queue<Node> openSet = new LinkedList<>();
        openSet.add(start);

        while (openSet.isEmpty() == false)
        {
            Node currentNode = openSet.poll();

            if (closedSet.contains(currentNode))
                continue;

            if(currentNode == end)
                return backTrack(currentNode);

            closedSet.add(currentNode);
            for(Node neighbor : currentNode.getNeighbors())
            {
                if(closedSet.contains(neighbor) == false)
                {
                    if(EMPTY_SPACE == neighbor.getValue()|| neighbor.getValue() == end.getValue())
                    {
                        openSet.add(neighbor);
                        neighbor.setParent(currentNode);
                    }
                }
            }
        }
        return null;
    }

    private static ArrayList<Node> backTrack(Node end)
    {
        ArrayList<Node> result = new ArrayList<>();
        Node current = end;

        while (current != null)
        {
            result.add(current);
            current = current.getParent();
        }

        return result;
    }

}
