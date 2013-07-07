package chapter7;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10104 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	private static class MutableLong {
		public String toString() {
			return String.valueOf(val);
		}
		
		public Long val;
	}
	
	/* Find the gcd(p,q) and x,y such that p*x + q*y = gcd(p,q) */
	static long gcd(long p, long q, MutableLong x, MutableLong y) {
		MutableLong x1 = new MutableLong(), y1 = new MutableLong(); /* previous coefficients */
		long g; /* value of gcd(p,q) */
		if (q > p) return(gcd(q,p,y,x));
		if (q == 0) {
			x.val=1L;
			y.val=0L;
			return(p);
		}
		g = gcd(q, p%q, x1, y1);
		x.val = y1.val;
		y.val = (x1.val - (long)Math.floor(p/(1.0*q))*y1.val);
		
		return g;
	}
	
	public static void main(String [] args) {
		String line;
		PrintWriter pw = new PrintWriter(System.out);
		
		while(sc.hasNext()) {
			line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			long a = Long.valueOf(st.nextToken().replace(",", "").trim());
			long b = Long.valueOf(st.nextToken().replace(",", "").trim());
			MutableLong x = new MutableLong();
			MutableLong y = new MutableLong();
			long g = gcd(a, b, x, y);
			pw.println(x + " " + y + " " + g);
		}
		pw.flush();
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			System.out.print("DEBUG: ");
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				String fileName = "ProgrammingChallenges/input/chapter7/Problem10104.in";
				Scanner sc = new Scanner(new File(fileName));
				System.out.println("Reading from: " + fileName);
				return sc;
			}
			else {
				return new Scanner(System.in);
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
}