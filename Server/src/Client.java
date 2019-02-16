import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	public InputStream is;
	public OutputStream os;
	public boolean connected=false;
	public Client(Socket s) {

		try {
			os =s.getOutputStream();
			is=s.getInputStream();
			ois = new ObjectInputStream(is);
			oos = new ObjectOutputStream(os);
			connected = true;
		} catch (IOException e) {
			connected=false;
		}
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(connected) {
					try {
						Object o=ois.readObject();
						if(o instanceof Message) {
							Message message = (Message) o;
							System.out.println(message);
							
						}
					} catch (ClassNotFoundException | IOException e) {
						connected=false;
					}
				}
				
			}
		});
		t.start();
	
	}
	public void sendMessage(Object o) {
		try {
			oos.writeObject(o);
		} catch (IOException e) {
			connected=false;
			e.printStackTrace();
		}
	}
}
