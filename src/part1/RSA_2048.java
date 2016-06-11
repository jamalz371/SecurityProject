package part1;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 
 * @author Ben Azouze Jamal
 *
 */

public class RSA_2048 {
	
	/**
	 * Generates RSA keys
	 * 
	 * @return key list (key[0] = public key, key[1] = private key)
	 */
	public static Key[] getKeys(){
		
		Key res[] = new Key[2];
		
		try {
			
			KeyPairGenerator kGen = KeyPairGenerator.getInstance("RSA");
			kGen.initialize(2048);
			KeyPair kPair = kGen.generateKeyPair();
			
			Key publicKey = kPair.getPublic();
			Key privateKey = kPair.getPrivate();
			
			res[0] = publicKey;
			res[1] = privateKey;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	/**
	 * Encrypts a plaintext by using the private key 
	 * @param key is the private key to use
	 * @param input is the plaintext to encrypt
	 * @return cipher text (= plaintext encrypted with the private key)
	 */
	
	public static byte[] encryptWithPrivate(byte[] key, byte[] input){
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		byte[] res = null;
		
		try {
			Key privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(key));
			
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding","BC");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			
			byte[] plainText = input;
			byte[] cipherText = cipher.doFinal(plainText);
			
			res = cipherText;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return res;
		
	}
	
	/**
	 * Encrypts a given byte array with a given public key
	 * 
	 * @param pubKey the public key
	 * @param input the bytes to encrypt
	 * @return the RSA-2048 encryption of the input in a byte array
	 */
	public static byte[] encrypt(byte[] pKey, byte[] input){
		
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		byte[] res = null;
		
		try {
			Key pubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pKey));
			
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding","BC");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			
			byte[] plainText = input;
			byte[] cipherText = cipher.doFinal(plainText);
			
			res = cipherText;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return res;
		
	}
	
	/**
	 * Decrypts an given cipher byte array with a given private key
	 * 
	 * @param privateKey the private key
	 * @param input the cipher byte array
	 * @return a decrypted byte array of the input
	 */
	
	public static byte[] decrypt(byte[] privKey, byte[] input){
		
		byte[] res = null;
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		try {
			Key privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privKey));
			
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			byte[] cipherText = input;
			byte[] plainText = cipher.doFinal(cipherText);
		    
			res = plainText;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	/**
	 * 
	 * @param key
	 * @param input
	 * @return
	 */
	public static byte[] decryptWithPublic(byte[] key, byte[] input){
		byte[] res = null;
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		try {
			Key pubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(key));
			
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			
			byte[] cipherText = input;
			byte[] plainText = cipher.doFinal(cipherText);
		    
			res = plainText;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public static String bytesToHexRepresentation(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	        sb.append(String.format("%02X ", b));
	    }
	    return sb.toString();
	}
	

	@SuppressWarnings("null")
	public static String[] generateKeyHex(){
		Key mykey[] = new Key[2];
		mykey = RSA_2048.getKeys();
		String s[] = null;
		s[0] = bytesToHexRepresentation(mykey[0].getEncoded());
		byte[] encodedBytes2 = mykey[1].getEncoded();
		s[1] = bytesToHexRepresentation(encodedBytes2);
		return s;
	}
}
