//correct
package AOAPC.vol1.SortingSearching;

import java.util.*;

public class Problem10785 {
	public static String getName(int L) {
		final char[] vowels = {'A', 'U', 'E', 'O', 'I'};
		final char[] consonants = {'J', 'S', 'B', 'K', 'T', 'C', 'L', 'D', 'M', 'V', 'N', 'W', 'F', 'X', 'G', 'P', 'Y', 'H', 'Q', 'Z', 'R'};
		final int MAX_CONSONANT = 5;
		final int MAX_VOWEL = 21;
		ArrayList<Character> c_list = new ArrayList<Character>();
		ArrayList<Character> v_list = new ArrayList<Character>();
		for(int i = 0; i < L; ++i) {
			if(i%2 == 0)
				v_list.add(vowels[i/(2*MAX_VOWEL)]);
			else
				c_list.add(consonants[i/(2*MAX_CONSONANT)]);
		}
		Collections.sort(v_list);
		Collections.sort(c_list);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < v_list.size(); ++i) {
			sb.append(v_list.get(i));
			if(i < c_list.size())
				sb.append(c_list.get(i));
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; ++i) {
			System.out.println("Case " + (i+1) + ": " + getName(sc.nextInt()));
		}
	}
}
