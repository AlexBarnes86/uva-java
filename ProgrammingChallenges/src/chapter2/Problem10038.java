package chapter2;

import java.util.Scanner;

public class Problem10038 {
	public static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(sc.hasNext()) {
			String line = sc.nextLine();
			String[] tokens = line.trim().split("\\s+");
			
			int n = Integer.parseInt(tokens[0]); //problem implies n > 0, could be trouble
			boolean[] appear = new boolean[n-1];
			
			if(n == 1) {
				System.out.println("Jolly");
				continue;
			}
			
			long prev = Long.parseLong(tokens[1]);
			for(int i = 2; i < tokens.length; ++i) {
				long val = Long.parseLong(tokens[i]);
				long diff = Math.abs(val-prev);
				if(diff < n  && diff != 0) {
					appear[(int)diff-1] = true;
				}
				prev = val;
			}
			
			boolean jolly = true;
			for(int i = 0; i < appear.length; ++i) {
				if(appear[i] == false) {
					jolly = false;
					break;
				}
			}
			
			System.out.println((jolly ? "Jolly" : "Not jolly"));
		}
	}
}
