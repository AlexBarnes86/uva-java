//correct
package vol1.SortingSearching;

import java.util.*;

public class Problem340 {
	public static int count_matches(ArrayList<Integer> solution, ArrayList<Integer> guess) {
		int sum = 0;
		for(int i = 0; i < solution.size(); ++i) {
			if(solution.get(i).equals(guess.get(i)))
				sum++;
		}
		return sum;
	}
	
	public static int count_pairs(ArrayList<Integer> solution, ArrayList<Integer> guess) {
		ArrayList<Integer> copy = (ArrayList<Integer>)solution.clone();
		Collections.sort(copy);
		Collections.sort(guess);
		int sum = 0;
		for(int i = 0; i < guess.size(); ++i) {
			int pos = Collections.binarySearch(copy, guess.get(i));
			if(pos >= 0) {
				sum++;
				copy.remove(pos);
			}
		}
		return sum;		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int game = 1;
		while(true) {
			int N = sc.nextInt();
			if(N == 0)
				break;
			System.out.println("Game " + game + ":");
			ArrayList<Integer> solution = new ArrayList<Integer>();
			for(int i = 0; i < N; ++i) {
				solution.add(sc.nextInt());
			}
			while(true) {
				boolean finished = true;
				ArrayList<Integer> guess = new ArrayList<Integer>();
				for(int i = 0; i < N; ++i) {
					int num = sc.nextInt();
					if(num != 0)
						finished = false;
					guess.add(num);
				}
				if(finished)
					break;
				int matches = count_matches(solution, guess);
				int pairs = count_pairs(solution, guess);
				System.out.println("    ("+matches+","+(pairs-matches)+")");
			}
			game++;
		}
	}
}
