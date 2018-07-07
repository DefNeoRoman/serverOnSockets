import java.io.IOException;

public class Main {
    public static void main(String[] args){
         try
        {
            ChatServer server = new ChatServer(5050);

            server.start();
            System.out.println("Server started");
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
