package AOAPC.vol1.NumberTheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem408 {
	public static boolean coprime(long a, long b) {
		long temp;
		if (a < b) {
			temp = b;
			b = a;
			a = temp;
		}

		while (b > 0) {
			temp = a % b;
			a = b;
			b = temp;
		}

		if (a == 1)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while((line = br.readLine()) != null) {
			String[] tokens = line.trim().split("\\s+");
			long S = Long.parseLong(tokens[0]);
			long M = Long.parseLong(tokens[1]);
			if(coprime(S, M))
				System.out.printf("%10d%10d    Good Choice\n", S, M);
			else
				System.out.printf("%10d%10d    Bad Choice\n", S, M);
			System.out.println();
		}
	}
}
