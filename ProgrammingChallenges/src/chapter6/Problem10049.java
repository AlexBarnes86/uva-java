package chapter6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem10049 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	public static void main(String[] args) {
		final int MAX = 2000000000;
		
		List<Integer> changed = new ArrayList<Integer>();
		changed.add(1);
		changed.add(2);
		
		int i = 2;
		int ct = 2;
		while(changed.get(changed.size()-1) < MAX) {
			for(int j = 0; j < ct; ++j) {
				changed.add(changed.get(changed.size()-1)+i);
			}
			
			i++;
			if(changed.get(ct) == i) {
				ct++;
			}
		}
		
		Object[] ary = changed.toArray();
		while(sc.hasNext()) {
			int n = sc.nextInt();
			if(n == 0) {
				break;
			}
			
			int ans = Arrays.binarySearch(ary, n);
			if(ans >= 0) {
				System.out.println(ans+1);
			}
			else {
				System.out.println(-ans-1);
			}
		}
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
				String fileName = "ProgrammingChallenges/input/chapter6/Problem10049.in";
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