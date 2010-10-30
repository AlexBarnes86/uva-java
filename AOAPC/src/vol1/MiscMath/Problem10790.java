//correct
package vol1.MiscMath;

import java.util.*;

public class Problem10790 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = 1;
		while(true) {
			long m = sc.nextLong();
			long n = sc.nextLong();
			if(m == 0 && n == 0)
				return;
			System.out.println("Case " + i + ": " + (m*(m-1)/2)*(n*(n-1)/2));
			i++;
		}
	}
}
