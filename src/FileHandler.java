import java.lang.reflect.Array;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileHandler {

    //Constructors
    public FileHandler(){

    }

    //Method Declarations
    public ArrayList<String> loadFile (String filePath)  { //Loads the file using the string path
        try {
            Path path = Paths.get(filePath);
            Scanner fileInput = new Scanner(path);
            fileInput.useDelimiter(" |\\n"); // delimited by space or new line

            ArrayList<String> plainText = new ArrayList<>(); //Loads the file into the array list using the space or new line delimiter
            while (fileInput.hasNext()) {
                String word = fileInput.next();
                plainText.add(word);
            }
            return plainText;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }
    public void writeFile(String fileName, String toWrite) {
        try {
            FileWriter newWrite = new FileWriter(fileName); //Writes to the file name given char by char
            for (int i = 0; i < toWrite.length(); i++) {
                newWrite.write(toWrite.charAt(i));
            }
            //System.out.println("Successfully written");
            newWrite.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        /* if (file.createNewFile()){
            FileWriter writer = new FileWriter(filename);
            for (int i=0; i<string.length(); i++){
                writer.write(string.charAt(i));
            }
        }
        else{
            System.out.println("The file already exists");
        }*/
    }
}