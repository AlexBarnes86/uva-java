package AOAPC.vol1.NumberTheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem10110 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while((line = br.readLine()) != null) {
			long n = Long.parseLong(line.trim());
			if(n == 0)
				break;
			long root = (long)Math.sqrt(n);
			if(root*root == n)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}
