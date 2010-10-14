package AOAPC.vol1.BigNumber;

import java.util.*;
import java.math.*;

public class Problem10106 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			BigInteger first = new BigInteger(sc.nextLine());
			System.out.println(first.multiply(new BigInteger(sc.nextLine())));
		}
	}
}
