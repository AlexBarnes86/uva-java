//correct
package AOAPC.vol0;

import java.util.*;

public class Problem10055 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			long x = sc.nextLong(), y = sc.nextLong();
			System.out.println(Math.abs(y-x));
		}
	}
}