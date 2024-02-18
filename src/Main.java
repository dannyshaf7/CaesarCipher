import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean endFlag = false;
        while (!endFlag) {
            System.out.println("Welcome to Team Kida's Shift Cipher! \nEnter exit to end.\n");
            Scanner inputObject = new Scanner(System.in);
            System.out.println("Enter plaintext to be encrypted:");
            String plainText = inputObject.nextLine();
            if (!plainText.equals("exit")) {
                inputObject = new Scanner(System.in);
                System.out.println("Enter encryption key (positive integer 0-25):");
                int key = inputObject.nextInt();
                if (0 <= key && key <= 25) {
                    CipherMethods newCipher = new CipherMethods(key);
                    newCipher.Encrypt(plainText, key);
                } else {
                    System.out.println("error, key outside bounds \n");
                }
            }
            else {
                endFlag = true;
            }
        }




    }
}