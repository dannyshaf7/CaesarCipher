import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean endFlag = false;
        while (!endFlag) {
            System.out.println("Welcome to Team Kida's Shift Cipher! \nEnter exit to end.\n");
            Scanner inputObject = new Scanner(System.in);
            System.out.println("1. Encrypt a String \n2. Decrypt a String \n3. End");
            int menuInput = inputObject.nextInt();
            if (menuInput == 1) {
                inputObject = new Scanner(System.in);
                System.out.println("Enter plaintext to be encrypted:");
                String plainText = inputObject.nextLine();
                inputObject = new Scanner(System.in);
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                int key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    CipherMethods newCipher = new CipherMethods(key);
                    String cipherText = newCipher.Encrypt(plainText, key);
                    System.out.println("Encrypted ciphertext: " + cipherText);
                } else {
                    System.out.println("error, key outside bounds \n");
                }
            } else if (menuInput == 2) {
                inputObject = new Scanner(System.in);
                System.out.println("Enter ciphertext to be decrypted:");
                String cipherText = inputObject.nextLine();
                inputObject = new Scanner(System.in);
                System.out.println("Enter encryption key (positive integer 0 to 25):");
                int key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    CipherMethods newCipher = new CipherMethods(key);
                    String plainText = newCipher.Decrypt(cipherText, key);
                    System.out.println("Decrypted plaintext: " + plainText);
                } else {
                    System.out.println("error, key outside bounds \n");
                }
            } else if (menuInput == 3) {
                endFlag = true;
            } else {
                System.out.println("Error, please try again.");
            }
        }

    }
}
