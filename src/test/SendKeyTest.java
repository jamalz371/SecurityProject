package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SendKeyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		InputStream in = null;
		//OutputStream out = null;
		ServerSocket serverSocket = null;
		int portNumber = 443;
	    String myAddress = "127.0.0.1";
	    
        try {
            serverSocket = new ServerSocket(portNumber,5,InetAddress.getByName(myAddress));
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        byte[] storeBytes = new byte[16*1024];

        
        try {
			while ((in.read(storeBytes)) > 0) {
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        System.out.println("Stored : " + storeBytes.toString());
	}
}

