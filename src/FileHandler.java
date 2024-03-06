// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
// import java.io.File;
// import java.io.FileReader;
// import java.io.FileNotFoundException;
// import java.io.BufferedReader;
import java.io.FileWriter;
// import java.io.Writer;
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
        // Path path = Paths.get(filePath);
        Path path = Paths.get(localDir + "/"+ fileName);
        Scanner fileInput = new Scanner(path);
        fileInput.useDelimiter(" |\\n"); // delimited by space or new line

        ArrayList<String> plainText = new ArrayList<>();
        while (fileInput.hasNext()){
            String word = fileInput.next();
            plainText.add(word);
        }
        return plainText;
    }
    public void writeFile(String filePath, String toWrite) throws IOException {
        try {
            FileWriter newWrite = new FileWriter(filePath);
            for (int i = 0; i < toWrite.length(); i++) {
                newWrite.write(toWrite.charAt(i));
            }
            System.out.println("Successfully written");
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
            System.out.println("The file  already exists");
        }*/
    }
}