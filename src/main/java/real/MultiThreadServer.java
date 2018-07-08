package real;

import real.MonoThreadClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
    static ExecutorService executeIt = Executors.newFixedThreadPool(10);

    /**
     * @param args
     */
    public static void main(String[] args) {

       try (ServerSocket server = new ServerSocket(5050);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server started");
            while (!server.isClosed()) {
                if (br.ready()) {
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("Bye.")) {
                        server.close();
                        break;
                    }
                }
                Socket client = server.accept();
                executeIt.execute(new MonoThreadClientHandler(client));
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
