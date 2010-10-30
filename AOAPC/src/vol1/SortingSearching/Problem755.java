package vol1.SortingSearching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Problem755 {
	public static String standardize(String line) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < line.length(); ++i) {
			char ch = line.charAt(i);
			switch(ch) {
			case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '0':
				sb.append(ch);
				break;
			case 'A': case 'B': case 'C':
				sb.append('2');
				break;
			case 'D': case 'E': case 'F':
				sb.append('3');
				break;
			case 'G': case 'H': case 'I':
				sb.append('4');
				break;
			case 'J': case 'K': case 'L':
				sb.append('5');
				break;
			case 'M': case 'N': case 'O':
				sb.append('6');
				break;
			case 'P': case 'R': case 'S':
				sb.append('7');
				break;
			case 'T': case 'U': case 'V':
				sb.append('8');
				break;
			case 'W': case 'X': case 'Y':
				sb.append('9');
				break;
			}
		}
		return sb.toString();
	}
	
	public static void solve(BufferedReader br) throws Exception {
		int N = Integer.parseInt(br.readLine());
		TreeMap<String, Integer> hm = new TreeMap<String, Integer>();
		for(int i = 0; i < N; ++i) {
			String line = br.readLine();
			String number = standardize(line);
			if(hm.containsKey(number)) {
				hm.put(number, hm.get(number)+1);
			} else {
				hm.put(number, new Integer(1));
			}
		}
		boolean output = false;
		for(String number : hm.keySet()) {
			int repeats = hm.get(number).intValue();
			if(repeats > 1) {
				System.out.println(number.substring(0, 3) + '-' + number.substring(3) + " " + repeats);
				output = true;
			}
		}
		if(!output) {
			System.out.println("No duplicates.");
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			br.readLine(); //read blank line before each test case
			solve(br);
			if(i != N-1)
				System.out.println(); //print blank line between datasets
		}
	}
}
