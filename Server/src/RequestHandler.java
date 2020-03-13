import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class RequestHandler extends Thread{

        private Socket client;
        private ArrayList<String> equations;

        public RequestHandler(Socket client) {
            this.equations = new ArrayList<>();
            this.client = client;
        }

        public void run() {
            try {
                while(true) {
                    ObjectInputStream input = new ObjectInputStream(this.client.getInputStream());
                    String eq = (String) input.readObject();

                    equations.add(eq);
                    System.out.println("\nList of all equations:");
                    int count = 0;
                    for(String s:equations){
                        System.out.println(s);
                        count += 1;
                    }
                    System.out.println("Total: " + count);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
