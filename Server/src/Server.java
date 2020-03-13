import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(8080);

        while(true){
            Socket client = server.accept();

            RequestHandler rh = new RequestHandler(client);
            rh.start();
        }
    }
}
