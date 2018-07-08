package versionOne;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
    private ServerSocket serverSocket;
    private boolean finished;
    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
        finished = false;
    }
    public void close(){
        finished = true;
    }
    public void start(){
        while(!finished){
            try {
                System.out.println("Server started");
                new ChatSocket(serverSocket.accept(),this).start();
            }catch (IOException io){
                io.printStackTrace();
            }
        }
    }
}
