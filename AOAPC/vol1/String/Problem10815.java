//correct
package AOAPC.vol1.String;

import java.util.*;

public class Problem10815 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashSet<String> words = new HashSet<String>();
		while(sc.hasNext()) {
			String line = sc.next().toLowerCase();
			String [] tokens = line.replaceAll("[^a-z]", " ").replaceAll("  +", " ").trim().split(" ");
			if(tokens == null || tokens.length == 0)
				continue;
			for(String word : tokens) {
				if(word == null || word.isEmpty())
					continue;
				words.add(word);
			}
		}
		ArrayList<String> dict = new ArrayList<String>();
		for(Iterator<String> itr = words.iterator(); itr.hasNext();)
			dict.add(itr.next());
		Collections.sort(dict);
		for(int i = 0; i < dict.size(); ++i)
			System.out.println(dict.get(i));
	}
}
