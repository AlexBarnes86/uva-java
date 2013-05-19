//correct
package chapter4;

import java.util.*;

public class Problem120 {
	public static void flip(ArrayList<Integer> pancakes, int pos) {
		ArrayList<Integer> stack = (ArrayList<Integer>)pancakes.clone();
		for(int i = 0; i <= pos; ++i) {
			pancakes.set(i, stack.get(pos-i));
		}
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			String tokens[] = sc.nextLine().split(" ");
			ArrayList<Integer> pancakes = new ArrayList<Integer>();
			ArrayList<Integer> flips = new ArrayList<Integer>();
			for(int i = 0; i < tokens.length; ++i) {
				pancakes.add(new Integer(tokens[i]));
			}
			//output original stack
			for(int i = 0; i < pancakes.size()-1; ++i)
				System.out.print(pancakes.get(i) + " ");
			System.out.println(pancakes.get(pancakes.size()-1));
			
			ArrayList<Integer> sorted = (ArrayList<Integer>)pancakes.clone();
			Collections.sort(sorted);
			for(int n = sorted.size()-1; n > 0; --n) {
				//System.out.println("Sorting: " + pancakes);
				if(pancakes.get(n) == sorted.get(n))
					continue;
				int pos = pancakes.indexOf(sorted.get(n));
				if(pos != 0) {
					flip(pancakes, pos);
					flips.add(pancakes.size() - pos);
				}
				flip(pancakes, n);
				flips.add(pancakes.size() - n);
			}
			//output flips
			for(int i = 0; i < flips.size(); ++i) 
				System.out.print(flips.get(i)+ " ");
			System.out.println(0);
		}
	}
}
