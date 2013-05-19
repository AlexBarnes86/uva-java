//correct
package vol1.SortingSearching;

import java.util.*;

public class Problem156 {
	public static String make_key(String word) {
		char[] letters = word.toLowerCase().toCharArray();
		Arrays.sort(letters);
		return new String(letters);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> words = new ArrayList<String>();
		HashMap <String, Integer> counts = new HashMap<String, Integer>();
		
		while(true) {
			String line = sc.nextLine();
			if(line.equals("#"))
				break;
			line = line.replaceAll(" +", " ").trim();
			if(line.isEmpty())
				continue;
			String[] tokens = line.split(" ");
			for(int i = 0; i < tokens.length; ++i) {
				words.add(tokens[i]);
				String key = make_key(tokens[i]);
				if(counts.containsKey(key))
					counts.put(key, counts.get(key)+1);
				else
					counts.put(key, 1);
			}
		}
		
		Collections.sort(words);
		for(int i = 0; i < words.size(); ++i)
			if(counts.get(make_key(words.get(i))) == 1)
				System.out.println(words.get(i));
	}
}
