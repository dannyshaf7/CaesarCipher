import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // https://www.ef.com/wwen/english-resources/english-vocabulary/top-1000-words/
        ArrayList<String> commonWords = new ArrayList<>(); //
        ArrayList<String> encryptedText = new ArrayList<>();
        ArrayList<String> plainText = new ArrayList<>();

        boolean endFlag = false;
        while (!endFlag) {
            System.out.println("Welcome to Team Kida's Shift Cipher! \n");
            Scanner inputObject = new Scanner(System.in);
            System.out.println("1. Encrypt a String \n2. Decrypt a String \n3. " +
                    "Break Cipher \n4. End");
            int menuInput = inputObject.nextInt();

            if (menuInput == 1) {
                String tempWord = " ";
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                int key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    inputObject = new Scanner(System.in);
                    System.out.println("Enter absolute path of text file to be encrypted: ");
                    String filePath = inputObject.nextLine();
                    FileHandler newHandler = new FileHandler();
                    plainText = newHandler.loadFile(filePath);
                    CipherMethods newCipher = new CipherMethods(key);
                    String cipherText = "";
                    for (int i = 0; i < plainText.size(); i++) {
                        tempWord = plainText.get(i);
                        cipherText += newCipher.Encrypt(tempWord, key) + " ";
                    }
                    //System.out.println(cipherText);
                    newHandler.writeText(cipherText, "encrypted");
                } else {
                    System.out.println("error, key out of bounds \n");
                }
            } else if (menuInput == 2) {
                String tempWord = " ";
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                int key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    inputObject = new Scanner(System.in);
                    System.out.println("Enter absolute path of text file to be decrypted: ");
                    String filePath = inputObject.nextLine();
                    FileHandler newHandler = new FileHandler();
                    encryptedText = newHandler.loadFile(filePath);
                    CipherMethods newCipher = new CipherMethods(key);
                    String decryptedText = "";
                    for (int i = 0; i < encryptedText.size(); i++) {
                        tempWord = encryptedText.get(i);
                        decryptedText += newCipher.Decrypt(tempWord, key) + " ";
                    }
                    //System.out.println(decryptedText);
                    newHandler.writeText(decryptedText, "decrypted");
                } else {
                    System.out.println("error, key out of bounds \n");
                }
            } else if (menuInput == 3) {
                CipherMethods newCipher = new CipherMethods();
                FileHandler dictionary = new FileHandler();
                //dictionary.loadFile(commonWords);
                FileHandler cipherText = new FileHandler();
                //cipherText.loadFile(encryptedText);
                newCipher.compareToDict(commonWords, encryptedText);
            } else if (menuInput == 4) {
                endFlag = true;
            } else {
                System.out.println("Error, please try again.");
            }
        }
    }
}