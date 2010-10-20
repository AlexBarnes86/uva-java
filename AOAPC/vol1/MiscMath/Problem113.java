package AOAPC.vol1.MiscMath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Problem113 {
	public static BigInteger solve(final int n, final BigInteger p, BigInteger low, BigInteger high) {
		BigInteger k = low.add(high).divide(new BigInteger("2"));
		BigInteger pow = k.pow(n);
		int res = pow.compareTo(p);
		switch(res) {
		case 0:
			return k;
		case 1:
			return solve(n, p, low, k);
		default: case -1:
			return solve(n, p, k.add(BigInteger.ONE), high);
		}
	}
	
	public static void main(String [] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while((line = br.readLine()) != null) {
			int n = Integer.parseInt(line);
			BigInteger p = new BigInteger(br.readLine());
			
			System.out.println(solve(n, p, BigInteger.ONE, new BigInteger("1000000000")));
		}
	}
}
