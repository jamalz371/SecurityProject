package part1;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;
/**
 * 
 * @author Ben Azouze Jamal
 *
 */
public class SHA_3 {
	/**
	 *  default output size of the hash = 512
	 */
    private static Size DEFAULT = Size.S512;
    
    /**
     * 
     * Message digest calculation
     * 
     * @param string is the message input to treat
     * @return the string message result
     */
    public static byte[] digest(byte[] input) {
    	String string = new String(input,Charset.forName("UTF-8"));
        return digest(string, DEFAULT, true).getBytes();
    }
  /**
   *   
   * Message digest calculation
   *   
   * @param string is the message input to treat
   * @param s is the size
   * @return the string message result
   */
    public static String digest(String string, Size s) {
        return digest(string, s, true);
    }
    
    /**
     * 
     * Message digest calculation
     * 
     * @param string
     * @param s the size
     * @param bouncyencoder
     * @return the string message result encoded
     */
    public static String digest(String string, Size s, boolean bouncyencoder) {
        Size size = s == null ? DEFAULT : s;
        // Initialization with the length of bits
        DigestSHA3 md = new DigestSHA3(size.getValue());
        String text = string != null ? string : "null";
        try {
        	// update the digest using the bytes in the charset UTF-8 stored in text
            md.update(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            // update the digest using the text bytes 
            md.update(text.getBytes());
        }
        byte[] digest = md.digest();
        return encode(digest, bouncyencoder);
    }
    /**
     * 
     * Encodes the string representation of 
     * 
     * @param bytes
     * @param bouncyencoder
     * @return the string encoded
     */
    public static String encode(byte [] bytes, boolean bouncyencoder) {
        if (bouncyencoder)
            return Hex.toHexString(bytes); // return the string representation in base 16
        else {
            BigInteger bigInt = new BigInteger(1, bytes);
            return bigInt.toString(16);
        }
    }
    // possible sizes
    protected enum Size {
        
        S224(224),
        S256(256),
        S384(384),
        S512(512);
        
        int bits = 0;
        
        /**
         * 
         * Initializes the value of bits
         * 
         * @param bits is the integer value
         */
        
        Size(int bits) {
            this.bits = bits;
        }
       /**
        *  
        *  Gets the value of bits
        *  
        * @return integer value bit
        */
        public int getValue() {
            return this.bits;
        }
    }
}