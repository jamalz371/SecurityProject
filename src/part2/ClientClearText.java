package part2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientClearText {
    public static void main(String[] args) throws IOException {
        new ClientClearText();
    }
    
    private Socket socket = null;
    private String host = "127.0.0.1";    // null
    
    public ClientClearText() throws IOException {


        socket = new Socket(host, 4444);

        // getText() ici ;
        File file = new File("C:\\Users\\JAMAL\\Desktop\\test.txt");
        // Get the size of the file
        long length = file.length();
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