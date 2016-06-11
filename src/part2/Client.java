package part2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    //public static void main(String[] args) throws IOException {
     //   new Client();
    //}
    
    private Socket socket = null;
    private String host = null;        //"127.0.0.1";    // null
    private int port = 4545;
    
    public void setIP(String hip){
    	host = hip;
    }
    
    public void setPort(int valuePort){
    	port = valuePort;
    }
    
    public void sendFile(String f) throws IOException {

    	String myFile = f;
    	// ajouter getText() ici pour avoir l'IP de host
        socket = new Socket(host, port);

         
        File file = new File(myFile);     //"C:\\Users\\JAMAL\\Desktop\\test.txt");
        // Get the size of the file
        //long length = file.length();
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