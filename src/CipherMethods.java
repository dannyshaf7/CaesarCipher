import java.lang.Math;

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
    public String Encrypt(String text, int key){
        plainText = text;
        cipherKey = key;
        int charPos;
        char plainChar;
        char cipherChar;
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
                        if (cipherKey < 0){
                            double negPos = charPos + cipherKey;  // 2 - 5 = -3
                            int floorVal = (int)Math.floor(negPos / 26); // -3/26 = -0.1153846
                            charPos = (int)negPos - (26 * floorVal); // -3 - (-26) = 23
                        }
                        else{
                            charPos = (charPos + key) % 26;
                        }
                        cipherChar = alphaPos[charPos];
                        cipherText += Character.toString(cipherChar);
                    }
                }
            }
        }
        return cipherText;
    }
    public String Decrypt(String text, int key){
        cipherKey = -1 * key;
        return Encrypt(text, cipherKey);
    }
    public void compareToDict(){

    }
}
