//correct
package vol1.SortingSearching;

import java.io.File;
import java.util.*;

public class Problem299 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);//new File("Problem299.input"));
		long n = sc.nextLong();
		for(long run = 0; run < n; ++run) {
			int L = sc.nextInt();
			int[] ary = new int[L];
			for(int j = 0; j < L; ++j) {
				ary[j] = sc.nextInt();
			}
			
			int x = 1;
		    int swap = 0;
		    for(int i = 0; i < L; i++, x++)
		    {
	            for(int j = 0; j < L-x; j++)
	            {
	                if(ary[j] > ary[j+1])
	                {
	                    int temp = ary[j];
	                    ary[j] = ary[j+1];
	                    ary[j+1] = temp;
	                    swap++;
	                }
	            }
		    }
		    System.out.println("Optimal train swapping takes " + swap + " swaps.");
		}
	}
}
