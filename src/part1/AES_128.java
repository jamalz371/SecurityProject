package part1;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;

/**
 * 
 * @author Ben Azouze Jamal
 *
 */
public class AES_128 {
/**
 * Generates a AES-128 key
 * 
 * @return a key 
 */
	public static SecretKey getKey(){
		SecretKey key = null;
		
		try {
			
			KeyGenerator keyGenerated;
			keyGenerated = KeyGenerator.getInstance("AES"); // specify the used algorithm
			keyGenerated.init(128,new SecureRandom()); // initialization at 128 bits and random generation
		    key = keyGenerated.generateKey(); // generation of the key
		    
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return key;
	}
	
	/**
	 * 
	 * Encrypts the received message in input and return the encrypted message
	 * 
	 * @param k is the used key
	 * @param input is the message received in input to encrypt
	 * @return the encrypted message
	 */
	public static byte[] encrypt(byte[] k, byte[] input){
		
		SecretKey key = new SecretKeySpec(k, "AES");
		
		byte[] res = null;
        
		try {
			
			Cipher c = Cipher.getInstance("AES/CTR/PKCS5Padding");
			// Ceci marche aussi : Cipher c = Cipher.getInstance("AES/CTR/NoPadding");
			byte[] IV = {-111,4,126,-60,-29,-29,-86,27,-125,-92,73,42,-45,64,-121,79};
			c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV));
			
			byte[] plainText = input; // stores the read bytes in the plainText
	        byte[] cipherText = c.doFinal(plainText); // stores the bytes encrypted in the cipherText
		    res = cipherText;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return res;
    }
	
	/**
	 * 
	 * Decrypts the received message in input and returns the decrypted message
	 * 
	 * @param k is the used key
	 * @param input is the encrypted message  
	 * @return the decrypted message 
	 */
	public static byte[] decrypt(byte[] k, byte[] input) {
		
		SecretKey key = new SecretKeySpec(k, "AES");
		
		byte[] res = null;
		
		try{
			
			Cipher c = Cipher.getInstance("AES/CTR/PKCS5Padding");
			// Ceci marche aussi : Cipher c = Cipher.getInstance("AES/CTR/NoPadding");
			byte[] IV = {-111,4,126,-60,-29,-29,-86,27,-125,-92,73,42,-45,64,-121,79};
			c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV));
			
			byte[] encryptedBytes = input; // stores the read bytes 
	        byte[] plainBytes = c.doFinal(encryptedBytes); // stores the decrypted bytes in plainBytes
	        res = plainBytes;
		    
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
    }
	
	/**
	 * transforms the representation in bytes format to hexadecimal format
	 * @param bytes
	 * @return the representation in hexadecimal format
	 */
	
	private static String bytesToHexRepresentation(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	        sb.append(String.format("%02X ", b));
	    }
	    return sb.toString();
	}
	
	/**
	 * Generates a key in hexadecimal format
	 * @return the key in hexadecimal format
	 */
	public static String generateKeyHex(){
		SecretKey myKey = AES_128.getKey();
		byte[] encodedBytes = myKey.getEncoded();
		String s = bytesToHexRepresentation(encodedBytes);
		return s;
	}
	
	
}
