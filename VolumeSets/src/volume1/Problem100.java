package volume1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Problem100 {
	static int [] LUT = new int[1000000];
	
	public static int solve(int i) {
		long val = i;
		int ct = 1;
		while(val != 1) {
//			System.out.print(val + ", ");
			if(val < 1000000 && LUT[(int)val-1] != 0) {
				LUT[i-1] = ct + LUT[(int)val-1] -1;
//				System.out.println("LUT\n" + i + ": " + ct + "\n");
				return LUT[i-1];
			}
			if(val % 2 == 0)
				val/=2;
			else
				val = (val*3)+1;
			ct++;
		}
		LUT[i-1] = ct;
//		System.out.println("1\n" + i + ": " + ct + "\n");
		return ct;
	}
	
	public static void main(String [] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/volume1/Problem100.in"));
		String line = "";
		
		//test
//		for(int i = 1; i < 1000001; ++i) {
//			System.out.println(i + ": " + solve(i) + " " + solve(i));
//		}
		
		while((line = br.readLine()) != null) {
			String tokens[] = line.trim().split("\\s+");
			int i = Integer.parseInt(tokens[0]);
			int j = Integer.parseInt(tokens[1]);
			int low = (i<j)?i:j;
			int high = (i<j)?j:i;
			int max = 0;
			for(int k = low; k <= high; k++) {
				int cycles = solve(k);
				if(cycles > max)
					max = cycles;
			}
			System.out.println(i + " " + j + " " + max);
		}
	}
}