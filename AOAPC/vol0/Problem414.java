//correct
package AOAPC.vol0;

import java.util.*;

public class Problem414 {
	public static int count(String line, char ch) {
		int sum = 0;
		for(int i = 0; i < line.length(); ++i)
			if(line.charAt(i) == ch)
				sum++;
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int n = Integer.parseInt(sc.nextLine());
			if(n == 0)
				return;
			ArrayList<Integer> spaces = new ArrayList<Integer>();
			for(int i = 0; i < n; ++i) {
				String line = sc.nextLine();
				spaces.add(count(line, ' '));
			}
			Collections.sort(spaces);
			int smallest = spaces.get(0);
			int sum = 0;
			for(int i = 0; i < spaces.size(); ++i)
				sum += spaces.get(i) - smallest;
			System.out.println(sum);
		}
	}
}
