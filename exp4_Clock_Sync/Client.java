import java.io.*;
import java.net.*;
import java.util.Date;

public class Client{
    public static void main(String[] args) throws IOException {
        // set the IP address and port number of the time server
        String address = "localhost";
        int port = 6969;

        // create a socket to connect to the time server
        Socket socket = new Socket(address, port);

        // get the current time from the system clock
        Date currentTime = new Date();
        long currentMillis = currentTime.getTime();

        // send the request to the time server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(currentMillis);

        // get the response from the time server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        long serverMillis = Long.parseLong(in.readLine());

        // calculate the round-trip delay and clock offset
        long roundTripDelay = (new Date()).getTime() - currentMillis;
        long clockOffset = serverMillis - (currentMillis + roundTripDelay/2);

        // display the results
        System.out.println("System clock time: " + currentTime);
        System.out.println("Server clock time: " + new Date(serverMillis));
        System.out.println("Round-trip delay: " + roundTripDelay + "ms");
        System.out.println("Clock offset: " + clockOffset + "ms");

        // close the socket
        socket.close();
    }
}
