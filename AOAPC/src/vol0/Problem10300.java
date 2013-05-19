//correct
package vol0;

import java.util.*;

public class Problem10300 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; ++i) {
			int f = sc.nextInt();
			double sum = 0;
			for(int j = 0; j < f; ++j) {
				double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble();
				sum += a*c;
			}
			System.out.println((int)sum);
		}
	}
}
