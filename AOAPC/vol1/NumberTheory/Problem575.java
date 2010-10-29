package AOAPC.vol1.NumberTheory;

import java.util.Scanner;

public class Problem575 {
	private static final long [] mult = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, 2147483647, 4294967295L};
	public static long skewToDec(String n) {
		long sum = 0;
		for(int i = n.length()-1; i >= 0; --i) {
			switch(n.charAt(i)) {
			case '1':
				sum += mult[n.length()-1-i];
				break;
			case '2':
				sum += 2*mult[n.length()-1-i];
				break;
			}
		}
		return sum;
	}
	
	public static void main(String [] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String line = "";
		while(!(line = sc.nextLine().trim()).equals("0")) {
			System.out.println(skewToDec(line));
		}
	}
}
