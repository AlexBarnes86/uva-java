//correct
package AOAPC.vol1.MiscMath;

import java.util.*;

public class Problem11044 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i = 0; i < N; ++i) {
			int m = sc.nextInt()-2;
			int n = sc.nextInt()-2;
			int sonars = ((int)Math.ceil(m/3.0))*((int)Math.ceil(n/3.0));
			System.out.println(sonars);
		}
	}
}
