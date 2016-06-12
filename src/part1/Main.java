package part1;

import java.security.Key;
import javax.crypto.SecretKey;

/**
 * 
 * @author Ben Azouze Jamal
 *
 */

public class Main {
	
	
	public static void main(String[] args) {
				
		String plainText = "Salut tout le monde !";
		
		RSA_2048_Exemple(plainText.getBytes());
		//AES_128_Exemple(plainText.getBytes());
		
		//System.out.println(new String(SHA_3_Exemple()));

	}
	
	
	public static void RSA_2048_Exemple(byte[] in) {
		
		Key[] keys = RSA_2048.getKeys();
		
		try{
			byte[] cipher = RSA_2048.encrypt(keys[0].getEncoded(),in);
			System.out.println("Cipher text :"+ new String(cipher,"UTF-8"));
			
			byte[] plainText = RSA_2048.decrypt(keys[1].getEncoded(),cipher);
			System.out.println("Plain text :"+ new String(plainText,"UTF-8"));
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void AES_128_Exemple(byte[] in){
		SecretKey key = AES_128.getKey();
		
		try{
			byte[] cipher = AES_128.encrypt(key.getEncoded(),in);
			System.out.println("Cipher text :"+ new String(cipher,"UTF-8"));
			
			byte[] plainText = AES_128.decrypt(key.getEncoded(),cipher);
			System.out.println("Plain text :"+new String(plainText,"UTF-8"));
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static byte[] SHA_3_Exemple(){
		
		String msg = "Il va pleuvoir demain";
		return SHA_3.digest(msg.getBytes());
	}

}
