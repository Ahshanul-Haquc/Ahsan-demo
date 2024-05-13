import java.io.*;
import java.net.*;

public class ServerOneWay {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket (5000);
        System.out.println("Server is connected at port no: " + ss.getLocalPort());
        System.out.println("Server is connecting\n");
        System.out.println("Waiting for the client\n");
        Socket s = ss.accept();
        System.out.println("Client request is accepted at port no: " + s.getPort());
        System.out.println("Server’s Communication Port: "+s.getLocalPort());
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        DataInputStream input = new DataInputStream(s.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String clientMessage = "";
        String serverMessage = "";

        while (!clientMessage.equals("stop"))
            clientMessage = input.readUTF();
            System.out.println("Client Says: " + clientMessage);

            // Sending response to client
            System.out.print("Server Says: ");
            serverMessage = reader.readLine();
            output.writeUTF(serverMessage);
            output.flush(); // Flush the output stream to ensure the message is sent immediately
        }

        s.close();
        input.close();
        output.close();
        reader.close();
    }

