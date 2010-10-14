//correct
package AOAPC.vol0;

import java.util.*;
import java.math.*;

public class Problem694 {
	public static final BigInteger one = new BigInteger(1+"");
	public static final BigInteger two = new BigInteger(2+"");
	public static final BigInteger three = new BigInteger(3+"");
	
	public static int collatz(int A , int L) {
		BigInteger i = new BigInteger(A+"");
		BigInteger limit = new BigInteger(L+"");
		int terms = 0;
		while(true) {
			if(i.compareTo(limit) > 0)
				break;
			terms++;
			if(i.equals(one))
				break;
			if(i.mod(two).equals(one)) {
				i = i.multiply(three).add(one);
			}
			else
				i = i.divide(two);
		}
		return terms;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 1;
		while(true) {
			int A = sc.nextInt();
			int L = sc.nextInt();
			if(A < 0 && L < 0)
				return;
			String output = String.format("Case %d: A = %d, limit = %d, number of terms = %d", n, A, L, collatz(A, L));
			System.out.println(output);
			n++;
		}
	}
}
