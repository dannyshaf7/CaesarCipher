import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;


public class FileHandler {
    int shiftNumber;
    private int[] keySpace = new int[26];

    //Constructors
    public FileHandler(int shift){
        shiftNumber = shift;
    }

    //Method Declarations
    public void loadFile (ArrayList<String> list) throws IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input the absolute path for the file");
        String filename = userInput.nextLine();
        File file = new File(filename); //Should be absolute path

        Scanner fileInput = null;
        fileInput = new Scanner(file);

        while (fileInput.hasNext()) {
            String word = fileInput.next();
            list.add(word);
            fileInput.nextLine();
        }
    }
    public void writeText(){

    }
}