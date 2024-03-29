
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		genRSAKeys();
	}
	public static void genRSAKeys() {
		long p=MathTools.generatePrime(10000);
		long q=MathTools.generatePrime(10000);
		System.out.println(p+" "+q);
		long n= p*q;
		long phi=(p-1)*(q-1);
		System.out.println("Phi:"+phi);
		long e=MathTools.genE(phi);
		long d = MathTools.genPrivateKey(e, phi);
		//n,e public key
		//n,d private key

		System.out.println("E:"+e);
		System.out.println("D:"+d);
	
		long encrypted=encryptText("Hola que tal", e, n, 256);
		decryptText( encrypted,d, n, 256);
	}
	public static long encryptText(String message,long e,long n,long multiplier) {
		long value=0;
		String string;
		for(int i=0;i<message.length();i++) {
			long ascii=(long)message.charAt(i);
			value+=(message.length()-1-i)*ascii;
		}
		long s=MathTools.encrypt(value, e, n);
		System.out.println("Ascii sequence: "+value);
		System.out.println("Encrypted sequence: "+s);
		return s;
	}
	public static long decryptText(long messageEncrypted,long d,long n,long multiplier) {
		long value=0;
		long s=MathTools.decrypt(messageEncrypted, d, n);
	
		System.out.println("Decrypted sequence: "+s);
		return s;
	}
	
}
