package AOAPC.vol1.BigNumber;

import java.math.*;
import java.util.*;

public class Problem424 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger sum = BigInteger.ZERO;
		while(sc.hasNext()) {
			sum = sum.add(new BigInteger(sc.nextLine()));
		}
		System.out.println(sum);
	}
}
