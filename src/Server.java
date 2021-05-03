import javax.xml.crypto.Data;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A class that accepts multiple clients simultaneously.
 * Receives and sends information to and from clients.
 *
 * @author Moosh Khan, Kenny Park
 * @version May 1, 2021
 */

public class Server extends Thread {
    public static Server instance;
    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;
    public static Database database;

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

    private static ArrayList<RequestHandler> requestHandlers = new ArrayList<>();

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                System.out.println("Listening for a connection");
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
                requestHandlers.add(requestHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateAllClients(RequestHandler except) {
        for (RequestHandler requestHandler : requestHandlers) {
            if (requestHandler != null && requestHandler != except) {
//                requestHandler.sendPacket(packet);
                requestHandler.sendCurrentData();
            }
        }
    }

    public static void main(String[] args) {
        int port = 4242;
        System.out.println("Start server on port: " + port);

//        database = new Database();
        loadDatabase();

        Server server = new Server(port);

        instance = server;
        new ServerGUI();

        server.startServer();

        server.stopServer();
    }

    private static void loadDatabase() {
        try {
            FileInputStream fi = new FileInputStream("database.txt");
            ObjectInputStream oi = new ObjectInputStream(fi);

            database = (Database) oi.readObject();
            System.out.println("Database file loaded.");

            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("Database file not initialized, initializing:");
            database = new Database();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveDatabase() {
        try {
            FileOutputStream f = new FileOutputStream("database.txt");
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(database);
            System.out.println("Database saved.");

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
    }
}

/**
 * A class that is made per connected client.
 * Receives and sends information to and from clients.
 *
 * @author Moosh Khan, Kenny Park
 * @version May 1, 2021
 */
class RequestHandler extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Received a connection");

//            BufferedReader reader = new BufferedReader(new InputStreamReader(socketInputStream));
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Send current posts/profiles to new client.
            sendCurrentData();

//            String line = reader.readLine();
            Packet input = (Packet) in.readObject();
            Packet output;
            while (input != null) {
//                output = handleIncomingMessage(line);

                output = handleIncomingPacket(input);
                if (output != null) {
                    sendPacket(output);
                    Server.updateAllClients(this);
//                    Server.sendPacketToAllClients(output);
                    Server.saveDatabase();
                }

                input = (Packet) in.readObject();
//                line = reader.readLine();
            }
//            reader.close();
            out.close();
            socket.close();

            System.out.println("Connection closed");
        } catch (SocketException e) {
            System.out.println("Client lost connection.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCurrentData() {
        Packet postsPacket = new Packet(Packet.PacketType.POST_HASHTABLE, Server.database.getPosts());
        Packet profilesPacket = new Packet(Packet.PacketType.PROFILE_HASHTABLE, Server.database.getProfiles());
        sendPacket(postsPacket);
        sendPacket(profilesPacket);
        System.out.println("Sent current posts and profiles.");
    }

    public void sendPacket(Packet packet) {
        try {
            out.reset();
            out.writeObject(packet);
            out.flush();
            System.out.println("Sent " + packet.getPacketType() + ": " + packet.getObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Packet handleIncomingPacket(Packet packet) {
        Packet output = null;

        System.out.println("Received " + packet.getPacketType() + ": " + packet.getObject());
        switch (packet.getPacketType()) {
            case POST -> {
                Post post = (Post) packet.getObject();
                Server.database.putPost(post);
                output = new Packet(Packet.PacketType.POST, post);
            }
            case PROFILE -> {
                Profile profile = (Profile) packet.getObject();
                Server.database.putProfile(profile);
                output = new Packet(Packet.PacketType.PROFILE, profile);
            }
            case COMMENT -> {
                Comment comment = (Comment) packet.getObject();
                try {
                    Post post = Server.database.getPostByID(comment.getPostIDReplyingTo());
                    post.putComment(comment);
                    output = new Packet(Packet.PacketType.POST, post);
                } catch (PostNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case NEW_PROFILE_ID_QUERY -> {
                int ID = Server.database.getHighestProfileID();
                output = new Packet(Packet.PacketType.NEW_PROFILE_ID_RESPONSE, ID);
            }
            case DELETE_POST_QUERY -> {
                Post post = (Post) packet.getObject();
                Server.database.deletePost(post.getPostID());
                output = new Packet(Packet.PacketType.POST_HASHTABLE, Server.database.getPosts());
            }
            case DELETE_PROFILE_QUERY -> {
                Profile profile = (Profile) packet.getObject();
                Server.database.deleteProfile(profile.getProfileID());
                output = new Packet(Packet.PacketType.PROFILE_HASHTABLE, Server.database.getProfiles());
            }
            case DELETE_COMMENT_QUERY -> {
                Comment comment = (Comment) packet.getObject();
                Post post = new Post();
                try {
                    post = Server.database.getPostByID(comment.getPostIDReplyingTo());
                    post.removeComment(comment.getCommentID());
                } catch (PostNotFoundException e) {
                    e.printStackTrace();
                }
                output = new Packet(Packet.PacketType.POST, post);
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

//            output = database.putPost(post);
        } else if (message.contains("#PROFILE")) {
            String profileStr = message.substring(9);
            System.out.println("Received profile: " + profileStr);
            Profile profile = new Profile(profileStr);
//            output = database.putProfile(profile);
        } else if (message.contains("#COMMENT")) {
            String commentStr = message.substring(9);
            System.out.println("Received comment: " + commentStr);
        }
        return output;
    }
}