package test;

import java.awt.RenderingHints.Key;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import part1.AES_128;
import part1.RSA_2048;
import part2.SessionKey;

public class KeysTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionKey a = new SessionKey();
		a.setKeyAES();
		a.setKeysRSA();
		System.out.println("Début AES : ");
		System.out.println("------------");
		System.out.println("AES key : " + a.getKeyAES());
		System.out.println("AES key getEncoded : " + a.getKeyAES().getEncoded());
		System.out.println("AES key getEncoded : " + a.getKeyAES().getEncoded());
		System.out.println("AES key gethash : " + a.getKeyAES().hashCode());
		System.out.println("AES key gethash : " + a.getKeyAES().hashCode());
		System.out.println("AES key : " + a.getKeyAES());
		
		String message = "Salut tout le monde !";
		byte[] resAES = AES_128.encrypt(a.getKeyAES().getEncoded(), message.getBytes());
		byte[] plainAES = AES_128.decrypt(a.getKeyAES().getEncoded(), resAES);
		java.security.Key[] tmp = a.getKeysRSA();
		byte[] resRSA = RSA_2048.encrypt(tmp[0].getEncoded(), message.getBytes());
		byte[] plainRSA = RSA_2048.decrypt(tmp[1].getEncoded(), resRSA);
		
		try {
			System.out.println("CipherText : " + new String(resAES,"UTF-8"));
			System.out.println("PlainText : " + new String(plainAES,"UTF-8"));
			System.out.println("CipherText RSA: " + new String(resRSA,"UTF-8"));
			System.out.println("PlainText RSA : " + new String(plainRSA,"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		
	}

}
