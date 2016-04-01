package part2;

import part1.AES_128;
import part1.RSA_2048;

public class Protocole {

	public static void main(String[] args){
		//Protocole p = new Protocole();
		//System.out.println(p.step1());
	}
	
	/*public boolean sendKeyPublic(){
		
		publicKeySent = true;
		return publicKeySent;
	}*/
	
	public static byte[] step1(byte[] keyToEncrypt, byte[] keyRSAPublicBOB){
		byte[] sessionKey = keyToEncrypt;
		byte[] bobPublicKeyRSA = keyRSAPublicBOB;
		byte[] resStep1 = RSA_2048.encrypt(bobPublicKeyRSA,sessionKey);
		return resStep1;
	}
	
	public static byte[] step2(byte[] sessKey, byte[] fileToEncrypt){
		byte[] currentKey = sessKey;
		byte[] contentFile = fileToEncrypt;
		byte[] resStep2 = AES_128.encrypt(currentKey, contentFile);
		return resStep2;
	}
	
	public static byte[] step3(byte[] privateKeyAlice, byte[] sha3Content){
		byte[] keyAlice = privateKeyAlice;
		byte[] signToEncrypt = sha3Content;
		byte[] resStep3 = RSA_2048.encryptWithPrivate(keyAlice, signToEncrypt);
		return resStep3;
	}
}
