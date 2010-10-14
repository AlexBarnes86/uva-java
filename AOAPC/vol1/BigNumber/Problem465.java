//correct
package AOAPC.vol1.BigNumber;

import java.util.*;
import java.math.BigInteger;

public class Problem465 {
	public static final boolean ADD = true;
	public static final boolean MULT = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean operator;
		BigInteger max = new BigInteger(Integer.MAX_VALUE+"");
		while(sc.hasNext()) {
			String line = sc.nextLine();
			if(line.contains("+"))
				operator = ADD;
			else
				operator = MULT;
			String[] tokens = line.trim().split("\\s*(\\+|\\*)\\s*");
			BigInteger first = new BigInteger(tokens[0]);
			BigInteger second = new BigInteger(tokens[1]);
			System.out.println(line);
			if(first.compareTo(max) > 0)
				System.out.println("first number too big");
			if(second.compareTo(max) > 0)
				System.out.println("second number too big");
			if(operator == ADD && first.add(second).compareTo(max) > 0 ||
			   operator == MULT && first.multiply(second).compareTo(max) > 0)
				System.out.println("result too big");
		}
	}
}
