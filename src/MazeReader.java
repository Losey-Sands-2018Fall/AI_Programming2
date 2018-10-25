import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class MazeReader {
    public static Character[][] readMazeAsCharArray(String filename){
        try{
            Vector<Character> characters = new Vector<>();
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> lines = new ArrayList<>();
            String line = br.readLine();
            while(line != null){
                lines.add(line);
                line = br.readLine();
            }
            Character[][] mazeArray = new Character[lines.size()][lines.get(0).length()];
            for(int x = 0;x<lines.size();x++){
                char[] lineToChar = lines.get(x).toCharArray();
                for(int y = 0;y<lineToChar.length;y++){
                    mazeArray[x][y] = lineToChar[y];
                }
            }
            return mazeArray;
        }catch(IOException ioe){
            System.out.println("Error reading file " + filename);
            return null;
        }
    }
}
