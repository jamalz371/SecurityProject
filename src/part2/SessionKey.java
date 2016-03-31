package part2;

import javax.crypto.SecretKey;

import part1.AES_128;


public class SessionKey {

	private static SecretKey myKey;
	
	public static void setKey(){
		myKey = AES_128.getKey();
	}
	
	public static SecretKey getKey(){
		return myKey;
	}
}
