import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket;

        socket = new Socket("127.0.0.1",9000);
        BufferedReader serverReader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader systemInReader = new BufferedReader(new InputStreamReader(System.in));

        String lineFromSystemIn, lineFromServer;

        while ((lineFromSystemIn = systemInReader.readLine()) != null) {
            serverWriter.println(lineFromSystemIn);
            lineFromServer = serverReader.readLine();
            System.out.println(lineFromServer);
            if (lineFromSystemIn.equalsIgnoreCase("close")) {
                break;
            }
            if (lineFromSystemIn.equalsIgnoreCase("exit")) {
                break;
            }
        }

        serverWriter.close();
        serverReader.close();
        systemInReader.close();
        socket.close();
    }
}
