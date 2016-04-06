package part2;

import java.security.Key;

import javax.crypto.SecretKey;

import part1.AES_128;
import part1.RSA_2048;


public class SessionKey {

	public static void main(String[] args) {
		System.out.println("cl� AES au d�part : " + myKeyAES);
		System.out.println("cl� RSA au d�part : " + myKeysRSA);
		setKeyAES();
		System.out.println("cl� AES apr�s : " + getKeyAES().getEncoded());
		setKeysRSA();
		
	}
	
	
	private static SecretKey myKeyAES;
	private static final Key[] myKeysRSA = setKeysRSA();
	
	public static void setKeyAES(){
		myKeyAES = AES_128.getKey();
	}
	
	public static SecretKey getKeyAES(){
		return myKeyAES;
	}
	
	public static Key[] setKeysRSA(){
		return RSA_2048.getKeys();
	}
	
	public static Key[] getKeysRSA(){
		return myKeysRSA;
	}
	
	public static void setPersonalRSA(Key in){
		myKeysRSA[1] = in;
	}
	
	public static Key getPersonalRSA(){
		return myKeysRSA[1];
	}

}
