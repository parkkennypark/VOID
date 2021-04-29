import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 *
 * @author Moosh Khan
 * @version client
 */
public class Client {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(){
        try{
            socket = new Socket("localhost", 4242);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendPostToServer(String postStr){
        writer.write("#POST " + postStr);
        writer.println();
        writer.flush();
    }

    public void sendProfileToServer(String profileStr){
        writer.write("#PROFILE " + profileStr);
        writer.println();
        writer.flush();
    }

    public void cleanUp(){
        try{
            writer.close();
            reader.close();
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
