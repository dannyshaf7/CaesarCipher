// import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        /*for (int i = 0; i < args.length; i++){
            System.out.println("args from command line: " + args[i]);
        }*/


        boolean cmdArgsFlag = false;
        String cmdArgs = "";
        String fileToEncrypt = "";
        String fileToDecrypt = "";
        String fileToCrack = "";
        int key = -1;
        String outputFile = "";
        double threshold = -1;
        if (args.length == 4){
            if (args[0].equals("-e")){
                cmdArgs = args[0];
                fileToEncrypt = args[1];
                key = Integer.parseInt(args[2]);
                outputFile = args[3];
                cmdArgsFlag = true;
            } else if (args[0].equals("-d")) {
                cmdArgs = args[0];
                fileToDecrypt = args[1];
                key = Integer.parseInt(args[2]);
                outputFile = args[3];
                cmdArgsFlag = true;
            } else if (args[0].equals("-c")) {
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


        ArrayList<String> commonWords; //
        ArrayList<String> encryptedText;
        ArrayList<String> plainText;
        String localDir = System.getProperty("user.dir");
        String dictPath = localDir + "/dictionary.txt";

        boolean endFlag = false;
        while (!endFlag) {
            if (cmdArgsFlag){
                if (cmdArgs.equals("-e")){
                    String tempWord;
                    if (0 <= key && key <= 25) {
                        FileHandler newHandler = new FileHandler();
                        plainText = newHandler.loadFile(fileToEncrypt);
                        CipherMethods newCipher = new CipherMethods(key);

                        StringBuilder cipherText = new StringBuilder();
                        for (int i = 0; i < plainText.size(); i++) {
                            tempWord = plainText.get(i);
                            cipherText.append(newCipher.Encrypt(tempWord, key)).append(" ");
                        }
                        newHandler.writeFile(outputFile, cipherText.toString());
                        System.out.println(cipherText);
                    } else {
                        System.out.println("error, key out of bounds \n");
                    }
                } else if (cmdArgs.equals("-d")) {
                    String tempWord;
                    if (0 <= key && key <= 25) {
                        FileHandler newHandler = new FileHandler();
                        encryptedText = newHandler.loadFile(fileToDecrypt);
                        CipherMethods newCipher = new CipherMethods(key);
                        
                        StringBuilder decryptedText = new StringBuilder();
                        for (int i = 0; i < encryptedText.size(); i++) {
                            tempWord = encryptedText.get(i);
                            decryptedText.append(newCipher.Decrypt(tempWord, key)).append(" ");
                        }
                        newHandler.writeFile(outputFile, decryptedText.toString());
                        System.out.println(decryptedText);
                    } else {
                        System.out.println("error, key out of bounds \n");
                    }
                } else {
                    FileHandler newHandler = new FileHandler();
                    encryptedText = newHandler.loadFile(fileToCrack);
                    newHandler = new FileHandler();
                    commonWords = newHandler.loadFile(dictPath);
                    CipherMethods newCipher = new CipherMethods();
                    if (0 <= threshold && threshold <= 1) {
                        newCipher.compareToDict(threshold, commonWords, encryptedText);
                    }
                    else {
                        System.out.println("error, threshold out of bounds \n");
                    }
                }
                cmdArgsFlag = false;
            }
            System.out.println("Welcome to Team Kida's Shift Cipher! \n");
            Scanner inputObject = new Scanner(System.in);
            System.out.println("1. Encrypt \n2. Decrypt \n3. " +
                    "Crack \n4. Quit\n");
            int menuInput = inputObject.nextInt();

            if (menuInput == 1) {
                String tempWord;
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    inputObject = new Scanner(System.in);
                    System.out.println("Enter the name of the text file to be encrypted " +
                            "(must end in .txt): ");
                    String filePath = inputObject.nextLine();
                    FileHandler newHandler = new FileHandler();
                    try {
                        plainText = newHandler.loadFile(filePath);
                        CipherMethods newCipher = new CipherMethods(key);

                        inputObject = new Scanner(System.in);
                        System.out.println("Enter the name of the file to write encrypted text " +
                                "(if the file exists, it will be overwritten): ");
                        filePath = inputObject.nextLine();

                        StringBuilder cipherText = new StringBuilder();
                        for (int i = 0; i < plainText.size(); i++) {
                            tempWord = plainText.get(i);
                            cipherText.append(newCipher.Encrypt(tempWord, key)).append(" ");
                        }
                        newHandler.writeFile(filePath, cipherText.toString());
                        System.out.println(cipherText);
                    }
                    catch (Exception e){
                        System.out.println("File not found: \n" + e + "\n");
                    }
                } else {
                    System.out.println("error, key out of bounds \n");
                }
            } else if (menuInput == 2) {
                String tempWord;
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    inputObject = new Scanner(System.in);
                    System.out.println("Enter absolute path of the file to be decrypted: ");
                    String filePath = inputObject.nextLine();
                    FileHandler newHandler = new FileHandler();
                    encryptedText = newHandler.loadFile(filePath);
                    CipherMethods newCipher = new CipherMethods(key);

                    inputObject = new Scanner(System.in);
                    System.out.println("Enter absolute path of the file to write decrypted text: ");
                    filePath = inputObject.nextLine();
                    StringBuilder decryptedText = new StringBuilder();
                    for (int i = 0; i < encryptedText.size(); i++) {
                        tempWord = encryptedText.get(i);
                        decryptedText.append(newCipher.Decrypt(tempWord, key)).append(" ");
                    }
                    newHandler.writeFile(filePath, decryptedText.toString());
                    System.out.println(decryptedText);
                } else {
                    System.out.println("error, key out of bounds \n");
                }
            } else if (menuInput == 3) {
                inputObject = new Scanner(System.in);
                System.out.println("Enter absolute path of the file to be decrypted: ");
                String filePath = inputObject.nextLine();
                FileHandler newHandler = new FileHandler();
                encryptedText = newHandler.loadFile(filePath);
                newHandler = new FileHandler();
                commonWords = newHandler.loadFile(dictPath);
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
            } else if (menuInput == 4) {
                endFlag = true;
            } else {
                System.out.println("Error, please try again.");
            }
        }
    }
}
