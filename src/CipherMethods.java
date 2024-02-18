public class CipherMethods {
    private String plainText;
    private String cipherText = "";
    int cipherKey;
    private char[] alphaPos = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    //Constructors
    public CipherMethods(int key){
        cipherKey = key;
    }

    //Method Declarations
    public void Encrypt(String text, int key){
        plainText = text;
        cipherKey = key;
        int charPos;
        char plainChar;
        char cipherChar;
        //System.out.println("working so far");
        for (int i=0; i < plainText.length(); i++) {
            plainChar = plainText.charAt(i);
            if (plainChar == ' ') {
                cipherText += " ";
            }
            else {
                for (int j = 0; j < alphaPos.length; j++) {
                    cipherChar = alphaPos[j];
                    if (plainChar == cipherChar) {
                        charPos = j;
                        charPos = (charPos + key) % 26;
                        cipherChar = alphaPos[charPos];
                        cipherText += Character.toString(cipherChar);
                    }
                }
            }
        }
        System.out.println("The new ciphertext is: " + cipherText);
    }
    public void Decrypt(){

    }
    public void compareToDict(){

    }
}
