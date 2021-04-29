import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 *
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

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            String line = in.readLine();
            while (line != null && line.length() > 0) {
                handleIncomingMessage(line);
                line = in.readLine();
            }

            in.close();
            out.close();
            socket.close();

            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleIncomingMessage(String message) {
        if (message.split(" ")[0].equals("#POST")) {
            System.out.println("Post: " + message);
        } else if (message.split(" ")[0].equals("#PROFILE")) {
            System.out.println("Creating profile with details: " + message);
        }
    }
}