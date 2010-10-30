package vol1.SortingSearching;

import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Problem123 {
	public static void debug(String str) {
		//System.out.println("DEBUG: " + str);
	}
	public static void myPrint(String str, String key, int position) {
		debug(str + ", " + key + ", " + position);
		int count = 0;
		String[] tokens = str.split(" ");
		for(int i = 0; i < tokens.length; ++i) {
			if(tokens[i].equals(key)) {
				if(count == position) {
					System.out.print(tokens[i].toUpperCase());
				}
				else
					System.out.print(tokens[i]);
				count++;
			}
			else
				System.out.print(tokens[i]);
			
			if(i != tokens.length - 1)
				System.out.print(" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new FileReader("Problem123.in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = "";
		ArrayList<String> ignore = new ArrayList<String>();
		while(!(line = br.readLine()).equals("::")) {
			ignore.add(line.toLowerCase());
		}
		Collections.sort(ignore);
		ArrayList<String> titles = new ArrayList<String>();
		TreeMap<String, ArrayList<String>> index = new TreeMap<String, ArrayList<String>>();
		while((line = br.readLine()) != null) {
			line = line.toLowerCase();
			String[] words = line.split(" ");
			for(String word : words) {
				if(Collections.binarySearch(ignore, word) >= 0)
					continue;
				if(!index.containsKey(word)) {
					index.put(word, new ArrayList<String>());
					debug("Adding key " + word);
				}
				index.get(word).add(line);
				debug("Key => " + word + " : adding " + line);
			}
		}
		
		for(String key : index.keySet()) {
			String previous = "";
			int position = 0;
			debug("Getting titles for key: " + key + " found " + index.get(key).size());
			for(String title : index.get(key)) {
				
				if(previous.equals(title)) {
					position++;
				}
				else {
					position = 0;
				}
				myPrint(title, key, position);
				
				previous = title;
			}
		}
	}
}
