import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Moosh Khan
 * @version client
 */
public class Client implements Runnable{
    public static Client instance;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private static boolean connected;

    public Client() {
        instance = this;
        try {
            socket = new Socket("localhost", 4242);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }
    }

    @Override
    public void run() {
        while(true) {
            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(input);
        }
    }

    public void sendPostToServer(Post post) {
        String postStr = post.toString();
        System.out.println("Sent post " + post.getPostID());
        writer.write("#POST " + postStr);
        writer.println();
        writer.flush();
    }

    public void sendProfileToServer(Profile profile) {
        String profileStr = profile.toString();
        System.out.println("Sent post " + profile.getProfileID());
        writer.write("#PROFILE " + profileStr);
        writer.println();
        writer.flush();
    }

    public void sendCommentToServer(Comment comment) {
        String commentStr = comment.toString();
        System.out.println("Sent comment " + comment.getCommentID());
        writer.write("#COMMENT " + commentStr);
        writer.println();
        writer.flush();
    }

    public void cleanUp() {
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
