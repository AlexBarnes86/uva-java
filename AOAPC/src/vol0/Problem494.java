//correct
package vol0;

import java.util.*;

public class Problem494 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			String line = sc.nextLine();
			line = line.replaceAll("[^a-zA-Z ]", " ");
			line = line.trim().replaceAll("\\s+", " ");
			String[] tokens = line.split(" ");
			System.out.println(tokens.length);
		}
	}
}
