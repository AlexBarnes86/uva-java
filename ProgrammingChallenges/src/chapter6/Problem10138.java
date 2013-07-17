package chapter6;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10138 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	private static final List<BigInteger> fibs = getFibs(500);
	private static final Object[] fibObjAry = fibs.toArray();
	
	private static List<BigInteger> getFibs(int n) {
		List<BigInteger> seq = new ArrayList<BigInteger>(n);
		BigInteger prev = BigInteger.ONE;
		BigInteger cur = new BigInteger("2");
		int ct = 0;
		while(ct < n) {
			seq.add(prev);
			BigInteger next = prev.add(cur);
			prev = cur;
			cur = next;
			ct++;
		}
		
		return seq;
	}
	
	public static int countFibs(BigInteger n) {
		int idx = Arrays.binarySearch(fibObjAry, n);
		
		if(idx < 0) {
			idx = Math.abs(idx)-2;
		}
		
		return idx+1;
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		while(sc.hasNext()) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			BigInteger a = new BigInteger(st.nextToken());
			BigInteger b = new BigInteger(st.nextToken());
			
			if(a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO)) {
				break;
			}
			
			int n = countFibs(b) - countFibs(a);
			if(fibs.contains(new BigInteger(a + ""))) {
				n++;
			}
			pw.println(n);
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
				String fileName = "ProgrammingChallenges/input/chapter6/Problem10138.in";
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