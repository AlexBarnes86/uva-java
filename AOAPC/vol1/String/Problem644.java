//correct
package AOAPC.vol1.String;

import java.util.*;

public class Problem644 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 1;
		while(sc.hasNext()) {
			String line;
			ArrayList<String> codes = new ArrayList<String>();
			while(!(line = sc.nextLine()).equals("9")) {
				codes.add(line);
			}
			Collections.sort(codes);
			boolean immediate = true;
			for(int i = 0; i < codes.size()-1; ++i) {
				String first = codes.get(i);
				String second = codes.get(i+1);
				if(second.indexOf(first) == 0) {
					immediate = false;
					break;
				}
			}
			if(immediate == true)
				System.out.println("Set " + n + " is immediately decodable");
			else
				System.out.println("Set " + n + " is not immediately decodable");
			n++;
		}
	}
}
