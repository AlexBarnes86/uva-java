//correct
package vol0;

import java.util.*;

public class Problem490 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> lines = new ArrayList<String>();
		int max_length = 0;
		while(sc.hasNext()) {
			String line = sc.nextLine();
			lines.add(line);
			if(line.length() > max_length)
				max_length = line.length();
		}
		Collections.reverse(lines);
		for(int i = 0; i < max_length; ++i) {
			for(int j = 0; j < lines.size(); ++j) {
				String line = lines.get(j);
				if(line.length() <= i)
					System.out.print(" ");
				else
					System.out.print(line.charAt(i));
			}
			System.out.println();
		}
	}
}
