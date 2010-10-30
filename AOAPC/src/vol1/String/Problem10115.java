package vol1.String;

import java.util.*;

public class Problem10115 {
	public static String replaceFirst(String line, String match, String replace) {
		int pos = line.indexOf(match);
		return line.substring(0, pos) + replace + line.substring(pos+match.length());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		while((n = Integer.parseInt(sc.nextLine())) != 0) {
			ArrayList<String> rule = new ArrayList<String>();
			ArrayList<String> replace = new ArrayList<String>();
			
			for(int i = 0; i < n; ++i) {
				rule.add(sc.nextLine());
				replace.add(sc.nextLine());
			}
			
			String line = sc.nextLine();
			for(int i = 0; i < rule.size(); ++i) {
				while(line.indexOf(rule.get(i)) != -1)
					line = replaceFirst(line, rule.get(i), replace.get(i));
			}
			System.out.println(line);
		}
	}
}
