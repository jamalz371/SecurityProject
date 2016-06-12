package part2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * @author Ben Azouze Jamal
 *
 */

public class Client {
    
    private Socket socket = null;
    private String host = null;        
    private int port = 4545;
    
    /**
     * Set the ip address of the host
     * @param hip is the value of the ip address of the host
     */
    public void setIP(String hip){
    	host = hip;
    }
    
    /**
     * Set the port 
     * @param valuePort is the value of the port
     */
    public void setPort(int valuePort){
    	port = valuePort;
    }
    
    /**
     * Sends the file
     * @param f is the name of the file
     * @throws IOException
     */
    public void sendFile(String f) throws IOException {

    	String myFile = f;
        socket = new Socket(host, port);

         
        File file = new File(myFile);     
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
    }
    
}