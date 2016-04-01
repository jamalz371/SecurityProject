package part2;

import java.security.Key;

import javax.crypto.SecretKey;

import part1.AES_128;
import part1.RSA_2048;


public class SessionKey {

	public static void main(String[] args) {
		System.out.println("clé AES au départ : " + myKeyAES);
		System.out.println("clé RSA au départ : " + myKeysRSA);
		setKeyAES();
		System.out.println("clé AES après : " + getKeyAES().getEncoded());
		setKeyRSA();
		System.out.println("clé publique RSA après : " + myKeysRSA[0]);
		System.out.println("clé privée RSA après : " + myKeysRSA[1].getEncoded());
	}
	
	
	private static SecretKey myKeyAES;
	private static Key[] myKeysRSA;
	
	public static void setKeyAES(){
		myKeyAES = AES_128.getKey();
	}
	
	public static SecretKey getKeyAES(){
		return myKeyAES;
	}
	
	public static void setKeyRSA(){
		myKeysRSA = RSA_2048.getKeys();
	}
	
	public static Key[] getKeysRSA(){
		return myKeysRSA;
	}

}
