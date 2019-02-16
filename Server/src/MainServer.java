import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {
	public static ServerSocket server;
	public static ArrayList<Client>clients = new ArrayList<>();
	public static void main(String[] args) {
		try {
			server = new ServerSocket(Constants.PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server started");
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
			
					try {
						Socket s =server.accept();
						clients.add(new Client(s));
						System.out.println("New client");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		t.start();

	}
	public void sendMessage(Object o) {
		for(Client c:clients) {
			c.sendMessage(o);
		}
		
	}

}
