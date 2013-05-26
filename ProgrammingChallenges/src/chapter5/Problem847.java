package chapter5;

import java.io.PrintWriter;
import java.util.Scanner;

//Starting from N if we give the opponent any number between N/9 and N-1 then they will win
// 2 is the lowest number that can be used to force the opponent to lose
public class Problem847 {
	public static String solve(long N) {
		long total = 1;
		boolean ollie = true;
		
		while (total < N) {
			if (ollie) {
				total *= 9;
				ollie = false;
			}
			
			else {
				total *= 2;
				ollie = true;
			}
		}
		
		if (ollie) {
			return "Ollie wins.";
		}
		else {
			return "Stan wins.";
		}
	}
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		while(sc.hasNext()) {
			pw.println(solve(Long.parseLong(sc.nextLine())));
		}
		pw.flush();
	}
}
