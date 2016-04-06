package test;

import java.awt.RenderingHints.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

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
		

	}

}
