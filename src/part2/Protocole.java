package part2;

import javax.crypto.SecretKey;

import part1.AES_128;

public class Protocole {

	public static void main(String[] args){
		Protocole p = new Protocole();
		System.out.println(p.step1());
	}
	
	//private String Alice = "ALICE";
	//private String Bob = "BOB";
	private boolean publicKeySent = false;
	
	public boolean sendKeyPublic(){
		
		publicKeySent = true;
		return publicKeySent;
	}
	
	public boolean step1(){
		boolean check = false;
		//String res = Alice + Bob;
		SecretKey key = AES_128.getKey();

		//byte[] toSend = res.getBytes();
		//System.out.println(toSend.toString());
		return check;
	}
	
}
