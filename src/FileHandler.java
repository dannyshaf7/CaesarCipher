import java.util.ArrayList;
import java.util.NoSuchElementException;
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
    public void loadFile (ArrayList<String> list){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input the absolute path for the file");
        try {
            String filename = userInput.nextLine();
            System.out.println(filename);
            File file = new File(filename); //Should be absolute path

            Scanner fileInput = null;
            fileInput = new Scanner(file);

            while (fileInput.hasNext()) {
                String word = fileInput.next();
                list.add(word);
                if (fileInput.hasNextLine()){
                    fileInput.nextLine();
                }


            }
        }
        catch(IOException e){

            e.printStackTrace();
        }
        catch(NoSuchElementException e){
            System.out.println("No line found when using Scanner");
            e.printStackTrace();
        }
    }
    public void writeText(){

    }
}