import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileHandler {

    // gets current working directory to use for relative path
    String localDir = System.getProperty("user.dir");

    //Constructor
    public FileHandler(){}


    //Method Declarations
    public ArrayList<String> loadFile (String fileName) throws IOException {
        Path path = Paths.get(localDir + "/"+ fileName); // Loads the file using the string path
        Scanner fileInput = new Scanner(path);
        fileInput.useDelimiter(" |\\n"); // delimited by space or new line

        // Loads the file into the array list using the space or new line delimiter
        ArrayList<String> plainText = new ArrayList<>();
        while (fileInput.hasNext()){
            String word = fileInput.next();
            plainText.add(word);
        }
        return plainText;
    }
    public void writeFile(String filePath, String toWrite) throws IOException {
        FileWriter newWrite = new FileWriter(filePath); //Writes to the file name given char by char
        for (int i = 0; i < toWrite.length(); i++) {
            newWrite.write(toWrite.charAt(i));
        }
        System.out.println("Successfully written");
        newWrite.close();
    }
}