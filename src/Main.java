import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
                String tempWord = "";
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                int key = inputObject.nextInt();
                if (0 <= key && key <= 25) { //Input validation to make sure key is between 0 and 25
                    inputObject = new Scanner(System.in);
                    System.out.println("Enter absolute path of text file to be encrypted: (If you're using Windows, use double \\)");
                    String filePath = inputObject.nextLine();
                    FileHandler newHandler = new FileHandler(); //Creates FileHandler object to writeText and to loadFile
                    if (newHandler.loadFile(filePath) != null){
                        plainText = newHandler.loadFile(filePath);
                        CipherMethods newCipher = new CipherMethods(key); //Creates CipherMethods to be able to encrypt, decrypt, and break

                        inputObject = new Scanner(System.in);
                        System.out.println("Enter the name of the file: ");
                        filePath = inputObject.nextLine();

                        String cipherText = "";
                        for (int i = 0; i < plainText.size(); i++) { //Iterates through the plaintext to encrypt each char
                            tempWord = plainText.get(i);
                            cipherText += newCipher.Encrypt(tempWord, key) + " ";
                        }
                        newHandler.writeFile(filePath, cipherText);
                        System.out.println(cipherText);
                    }
                    else {
                        System.out.println("There was an error in retrieving the file to encrypt");
                    }

                } else {
                    System.out.println("error, key out of bounds \n");
                }
            } else if (menuInput == 2) {
                String tempWord = " ";
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                int key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    inputObject = new Scanner(System.in);
                    System.out.println("Enter absolute path of the file to be decrypted: (If you're using Windows, use double \\)");
                    String filePath = inputObject.nextLine();
                    FileHandler newHandler = new FileHandler(); //Creates FileHandler object to writeText and to loadFile
                    if (newHandler.loadFile(filePath) != null) {
                        encryptedText = newHandler.loadFile(filePath);
                        CipherMethods newCipher = new CipherMethods(key); //Creates CipherMethods to be able to encrypt, decrypt, and break

                        inputObject = new Scanner(System.in);
                        System.out.println("Enter the name of the file: ");
                        filePath = inputObject.nextLine();

                        String decryptedText = "";
                        for (int i = 0; i < encryptedText.size(); i++) { //Iterates through the plaintext to encrypt each char
                            tempWord = encryptedText.get(i);
                            decryptedText += newCipher.Decrypt(tempWord, key) + " ";
                        }
                        newHandler.writeFile(filePath, decryptedText);
                        System.out.println(decryptedText);
                    }
                    else{
                        System.out.println("There was an error in reading the file from the absolute path. ");
                    }
                } else {
                    System.out.println("error, key out of bounds \n");
                }
            } else if (menuInput == 3) {
                inputObject = new Scanner(System.in);
                System.out.println("Enter absolute path of the file to be decrypted: (If you're using Windows, use double \\)");
                String filePath = inputObject.nextLine();
                FileHandler newHandler = new FileHandler();
                if (newHandler.loadFile(filePath) != null) {
                    encryptedText = newHandler.loadFile(filePath);
                    newHandler = new FileHandler();
                    //String dictPath = "/Users/danielshafer/Desktop/dictionary.txt";
                    //String dictPath = "C:\\Users\\Kiara\\IdeaProjects\\caeser-cipher\\src\\Dictionary";
                    //Want to ask the user where their dictionary file is:
                    System.out.println("Enter absolute path of the file to be decrypted: (If you're using Windows, use double \\)");
                    String dictPath = inputObject.nextLine();
                    if (newHandler.loadFile(filePath) != null) {
                        commonWords = newHandler.loadFile(dictPath);
                        CipherMethods newCipher = new CipherMethods();
                        inputObject = new Scanner(System.in);
                        System.out.println("Enter threshold for decryption matches (for 70% enter 0.70)");
                        try {
                            double threshold = inputObject.nextDouble();
                            if (0 <= threshold && threshold <= 1) {
                                newCipher.compareToDict(threshold, commonWords, encryptedText);
                            } else {
                                System.out.println("error, threshold out of bounds \n");
                            }
                        } catch (InputMismatchException e) {
                            //e.printStackTrace();
                            System.out.println("\n\n There was an error in getting the threshold for the decryption");
                        }

                    } else {
                    System.out.println("There was an error in retrieving rhe dictionary file with the absolute path given"); }
                }
                else{
                    System.out.println("There was an error in loading your file using the absolute path");
                }

            } else if (menuInput == 4) {
                endFlag = true;
            } else {
                System.out.println("Error, please try again.");
            }
        }
    }
}