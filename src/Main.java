import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // First part of main defines variables using arguments passed from command line
        boolean cmdArgsFlag = false;
        String cmdArgs = "";
        String fileToEncrypt = "";
        String fileToDecrypt = "";
        String fileToCrack = "";
        int key = -1;
        String outputFile = "";
        double threshold = -1;
        if (args.length == 4){   // formatting requires 4 valid arguments in order to operate from command line
            if (args[0].equals("-e")){  // option e is encryption
                cmdArgs = args[0];
                fileToEncrypt = args[1];
                key = Integer.parseInt(args[2]);
                outputFile = args[3];
                cmdArgsFlag = true;
            } else if (args[0].equals("-d")) {  // option d is decryption
                cmdArgs = args[0];
                fileToDecrypt = args[1];
                key = Integer.parseInt(args[2]);
                outputFile = args[3];
                cmdArgsFlag = true;
            } else if (args[0].equals("-c")) { // option c is cracking
                cmdArgs = args[0];
                fileToCrack = args[1];
                if (args[2].equals("-t")){
                    threshold = Double.parseDouble(args[3]);
                    cmdArgsFlag = true;
                } else {
                    System.out.println("Input Error: Command Line Arguments. " +
                            "Please try again.\n");
                }
            } else {
                System.out.println("Input Error: Command Line Arguments. " +
                        "Please try again.\n");
            }
        }

        // declaring global variables
        ArrayList<String> commonWords; //
        ArrayList<String> encryptedText;
        ArrayList<String> plainText;
        String dictName = "/dictionary.txt";

        boolean endFlag = false;
        while (!endFlag) {  // end flag is true if user input is 4
            if (cmdArgsFlag){  // cmdArgsFlag is true if the correct values were passed from command line
                if (cmdArgs.equals("-e")){
                    String tempWord;
                    if (0 <= key && key <= 25) {
                        FileHandler newHandler = new FileHandler();
                        try {
                            plainText = newHandler.loadFile(fileToEncrypt); // file is loaded into arraylist
                            CipherMethods newCipher = new CipherMethods(key);

                            StringBuilder cipherText = new StringBuilder();
                            for (int i = 0; i < plainText.size(); i++) {
                                tempWord = plainText.get(i);
                                // encrypted word is appended to string using the stringbuilder function with a space
                                cipherText.append(newCipher.Encrypt(tempWord, key)).append(" ");
                            }
                            newHandler.writeFile(outputFile, cipherText.toString()); // write encrypted string : file
                            System.out.println(cipherText);
                        }
                        catch (Exception e){
                            System.out.println("File not found: \n" + e + "\n");
                        }
                    } else {
                        System.out.println("error, key out of bounds \n");
                    }
                } else if (cmdArgs.equals("-d")) {
                    String tempWord;
                    if (0 <= key && key <= 25) {
                        FileHandler newHandler = new FileHandler();
                        try {
                            encryptedText = newHandler.loadFile(fileToDecrypt); // file is loaded into arraylist
                            CipherMethods newCipher = new CipherMethods(key);

                            StringBuilder decryptedText = new StringBuilder();
                            for (int i = 0; i < encryptedText.size(); i++) {
                                tempWord = encryptedText.get(i);
                                // decrypted word is appended to string using the stringbuilder function with a space
                                decryptedText.append(newCipher.Decrypt(tempWord, key)).append(" ");
                            }
                            newHandler.writeFile(outputFile, decryptedText.toString()); // write encrypted string : file
                            System.out.println(decryptedText);
                        }
                        catch (Exception e){
                            System.out.println("File not found: \n" + e + "\n");
                        }
                    } else {
                        System.out.println("error, key out of bounds \n");
                    }
                } else {
                    FileHandler newHandler = new FileHandler();
                    try {
                        encryptedText = newHandler.loadFile(fileToCrack); // file is loaded into arraylist
                        newHandler = new FileHandler();
                        commonWords = newHandler.loadFile(dictName); // dictionary file is loaded into arraylist
                        CipherMethods newCipher = new CipherMethods();
                        if (0 <= threshold && threshold <= 1) {
                            // passes threshold value, dictionary and ecrypted text arraylists to cracking function
                            newCipher.compareToDict(threshold, commonWords, encryptedText);
                        }
                        else {
                            System.out.println("error, threshold out of bounds \n");
                        }
                    }
                    catch (Exception e){
                        System.out.println("File not found: \n" + e + "\n");
                    }
                }
                cmdArgsFlag = false;
            }
            /* main menu, entered automatically if sufficient command line arguments not entered, entered after the
            initial run of command line processes*/
            System.out.println("Welcome to Team Kida's Shift Cipher! \n");
            Scanner inputObject = new Scanner(System.in); //scanner is created to accept user input from terminal
            System.out.println("1. Encrypt \n2. Decrypt \n3. " +
                    "Crack \n4. Quit\n");
            if (inputObject.hasNextInt()){ // user menu value input must be int to proceed
                int menuInput = inputObject.nextInt();
                if (menuInput == 1) {
                    String tempWord;
                    System.out.println("Enter encryption key (positive integer 0 to 25):");
                    if (inputObject.hasNextInt()){
                        key = inputObject.nextInt();
                        if (0 <= key && key <= 25) {  // Input validation to make sure key is between 0 and 25
                            inputObject = new Scanner(System.in);
                            System.out.println("Enter the name of the text file to be encrypted: ");
                            String fileName = inputObject.nextLine();
                            // Creates FileHandler object to writeText and to loadFile
                            FileHandler newHandler = new FileHandler();
                            try {
                                plainText = newHandler.loadFile(fileName);
                                // Creates CipherMethods to be able to encrypt, decrypt, and break
                                CipherMethods newCipher = new CipherMethods(key);

                                inputObject = new Scanner(System.in);
                                System.out.println("Enter the name of the file to write encrypted text " +
                                        "(if the file exists, it will be overwritten): ");
                                fileName = inputObject.nextLine();

                                StringBuilder cipherText = new StringBuilder();
                                // Iterates through the plaintext to encrypt each char and create string
                                for (int i = 0; i < plainText.size(); i++) {
                                    tempWord = plainText.get(i);
                                    cipherText.append(newCipher.Encrypt(tempWord, key)).append(" ");
                                }
                                newHandler.writeFile(fileName, cipherText.toString());
                                System.out.println(cipherText);
                            }
                            catch (Exception e){
                                System.out.println("File not found: \n" + e + "\n");
                            }
                        } else {
                            System.out.println("error, key out of bounds \n");
                        }
                    } else {
                        System.out.println("Error, invalid input.");
                    }
                } else if (menuInput == 2) {
                    String tempWord;
                    System.out.println("Enter encryption key (positive integer 0 to 25):");
                    if (inputObject.hasNextInt()){
                        key = inputObject.nextInt();
                        if (0 <= key && key <= 25) {
                            inputObject = new Scanner(System.in);
                            System.out.println("Enter the name of the text file to be decrypted: ");
                            String filePath = inputObject.nextLine();
                            // Creates FileHandler object to writeText and to loadFile
                            FileHandler newHandler = new FileHandler();
                            try {
                                encryptedText = newHandler.loadFile(filePath);
                                // Creates CipherMethods to be able to encrypt, decrypt, and break
                                CipherMethods newCipher = new CipherMethods(key);

                                inputObject = new Scanner(System.in);
                                System.out.println("Enter the name of the file to write decrypted text: " +
                                        "(if the file exists, it will be overwritten): ");
                                filePath = inputObject.nextLine();
                                StringBuilder decryptedText = new StringBuilder();
                                // Iterates through the plaintext to encrypt each char to word and create string
                                for (int i = 0; i < encryptedText.size(); i++) {
                                    tempWord = encryptedText.get(i);
                                    decryptedText.append(newCipher.Decrypt(tempWord, key)).append(" ");
                                }
                                newHandler.writeFile(filePath, decryptedText.toString());
                                System.out.println(decryptedText);
                            }
                            catch (Exception e){
                                System.out.println("File not found: \n" + e + "\n");
                            }
                        } else {
                            System.out.println("error, key out of bounds \n");
                        }
                    } else {
                        System.out.println("Error, invalid input.");
                    }
                } else if (menuInput == 3) {
                    inputObject = new Scanner(System.in);
                    System.out.println("Enter the name of the file to be decrypted: ");
                    String fileName = inputObject.nextLine();
                    FileHandler newHandler = new FileHandler();
                    try {
                        encryptedText = newHandler.loadFile(fileName);
                        newHandler = new FileHandler();
                        commonWords = newHandler.loadFile(dictName); //loads dictionary file into arraylist
                        CipherMethods newCipher = new CipherMethods();
                        inputObject = new Scanner(System.in);
                        System.out.println("Enter threshold for decryption matches (for 70% enter 0.70)");
                        threshold = inputObject.nextDouble();
                        if (0 <= threshold && threshold <= 1) {
                            newCipher.compareToDict(threshold, commonWords, encryptedText);
                        }
                        else {
                            System.out.println("error, threshold out of bounds \n");
                        }
                    }
                    catch (Exception e){
                        System.out.println("File not found: \n" + e + "\n");
                    }

                } else if (menuInput == 4) {
                    endFlag = true;
                } else {
                    System.out.println("Error, please try again.");
                }
            }
            else{
                System.out.println("Error, invalid input. Please try again.");
            }
        }
    }
}
