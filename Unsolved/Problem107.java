package AOAPC.vol1.MiscMath;
//Unsolved
import java.util.Scanner;

public class Problem107 {
	private static void solve(long height, long workers) {
		long sumHeights = 0;
		long levels = 1;
		
	}
	
	public static void main(String [] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String line = "";
		
		while(true) {
			line = sc.nextLine();
			String [] tokens = line.trim().split("\\s+");
			long height = Integer.parseInt(tokens[0]);
			long workers = Integer.parseInt(tokens[1]);
			if(height == 0 && workers == 0)
				return;
			solve(height, workers);
		}
	}
}
