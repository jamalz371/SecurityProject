package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.security.Key;

import part2.SessionKey;

public class SendKey2Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private Socket socket = null;
    private String host = "127.0.0.1";    // null
    
    public SendKey2Test() throws IOException {


        socket = new Socket(host, 4444);

      
  

        OutputStream out = socket.getOutputStream();

        Key[] a = SessionKey.getKeysRSA();
        byte[] enc = a[0].getEncoded();

        out.write(enc);

        out.close();
        socket.close();
    }
}
