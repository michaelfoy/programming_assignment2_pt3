import java.lang.StringBuilder;

/**
 * Class to encrypt and decrypt text using a Vigenere Cipher.
 * Includes methods for encryption, decryption and to print data from the class.
 * 
 * @author michaelfoy
 * @version 29.03.2016
 */

public class VigenereCipher
{
    private static final int NUM_CHARS = 26;
    private static final int MIN_CHAR = 65;
    private static final int MAX_CHAR = 90;
    private static char[][] vigenereTable = new char[NUM_CHARS][NUM_CHARS];

    
    /**
     * Constructs a new VigenereCipher object.
     */
    public VigenereCipher()
    {
        generateVigenereTable();
    }

    /**
     * Generates the Vigenere Table.
     */
    public static void generateVigenereTable()
    {   
        for(int i = 0; i < NUM_CHARS; i++)
        {
            for(int j = 0; j < NUM_CHARS; j++)
            {               
                vigenereTable[i][j] = (char)(((i + j) % NUM_CHARS) + MIN_CHAR);
            }
        }

    }

    /**
     * Use the keyword to generate a key.
     * Key length is the same as the length of the plain text.
     * The key comprises repeating keyword. 
     * The last keyword in the key is truncated if necessary.
     *
     * @param keyLength The number of characters in the key.
     * @param plainText The plain text for which a key will be generated.
     * @return The key for use in encrypting a message whose length matches the key length.
     */
    public String generateKey(String keyword, int keyLength)
    {
        String checkedKey = stringCheck(keyword);
        StringBuilder key = new StringBuilder();
        
        for(int i = 0; i < keyLength; i++)
        {
            char j = (char)(checkedKey.toCharArray()[i % checkedKey.length()]);
            key.append(j);
        }
        return key.toString();
    }

    /**
     * Encrypts plain text (message text) under the key using the Vigenere Table.
     * 
     * @param plainText The plain text (message text) to be encrypted
     * @param key The key under which the plain text is encrypted
     * @return The cipher text created by encrypting the plaintext under the generated key.
     */
    public String encrypt(String key, String plainText)
    {
        String checkedText = stringCheck(plainText);
        String checkedKey = stringCheck(key);
        
        StringBuilder encrypt = new StringBuilder();
        
        if(!stringLength(checkedText, checkedKey))
        {
            return "Key is not correct length";
        }
        else
        {
           for(int i = 0; i < checkedText.length(); i++)
           {
               char cipherChar = (char)(checkedText.charAt(i) + checkedKey.charAt(i) - MIN_CHAR);
               
               if(cipherChar > MAX_CHAR)
               {
                   cipherChar = (char)(cipherChar - NUM_CHARS);
               }
               encrypt.append(cipherChar);
           }
        return encrypt.toString();
        }
   }

    /**
     * Decrypts cipher text under the key using the Vigenere Table.
     * 
     * @param key The key under which the cipher text is deccrypted
     * @param cipherText The cipher text to be decrypted.
     * @return The plain text obtained by decrypting the cipher text under the generated key.
     */

    public String decrypt(String key, String cipherText)
    {
        String checkedText = stringCheck(cipherText);
        String checkedKey = stringCheck(key);
        
        StringBuilder decrypt = new StringBuilder();
        
        if(!stringLength(checkedText, checkedKey))
        {
            return "Key is not correct length";
        }
        else
        {
            for(int i = 0; i < checkedText.length(); i++)
            {
                char textChar = (char)(checkedText.charAt(i) - checkedKey.charAt(i) + MIN_CHAR);
                
                if(textChar < MIN_CHAR)
                {
                    textChar = (char)(textChar + NUM_CHARS);
                }
                decrypt.append(textChar);
            }
            return decrypt.toString();
        }
    }

    /**
     * Print the vigenere table.
     * 
     */
    public void printVigenereTable()
    {
        for(int i = 0; i < NUM_CHARS; i++)
        {
            for(int j = 0; j < NUM_CHARS; j++)
            {
                System.out.printf("%2s", vigenereTable[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Print the key.
     * 
     * @param key The key used for encryption and decryption.
     */
    public void printKey(String key)
    {
        System.out.println(stringCheck(key));
    }

    /**
     * Print the plain text (message text).
     * 
     * @param plainText The plain text (message text).
     */
    public void printMessage(String plainText)
    {
        System.out.println(stringCheck(plainText));
    }

    /**
     * Print the cipher text.
     * 
     * @param cipherText The encrypted message.
     */
    public void printCipher(String cipherText)
    {
        System.out.println(stringCheck(cipherText));
    }

    /**
     * Test method by: 
     *  Generating and printing Vigenere Table,
     *  Generating the key using the keyword,
     *  Encrypting a message (plain text),
     *  Decrypting the cipher text.
     *  Printing the table, key, message, ciphertext & decrypted ciphertext.
     */
    public void testVigenere()
    {

        generateVigenereTable();

        String messageText = "MICHIGANTECHNOLOGICALUNIVERSITY";
        String key = generateKey("HOUGHTON", messageText.length());
        String cipherText = encrypt(key, messageText);
        String decrypted = decrypt(key, cipherText);

        printVigenereTable();
        printKey(key);
        printMessage(messageText);
        printCipher(cipherText);
        printMessage(decrypted);
    }
    
    /*
     * Helper method to remove whitespace and punctuation from String,
     * set String to upppercase
     * 
     * @param string    String to be modified
     * @return string   Modified String
     */
    private String stringCheck(String string)
    {
        return string.replaceAll("[^a-zA-Z]", "").toUpperCase();
    }
    
    /*
     * Helper method to check the length of one string is equal to another
     * 
     * @param a    First String
     * @param b    Second String
     * @return     True is both Strings are equal length
     */
    private boolean stringLength(String a, String b)
    {
        return a.length() == b.length();
    }
}