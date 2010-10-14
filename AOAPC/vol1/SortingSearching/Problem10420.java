//correct, probably didnt even need the hash set, just a running total
package AOAPC.vol1.SortingSearching;

import java.util.*;

public class Problem10420 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		HashMap<String, HashSet<String> > countries = new HashMap<String, HashSet<String> >();
		for(int i = 0; i < n; ++i) {
			String line = sc.nextLine();
			String country = line.substring(0, line.indexOf(" "));
			String name = line.substring(line.indexOf(" ")+1);
			if(!countries.containsKey(country))
				countries.put(country, new HashSet<String>());
			countries.get(country).add(name);
		}
		ArrayList<String> keys = new ArrayList<String>();
		for(Iterator itr = countries.keySet().iterator(); itr.hasNext();)
			keys.add((String)itr.next());
		Collections.sort(keys);
		for(String key : keys) {
			System.out.println(key + " " + countries.get(key).size());
		}
	}
}