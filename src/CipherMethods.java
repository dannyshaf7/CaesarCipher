import java.lang.Math;
import java.util.ArrayList;

public class CipherMethods {

    // Class Variable Declarations:
    private String plainText;
    //private String cipherText = "";
    private int cipherKey;
    // array of chars - english alphabet - used for caesar cipher position comparisons
    private char[] alphaPos = new char[]{'a','b','c','d','e','f','g','h','i','j','k',
            'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    // Default Constructor
    public CipherMethods() {
        this(0);
    }
    // Parameterized Constructor
    public CipherMethods(int key){
        cipherKey = key;
    }

    //Method Declarations

    /* Encrypt method: takes string plaintext and int key as parameters
        charPos holds the position of the character in the alphaPos array
        plainChar holds the current plaintext char to be compared to the current
            cipherChar ciphertext char
        first for-loop iterates plaintext, adding whitespace to ciphertext when found
        else for-loop iterates through alphaPos array
            'if' looks for a match between plainChar and cipherChar, defines charPos as j
                if cipherKey is less than 0, we use the decryption formulas
                    java does not handle negative number mod 26 like we want
                else we use the standard modulo function
        method returns ciphertext as String
    */
    public String Encrypt(String text, int key){
        plainText = text;
        cipherKey = key;
        int charPos;
        char plainChar;
        char cipherChar;
        String cipherText = "";
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
                            // found to get a double from dividing, must start with double negPos
                            double negPos = charPos + cipherKey;
                            // we find the next lowest multiple of 26 by dividing the negative
                            // position value by the mod value 26, calculating the floor to
                            // reach the needed integer value, and multiplying it back to the
                            // mod value 26; then, subtracting 26*floorVal a negative int from
                            // the negPos will effectively subtract from 26 to get decrypt position
                            int floorVal = (int)Math.floor(negPos / 26);
                            charPos = (int)negPos - (26 * floorVal);
                        }
                        else{
                            // adding the current position and key then mod 26 will give the
                            // correct encrypt position, even when it goes beyond z back to a
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

    /* Decrypt method: takes String ciphertext and int key as parameters
        makes the key negative and calls the encrypt function, passing the ciphertext and
        key (now a negative int) as parameters
        method returns the plaintext as String
     */
    public String Decrypt(String text, int key){
        cipherKey = -1 * key;
        return Encrypt(text, cipherKey);
    }
    public void compareToDict(ArrayList<String> commonWords, ArrayList<String> encryptedText){
        String cipherTemp;
        String commonTemp;
        String plainTemp;
        String matchString = "";
        double matchCount;
        double matchRate;
        for (int k = 0; k < 26; k++){
            matchCount = 0;
            for (int i = 0; i < encryptedText.size(); i++){
                cipherTemp = encryptedText.get(i);
                plainTemp = Decrypt(cipherTemp, k);
                for (int j = 0; j < commonWords.size(); j++){
                    commonTemp = commonWords.get(j);
                    if (commonTemp.equals(plainTemp)){
                        matchCount += 1;
                        /*System.out.println("Found match! key of " + k + ", " +
                                "and cipher " + cipherTemp + ", and match " + commonTemp +
                                " (plaintext " + plainTemp + ")");
                         */
                    }
                }
            }
            matchRate = matchCount / encryptedText.size();
            if (matchRate > 0.5) {
                for (int i = 0; i < encryptedText.size(); i++){
                    plainTemp = Decrypt(encryptedText.get(i), k);
                    matchString += plainTemp + " ";
                }
                matchRate = matchRate * 100;
                System.out.println("Match Rate: %" + matchRate + " for key " + k + "\n");
                System.out.println("Decrypted Text: " + matchString + "\n");
            }
        }
    }
}
