package part2;

import javax.crypto.SecretKey;

import part1.AES_128;

public class Protocole {

	private String Alice = "ALICE";
	private String Bob = "BOB";
	
	public boolean step1(){
		boolean check = false;
		String res = Alice + Bob;
		SecretKey key = AES_128.getKey();
		res += key.toString();
		byte[] toSend = res.getBytes();
		
		return check;
	}
	
}
