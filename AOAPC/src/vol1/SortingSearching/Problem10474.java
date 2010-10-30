//correct
package vol1.SortingSearching;

import java.io.File;
import java.util.*;

public class Problem10474 {
	public static final int MAX_VAL = 10001;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int run = 1;
		while(sc.hasNext()) {
			int n = sc.nextInt();
			int q = sc.nextInt();
			if (n == 0 && q == 0)
				return;
			
			int[] ary = new int[MAX_VAL];
			for(int i = 0; i < n; ++i)
				ary[sc.nextInt()]++;
			int [] marbles = new int[n];
			int pos = 0;
			for(int i = 0; i < MAX_VAL; ++i) {
				for(int j = 0; j < ary[i]; j++) {
					marbles[pos] = i;
					pos++;
					if(pos == n)
						break;
				}
			}

			System.out.println("CASE# " + run + ":");
			for(int i = 0; i < q; ++i) {
				int query = sc.nextInt();
				if(query < 0 || query >= ary.length || ary[query] == 0)
					System.out.println(query + " not found");
				else {
					pos = Arrays.binarySearch(marbles, query);
					while(pos > 0 && marbles[pos-1] == query)
						pos--;
					System.out.println(query + " found at " + (pos+1));
				}
			}
			run++;
		}
	}
}
