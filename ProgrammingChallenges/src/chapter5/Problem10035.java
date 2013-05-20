package chapter5;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10035 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_INPUT = false;	
	public static final Scanner sc = getScanner();
	
	public static void main(String args[]) {
		PrintWriter pw = new PrintWriter(System.out);
		
		while(sc.hasNext()) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			StringBuffer firstNumber = new StringBuffer(st.nextToken());
			StringBuffer secondNumber = new StringBuffer(st.nextToken());
			
			if(firstNumber.toString().equals("0") && secondNumber.toString().equals("0")) {
				break;
			}
			
			while(firstNumber.length() != secondNumber.length()) {
				if(firstNumber.length() > secondNumber.length()) {
					secondNumber.insert(0, "0");
				}
				else {
					firstNumber.insert(0, "0");
				}
			}
			
			StringBuffer result = new StringBuffer();
			int carryCt = 0;
			int carry = 0;
			for(int i = firstNumber.length()-1; i >= 0; --i) {
				int partialSum = Integer.parseInt(firstNumber.substring(i,i+1)) + Integer.parseInt(secondNumber.substring(i,i+1)) + carry;
				carry = partialSum / 10;
				if(carry > 0) {
					carryCt++;
				}
				result.insert(0, partialSum % 10);
			}
			
			if(carryCt == 0) {
				pw.println("No carry operation.");
			}
			else if(carryCt == 1){
				pw.println("1 carry operation.");
			}
			else {
				pw.println(carryCt + " carry operations.");
			}
		}
		pw.flush();
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		if(!DEBUG_INPUT) {
			return new Scanner(System.in);
		}
		else {
			try {
				String debugFile = "ProgrammingChallenges/input/chapter5/Problem10035.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
}