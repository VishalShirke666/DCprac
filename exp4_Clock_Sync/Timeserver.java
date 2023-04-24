import java.io.*;
import java.net.*;

public class Timeserver {
    public static void main(String[] args) throws IOException {
        // set the port number of the time server
        int port = 6969;

        // create a server socket to listen for client connections
        ServerSocket s = new ServerSocket(port);
        System.out.println("Time server started on port " + port);

        while (true) {
            // accept a client connection
            Socket clientSocket = s.accept();
            //System.out.println("Client connected from " + clientSocket.getInetAddress());

            // read the request from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            long requestMillis = Long.parseLong(in.readLine());

            // get the current time from the system clock
            long serverMillis = System.currentTimeMillis();

            // send the response to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(serverMillis);

            // close the client socket
            clientSocket.close();
            System.out.println("Client disconnected");
        }
    }
}
