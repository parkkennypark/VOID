import javax.xml.crypto.Data;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

/**
 * @author Moosh Khan
 * @version server
 */

public class Server extends Thread {
    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        running = false;
        this.interrupt();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                System.out.println("Listening for a connection");
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 4242;
        System.out.println("Start server on port: " + port);

        Server server = new Server(port);
        server.startServer();

        server.stopServer();
    }
}

class RequestHandler extends Thread {
    private Socket socket;

    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Received a connection");

//            BufferedReader reader = new BufferedReader(new InputStreamReader(socketInputStream));
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Send current posts/profiles to new client.
            Packet postsPacket = new Packet(Packet.PacketType.POST_HASHTABLE, Database.getPosts());
            Packet profilesPacket = new Packet(Packet.PacketType.PROFILE_HASHTABLE, Database.getProfiles());
            out.writeObject(postsPacket);
            out.flush();
            out.writeObject(profilesPacket);
            out.flush();

//            String line = reader.readLine();
            Packet input = (Packet)in.readObject();
            Packet output;
            while (input != null) {
//                output = handleIncomingMessage(line);
                output = handleIncomingPacket(input);
                out.writeObject(output);
                System.out.println("Sent " + output.getPacketType() + ": " + output.getObject());

                input = (Packet)in.readObject();
//                line = reader.readLine();
            }
//            reader.close();
            out.close();
            socket.close();

            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Packet handleIncomingPacket(Packet packet) {
        Packet output = null;

        System.out.println("Received " + packet.getPacketType() + ": " + packet.getObject());
        switch (packet.getPacketType()) {
            case POST -> {
                Post post = (Post) packet.getObject();
                Database.putPost(post);
                output = new Packet(Packet.PacketType.POST, post);
//                output = Database.putPost(post);
            }
            case PROFILE -> {
                Profile profile = (Profile) packet.getObject();
                Database.putProfile(profile);
                output = new Packet(Packet.PacketType.PROFILE, profile);
//                output = Database.putProfile(profile);
            }
            case NEW_PROFILE_ID_QUERY -> {
                int ID = Database.getHighestProfileID();
                output = new Packet(Packet.PacketType.NEW_PROFILE_ID_RESPONSE, ID);
            }
            case IS_IDENTIFIER_UNIQUE_QUERY -> {
                String identifier = (String) packet.getObject();
                boolean unique = Database.isIdentifierUnique(identifier);
                output = new Packet(Packet.PacketType.IS_IDENTIFIER_UNIQUE_RESPONSE, unique);
            }
        }
        return output;
    }

    public Object handleIncomingMessage(String message) {
        Packet output = null;
        if (message.contains("#POST")) {
            String postStr = message.substring(6);
            System.out.println("Received post: " + postStr);
            Post post = new Post(postStr);

//            output = Database.putPost(post);
        } else if (message.contains("#PROFILE")) {
            String profileStr = message.substring(9);
            System.out.println("Received profile: " + profileStr);
            Profile profile = new Profile(profileStr);
//            output = Database.putProfile(profile);
        } else if (message.contains("#COMMENT")) {
            String commentStr = message.substring(9);
            System.out.println("Received comment: " + commentStr);
        }
        return output;
    }
}