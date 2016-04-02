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
	
	private static boolean checkStep1 = false;
	private static boolean checkStep2 = false;
	private static boolean checkStep3 = false;
	
	public static byte[] step1(byte[] keyToEncrypt, byte[] keyRSAPublicBOB){
		byte[] sessionKey = keyToEncrypt;
		byte[] bobPublicKeyRSA = keyRSAPublicBOB;
		byte[] resStep1 = RSA_2048.encrypt(bobPublicKeyRSA,sessionKey);
		setCheckStep1();
		return resStep1;
	}
	
	public static byte[] step2(byte[] sessKey, byte[] fileToEncrypt){
		byte[] currentKey = sessKey;
		byte[] contentFile = fileToEncrypt;
		byte[] resStep2 = AES_128.encrypt(currentKey, contentFile);
		setCheckStep2();
		return resStep2;
	}
	
	public static byte[] step3(byte[] privateKeyAlice, byte[] sha3Content){
		byte[] keyAlice = privateKeyAlice;
		byte[] signToEncrypt = sha3Content;
		byte[] resStep3 = RSA_2048.encryptWithPrivate(keyAlice, signToEncrypt);
		setCheckStep3();
		return resStep3;
	}
	
	public static void setCheckStep1(){
		checkStep1 = true;
	}
	
	public static boolean getCheckStep1(){
		return checkStep1;
	}
	
	public static void setCheckStep2(){
		checkStep2 = true;
	}
	
	public static boolean getCheckStep2(){
		return checkStep2;
	}
	
	public static void setCheckStep3(){
		checkStep3 = true;
	}
	
	public static boolean getCheckStep3(){
		return checkStep3;
	}
}
