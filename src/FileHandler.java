import java.lang.reflect.Array;
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
    public ArrayList<String> loadFile (String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Scanner fileInput = new Scanner(path);
        fileInput.useDelimiter(" ");

        ArrayList<String> plainText = new ArrayList<>();
        while (fileInput.hasNext()){
            String word = fileInput.next();
            plainText.add(word);
        }
        return plainText;
    }
    public void writeText(String string, String function, int key) throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input the name of the file to output the "+function+" file to: ");
        String filename = userInput.nextLine();
        File file = new File(filename); //Should be absolute path
        if (file.createNewFile()){
            FileWriter writer = new FileWriter(filename);
            writer.write("The key is "+ key+"\n");
            for (int i=0; i<string.length(); i++){
                writer.write(string.charAt(i));
            }
            writer.close();
        }
        else{
            System.out.println("The file already exists");
        }
    }
}