package test;

import java.io.UnsupportedEncodingException;
import part1.AES_128;
import part1.RSA_2048;
import part2.SessionKey;

public class KeysTest {

	public static void main(String[] args) {
		SessionKey.setKeyAES();
		SessionKey.setKeysRSA();
		System.out.println("Début AES : ");
		System.out.println("------------");
		System.out.println("AES key : " + SessionKey.getKeyAES());
		System.out.println("AES key getEncoded : " + SessionKey.getKeyAES().getEncoded());
		System.out.println("AES key getEncoded : " + SessionKey.getKeyAES().getEncoded());
		System.out.println("AES key gethash : " + SessionKey.getKeyAES().hashCode());
		System.out.println("AES key gethash : " + SessionKey.getKeyAES().hashCode());
		System.out.println("AES key : " + SessionKey.getKeyAES());
		
		String message = "Salut tout le monde !";
		byte[] resAES = AES_128.encrypt(SessionKey.getKeyAES().getEncoded(), message.getBytes());
		byte[] plainAES = AES_128.decrypt(SessionKey.getKeyAES().getEncoded(), resAES);
		java.security.Key[] tmp = SessionKey.getKeysRSA();
		byte[] resRSA = RSA_2048.encrypt(tmp[0].getEncoded(), message.getBytes());
		byte[] plainRSA = RSA_2048.decrypt(tmp[1].getEncoded(), resRSA);
		
		try {
			String testRSA = new String(resRSA,"UTF-8");
			String testRSAPlain = new String(plainRSA,"UTF-8");
			System.out.println("CipherText : " + new String(resAES,"UTF-8"));
			System.out.println("PlainText : " + new String(plainAES,"UTF-8"));
			System.out.println("CipherText RSA: " + new String(resRSA,"UTF-8"));
			System.out.println("PlainText RSA : " + new String(plainRSA,"UTF-8"));
			System.out.println("testRSA cipher : " + testRSA);
			System.out.println("testRSA plain : " + testRSAPlain);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
