//generates test data for problem 755
package vol1.SortingSearching;

import java.io.*;
import java.util.*;

public class Test755 {
	public static void main(String[] args) throws Exception {
		PrintWriter pw = new PrintWriter(new File("Problem755.input"));
		pw.println(1);
		pw.println();
		pw.println(100000);
		Random rand = new Random();
		for(int i = 0; i < 100000; ++i) {
			StringBuilder sb = new StringBuilder();
			int digits = 0;
			while(digits != 7) {
				if(rand.nextInt(3) == 0) {
					sb.append('-');
				}
				else {
					digits++;
					if(rand.nextInt(2) == 0)
						sb.append(rand.nextInt(9));
					else
						sb.append((char)(65+rand.nextInt(26)));
				}
			}
			pw.println(sb.toString());
			System.out.println(i + ": " + sb.toString());
		}
		pw.flush();
	}
}
