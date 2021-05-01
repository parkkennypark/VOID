import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * @author Moosh Khan
 * @version client
 */
public class Client implements Runnable {
    public static Client instance;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private PrintWriter writer;

    private static boolean connected;

    public Client() {
        instance = this;
        try {
            socket = new Socket("localhost", 4242);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

//            writer = new PrintWriter(socket.getOutputStream());

            connected = true;
            System.out.println("Connected");
            Thread t = new Thread(this);
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
            System.out.println("Failed to connect");
        }
    }

    @Override
    public void run() {
        while (true) {
            Object input;
            try {
                input = in.readObject();

                if (input instanceof Packet) {
                    Packet packet = (Packet) input;
                    System.out.println("Received " + packet.getPacketType() + ": " + packet.getObject());
                    switch (packet.getPacketType()) {
                        case POST -> {
                            Database.putPost((Post) packet.getObject());
                            Application.updateGUI();
                        }
                        case PROFILE -> {
                            Database.putProfile((Profile)packet.getObject());
                            Application.updateGUI();
                        }
                        case POST_HASHTABLE -> {
                            Database.setPosts((Hashtable) packet.getObject());
                        }
                        case PROFILE_HASHTABLE -> {
                            Database.setProfiles((Hashtable) packet.getObject());
                        }
                        case NEW_PROFILE_ID_RESPONSE -> {
                            int ID = (int) packet.getObject();
                            Application.setLocalProfileID(ID);
                            Application.openMainGUI();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPacketToServer(Packet packet) {
        try {
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
