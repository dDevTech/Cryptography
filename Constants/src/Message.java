import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String s;

	public Message(String s ) {
		this.setMessage(s);
		
	}

	public String getMessage() {
		return s;
	}

	public void setMessage(String s) {
		this.s = s;
	}
}
