//correct
package vol1.String;

import java.io.File;
import java.util.*;
import java.util.regex.*;

public class Problem409 {
	public static int count(String line, ArrayList<String> keywords) {
		int sum = 0;
		for(int i = 0; i < keywords.size(); ++i) {
			Pattern pattern = Pattern.compile("\\b"+keywords.get(i)+"\\b");
			Matcher matcher = pattern.matcher(line.toLowerCase());
			while(matcher.find()) {
				//System.out.println("Found: " + matcher.group(0));
				sum++;
			}
		}
		//System.out.println("Sum: " + sum);
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = 1;
		while(sc.hasNext()) {
			//if(n != 1)
			//	System.out.println(); //omfg, WA due to this...
			String[] tokens = sc.nextLine().split(" ");
			int K = Integer.parseInt(tokens[0]), E = Integer.parseInt(tokens[1]);
			ArrayList<String> keywords = new ArrayList<String>();
			for(int i = 0; i < K; ++i) {
				keywords.add(sc.nextLine().toLowerCase());
			}
			//need to preserve order
			//HashMap<String, Integer> excuses = new HashMap<String, Integer>();
			ArrayList<String> excuses = new ArrayList<String>();
			ArrayList<Integer> excuse_ct = new ArrayList<Integer>(); 
			int highest = 0;
			for(int i = 0; i < E; ++i) {
				String excuse = sc.nextLine();
				int ct = count(excuse, keywords);
				excuses.add(excuse);
				excuse_ct.add(ct);
				if(highest < ct)
					highest = ct;
			}
			System.out.println("Excuse Set #" + n);
			for(int i = 0; i < excuses.size(); ++i) {
				String excuse = excuses.get(i);
				int ct = excuse_ct.get(i);
				if(ct == highest) 
					System.out.println(excuse);
			}
			n++;
			System.out.println(); //omfg
		}
	}
}
