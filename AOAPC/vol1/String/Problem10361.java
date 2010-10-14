//correct
package AOAPC.vol1.String;

import java.util.*;
import java.util.regex.*;

public class Problem10361 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		Pattern pattern = Pattern.compile("([^<]*)<([^>]*)>([^<]*)<([^>]*)>(.*)");
		for(int i = 0; i < n; ++i) {
			String first = sc.nextLine();
			String second = sc.nextLine();
			Matcher matcher = pattern.matcher(first);
			matcher.find();
			String[] g = new String[5];
			for(int j = 1; j < 6; ++j)
				g[j-1] = matcher.group(j);
			System.out.println(g[0]+g[1]+g[2]+g[3]+g[4]);
			System.out.println(second.replace("...", g[3]+g[2]+g[1]+g[4]));
		}
	}
}
