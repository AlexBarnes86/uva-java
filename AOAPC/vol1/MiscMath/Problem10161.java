//correct
package AOAPC.vol1.MiscMath;

import java.util.*;

public class Problem10161 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int n = sc.nextInt();
			if(n == 0)
				break;
			int sqrt = (int)Math.sqrt(n);
			int square = (int)Math.pow(sqrt, 2);
			int k = n - square;
			int i = 0, j = 0;
			if(square % 2 == 0) { //even (up, left)
				i = 1;
				j = sqrt;
				if(n != square) {
					if(k > sqrt+1) {
						i = sqrt+1; 
						j = (sqrt+1)-(n-(square+sqrt+1));
					}
					else {
						i = k;
						j = sqrt+1;
					}
				}
			}
			else { //odd (right, down)
				i = sqrt;
				j = 1;
				if(n != square) {
					if(k > sqrt+1) {
						i = (sqrt+1)-(n-(square+sqrt+1));
						j = sqrt+1;
					}
					else {
						i = sqrt+1;
						j = k;
					}
				}
			}
			System.out.println(j + " " + i);
		}
	}
}
