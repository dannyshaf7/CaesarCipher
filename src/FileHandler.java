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
    public void loadFile (ArrayList<String> list) {
        try{
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please input the absolute path for the file");
            String filename = userInput.nextLine();
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
            e.printStackTrace();
        }

    }
    public void writeText(String string1) {
        try{
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please input the name of the file");
            String filename = userInput.nextLine();
            File file = new File(filename); //Should be absolute path
            if (file.createNewFile()){
                FileWriter writer = new FileWriter(filename);
                for (int i=0; i<string1.length(); i++){
                    writer.write(string1.charAt(i));
                }
                writer.close();
            }
            else{
                System.out.println("The file already exists");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }






    }
}