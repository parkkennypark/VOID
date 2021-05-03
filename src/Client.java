import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * A class that connects to the server.
 * Handles client-side networking.
 *
 * @author Moosh Khan, Kenny Park
 * @version May 1, 2021
 */
public class Client implements Runnable {
    public static Client instance;
    public static Database database;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private PrintWriter writer;

    private static boolean connected;

    public Client() {
        instance = this;
        database = new Database();
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
                            database.putPost((Post) packet.getObject());
                            Application.updateGUI();
                        }
                        case PROFILE -> {
                            database.putProfile((Profile) packet.getObject());
                            Application.updateGUI();
                        }
                        case POST_HASHTABLE -> {
                            database.setPosts((Hashtable) packet.getObject());
                            Application.updateGUI();
                        }
                        case PROFILE_HASHTABLE -> {
                            database.setProfiles((Hashtable) packet.getObject());
                            Application.updateGUI();
                        }
                        case NEW_PROFILE_ID_RESPONSE -> {
                            int ID = (int) packet.getObject();
                            Application.setLocalProfileID(ID);
                            Application.openMainGUI();
                        }
                    }
                }
            } catch (SocketException e) {
                System.out.println("Lost connection - exiting.");
                Runtime.getRuntime().exit(0);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPacketToServer(Packet packet) {
        try {
            out.reset();
            out.writeObject(packet);
            out.flush();
            System.out.println("Sent: " + packet.getPacketType() + ": " + packet.getObject());
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
