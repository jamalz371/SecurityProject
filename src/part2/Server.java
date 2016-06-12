package part2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Ben Azouze Jamal
 *
 */

public class Server {
   
	private int port = 4455;
    private Socket socket = null;
    private InputStream in = null;
    private OutputStream out = null;
    
    /**
     * Set the value of the protocol
     * @param valuePort is the value of the protocol
     */
    
    public void setPort(int valuePort){
    	port = valuePort;
    }
    
    /**
     * Receives the file 
     * @param rf is the name of the file to receive
     * @throws IOException
     */
    
    public void receiveFile(String rf) throws IOException{
    	ServerSocket serverSocket = null;

    	String myReceiveFile = rf;
    	
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number");
        }

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream");
        }

        try {
        	
            out = new FileOutputStream(myReceiveFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
    
}