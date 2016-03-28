package part1;

import java.security.Key;

import javax.crypto.SecretKey;

import part2.Window;

public class Main {
	
	
	public static void main(String[] args) {
				
		String plainText = "Salut tout le monde !";
		
		//RSA_2048_Exemple(plainText.getBytes());
		//AES_128_Exemple(plainText.getBytes());
		
		//System.out.println(new String(SHA_3_Exemple()));
		
		
		//SecretKey a = AES_128.getKey();
		//System.out.println(a.toString());
		//String sep = System.getProperty("line.separator"); // pour insérer une nouvelle ligne
		//String extension = name; // "name" est le nom d'un fichier file
		//extension = extension.substring(extension.length()-4, extension.length());
		//System.out.println("Le separateur : " + sep);
		String b = AES_128.generateKeyHex();
		System.out.println("clé AES en hex : " + b);
		byte[] d = hexStringToByteArray(b);
		System.out.println("clé AES en bytes : " + d);
		
		Key k[] = RSA_2048.getKeys();
		//System.out.println(k[0]); // clé publique en texte (ceci marche aussi)
		System.out.println("clé RSA publique en texte : " + k[0].toString());
		System.out.println("clé RSA publique en hex : " + RSA_2048.bytesToHexRepresentation(k[0].getEncoded()));
		System.out.println("clé RSA privée en hex : " + RSA_2048.bytesToHexRepresentation(k[1].getEncoded()));
		Window.writeFile("hahahahaha".getBytes(), "C:\\Users\\JAMAL\\Desktop\\hahahahaha.txt");
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	public static void RSA_2048_Exemple(byte[] in) {
		
		Key[] keys = RSA_2048.getKeys();
		
		try{
			byte[] cipher = RSA_2048.encrypt(keys[0].getEncoded(),in);
			System.out.println("Cipher text :"+ new String(cipher,"UTF-8"));
			
			byte[] plainText = RSA_2048.decrypt(keys[1].getEncoded(),cipher);
			System.out.println("Plain text :"+new String(plainText,"UTF-8"));
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
