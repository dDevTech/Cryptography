import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;



public class MainClient {
	public static Socket s;
	public static InputStream is;
	public static OutputStream os;
	public static ObjectOutputStream oos;
	public static ObjectInputStream ois;

	public static void main(String[] args) {

		try {
			s = new Socket(Constants.ip, Constants.PORT);
			System.out.println("Client accepted");
			os = s.getOutputStream();
			is = s.getInputStream();
			System.out.println("DD");
			ois = new ObjectInputStream(is);
			System.out.println("AA");
			oos = new ObjectOutputStream(os);
			
		} catch (UnknownHostException e) {
			System.exit(0);
			System.out.println("Not IP detected");
			System.out.println("Exiting");
			
		} catch (IOException e) {
			System.out.println("Any server on specific IP");
			System.out.println("Exiting");
			System.exit(0);
			
		}
	
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Object o=ois.readObject();
						if(o instanceof Message) {
							Message message = (Message) o;
							System.out.println(message);
							
						}
					} catch (ClassNotFoundException | IOException e) {
						System.out.println("Error type message");
					}
				}
				
			}
		});
		t.start();
		
			System.out.println("aa");
			String s=JOptionPane.showInputDialog("Message");
			sendMessage(new Message(s));
		
		

	}
	public static void sendMessage(Object o) {
		try {
			oos.writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
