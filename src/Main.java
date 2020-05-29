import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        InetAddress serverInetAddress;

        if (args.length > 1) {
            serverInetAddress = InetAddress.getByName(args[0]);
        }
        else {
            serverInetAddress = InetAddress.getLocalHost();
        }

        Socket socket = new Socket(serverInetAddress, 10000);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            bw.write(line + System.lineSeparator() );
            bw. flush();
            String received = br.readLine();
            System.out.printf("Sent: %s%nReceived: %s%n", line, received);
        }

        br.close();

        bw.write("bye" + System.lineSeparator());
        bw.flush();
        socket.close();
    }
}