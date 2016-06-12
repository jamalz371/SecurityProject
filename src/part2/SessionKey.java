package part2;

import java.security.Key;

import javax.crypto.SecretKey;

import part1.AES_128;
import part1.RSA_2048;

/**
 * 
 * @author Ben Azouze Jamal
 *
 */

public class SessionKey {

	/*public static void main(String[] args) {
		System.out.println("clé AES au départ : " + myKeyAES);
		System.out.println("clé RSA au départ : " + myKeysRSA);
		setKeyAES();
		System.out.println("clé AES après : " + getKeyAES().getEncoded());
		setKeysRSA();
	}*/
	
	
	private static SecretKey myKeyAES;
	private static final Key[] myKeysRSA = setKeysRSA();
	
	/**
	 * Set the key AES
	 */
	public static void setKeyAES(){
		myKeyAES = AES_128.getKey();
	}
	
	/**
	 * Get the key AES
	 * @return the key 
	 */
	
	public static SecretKey getKeyAES(){
		return myKeyAES;
	}
	
	/**
	 * Set the keys RSA
	 * @return the keys
	 */
	
	public static Key[] setKeysRSA(){
		return RSA_2048.getKeys();
	}
	
	/**
	 * Get the keys RSA
	 * @return the keys
	 */
	
	public static Key[] getKeysRSA(){
		return myKeysRSA;
	}
	
	/**
	 * Set a private key to use 
	 * @param in is the value of the private key
	 */
	
	public static void setPersonalRSA(Key in){
		myKeysRSA[1] = in;
	}
	
	/**
	 * Get the private key to use
	 * @return the private key
	 */
	
	public static Key getPersonalRSA(){
		return myKeysRSA[1];
	}

}
