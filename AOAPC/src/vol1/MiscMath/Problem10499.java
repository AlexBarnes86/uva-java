package vol1.MiscMath;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem10499 {
	public static void main(String [] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		long n;
		
		while((line = br.readLine()) != null) {
			String [] tokens = line.trim().split("\\s+");
			for(String str : tokens) {
				n = Long.parseLong(str);
				if(n < 0)
					return;
				if(n == 1)
					System.out.println("0%");
				else {
					n = n * 25;
					System.out.println(n + "%");
				}
			}
		}
	}
}
