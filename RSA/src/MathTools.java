import java.math.BigInteger;
import java.time.chrono.IsoChronology;
import java.util.Random;

public class MathTools {

	public static long generatePrime(long max) {
		boolean created=false;
		Random r = new Random();
		long current = (long)(r.nextDouble()*max);
		while(!created) {
			if(isPrime(current)) {
				return current;
			}
			current++;
		}
		return 2;
		
	}
	private static  boolean isPrime(long number) {
		  for (long i = 2; i < number; i++) {
		        if (number % i == 0) {
		            return false;
		        }
		    }
		    return true;
	}
	public static long genE(long phi) {
		
		Random random= new Random();
		while(true) {
			long e=(long)(random.nextDouble()*(phi-1000))+1000;
			if(coprime(e, phi)) {
				return e;
			}
		}

	
	}
	public static long genPrivateKey(long e,long phi) {
		BigInteger bi = BigInteger.valueOf(e);
		BigInteger i=bi.modInverse( BigInteger.valueOf(phi));
	
		return 	i.intValue();
	}
	private static long modInverse(long e, long phi) 
	{ 
	    e = e%phi; 
	    for (long d=phi-1; d<1; d--) {
	       if ((e*d) % phi == 1) {
	          return d; 
	       }
	    }
	    return 1;
	} 
	public static long encrypt(long m,long e,long n) {
		return (long)Math.pow(m, e)%n;
	}
	public static long decrypt(long c,long d,long n) {
		return (long)Math.pow(c, d)%n;
	}
	 private static long __gcd(long a, long b) 
	    { 
	      
	        if (a == 0 || b == 0) 
	            return 0; 
	          
	    
	        if (a == b) 
	            return a; 
	          
	  
	        if (a > b) 
	            return __gcd(a-b, b); 
	                  
	        return __gcd(a, b-a); 
	    } 
	      
	   
	    public static boolean coprime(long a, long b) { 
	          
	        if ( __gcd(a, b) == 1) {
	        	return true;
	        }else {
	        	return false;
	        }
	         
	    } 
}
