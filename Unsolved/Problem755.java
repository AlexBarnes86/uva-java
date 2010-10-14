//too slow, needs c++ on UVa - bastards
package AOAPC.vol1.SortingSearching;

import java.io.File;
import java.util.*;

public class Problem755 {
	public static String getNumber(String line) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < line.length(); ++i) {
			switch(line.charAt(i)) {
			case 'A': case 'B': case 'C': case 'a': case 'b': case 'c':
				sb.append('2');
				break;
			case 'D': case 'E': case 'F': case 'd': case 'e': case 'f':
				sb.append('3');
				break;
			case 'G': case 'H': case 'I': case 'g': case 'h': case 'i':
				sb.append('4');
				break;
			case 'J': case 'K': case 'L': case 'j': case 'k': case 'l':
				sb.append('5');
				break;
			case 'M': case 'N': case 'O': case 'm': case 'n': case 'o':
				sb.append('6');
				break;
			case 'P': case 'R': case 'S': case 'p': case 'r': case 's':
				sb.append('7');
				break;
			case 'T': case 'U': case 'V': case 't': case 'u': case 'v':
				sb.append('8');
				break;
			case 'W': case 'X': case 'Y': case 'w': case 'x': case 'y':
				sb.append('9');
				break;
			case '1': case '2': case '3': case '4': case '5':
			case '6': case '7': case '8': case '9': case '0':
				sb.append(line.charAt(i));
				break;
			default:
				break;
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		//long start = getSystemTime();
		long start = System.currentTimeMillis();
		Scanner sc = new Scanner(new File("Problem755.input"));
		int N = Integer.parseInt(sc.nextLine());
		sc.nextLine(); //eat blank line
		for(int i = 0; i < N; ++i) {
			HashMap<String, Integer> numbers = new HashMap<String, Integer>();
			int M = Integer.parseInt(sc.nextLine());
			HashSet<String> collisions = new HashSet<String>();
			for(int j = 0; j < M; ++j) {
				String number = getNumber(sc.nextLine());
				if(numbers.containsKey(number)) {
					numbers.put(number, numbers.get(number)+1);
					collisions.add(number);
				}
				else
					numbers.put(number, 1);
			}
			ArrayList<String> keys = new ArrayList<String>();
			for(Iterator<String> itr = collisions.iterator(); itr.hasNext(); ) {
				keys.add(itr.next());
			}
			Collections.sort(keys);
			for(int j = 0; j < keys.size(); ++j) {
				String num = keys.get(j);
				int collide = numbers.get(num);
				System.out.println(num.substring(0, 3)+"-"+num.substring(3)+" "+collide);
			}
			if(keys.size() == 0)
				System.out.println("No duplicates.");
			System.out.println();
			if(sc.hasNext())
				sc.nextLine(); //eat blank line
		}
		//System.out.println("System Time: " + (getSystemTime()-start));
		//System.out.println("Running Time: " + (System.currentTimeMillis()-start));
	}
}
