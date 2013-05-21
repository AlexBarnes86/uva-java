package chapter5;

import java.io.File;
import java.util.Scanner;

/*
This problem is very misleading:

First trick:
By definition of logarithms: 2^E = x implies log_2(x) = E.
Since log_2(x) gives us irrational decimal expansion
Thus, there is always a matching value for N - dont even bother with 'no power of 2'

Second trick:
They claim N is not larger than 2147483648. While this is technically true, this extra information is useless.
They don't really give us numbers anywhere near this maximum value in the judges data. This Java solution solves the judges data in about 0.2 seconds
You can test this for yourself by running the code and giving it large values - larger values take much longer than the 3 second time limit to calculate!
Larger values took so long to calculate I didn't even bother waiting for it to finish.

Explanation:
Rather than calculate 2^E and due a brute force comparison, use logarithms with lower and upper bounds
lower bound for 2^E would assume all the missing digits are zero (N*10^k)
upper bound for 2^E would assume the last digit of N is one larger, but again with all missing digits set to 0

Thus we have the following inequality:
N*10^k <= 2^E < (N+1)*10^k

Taking log base 2 of this inequality we find:
log2(N) + k*log2(10) <= E < log2(N+1) + k*log2(10)

Increase k until the equality holds true, the upper bound gives us the solution for N
*/
public class Problem701 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_INPUT = true;
	public static final Scanner sc = getScanner();
	
	final static double LOG2 = Math.log(2.0);
	final static double LOG2_10 = Math.log(10) / LOG2;
	
	public static long solve(long N) {
		int k = (N+"").length()+1;
		
		long lowerBound = (long)((Math.log(N) / LOG2) + k * LOG2_10);
		long upperBound = (long)((Math.log(N+1) / LOG2) + k * LOG2_10);
		
		while(lowerBound == upperBound) {
			k++;
			lowerBound = (long)((Math.log(N) / LOG2) + k * LOG2_10);
			upperBound = (long)((Math.log(N+1) / LOG2) + k * LOG2_10);
		}
		
		return upperBound;
	}
	
	public static void main(String args[]) {
		while(sc.hasNext()) {
			System.out.println(solve(Long.parseLong(sc.nextLine().trim())));
		}
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
				String debugFile = "ProgrammingChallenges/input/chapter5/Problem701.in";
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