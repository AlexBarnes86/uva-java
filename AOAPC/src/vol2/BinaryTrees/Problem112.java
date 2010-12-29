package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Problem112 {

	public static String solve(long total, String str) {
		long sum = 0;
		LinkedList<Long> stack = new LinkedList<Long>();
		str = str.replaceAll("\\(", " \\( ");
		str = str.replaceAll("\\)", " \\) ");
		str = str.replaceAll("S", " S ");
		str = str.replaceAll("E", " E ");
		str = str.trim();
		String [] tokens = str.split("\\s+");
		
		for(int i = 0; i < tokens.length; ++i) {
			String token = tokens[i];
			if(token.equals("(")) {
				continue;
			}
			else if(token.equals(")")) {
				long l = stack.pop();
				sum -= l;
			}
			else if(token.equals("S")) {
				if(sum == total)
					return "yes";
			}
			else if(token.equals("E")) {
				continue;
			}
			else {
				long l = Long.parseLong(token);
				stack.push(l);
				sum += l;
			}
		}
		
		return "no";
	}
	
	public static String readInput(BufferedReader br) throws Exception {

		String sb = "";
		String line;
		int ct = 0;
		while((line = br.readLine()) != null) {
			for(int i = 0; i < line.length(); ++i) {
				if(line.charAt(i) == '(')
					ct++;
				else if(line.charAt(i) == ')')
					ct--;
			}
			sb += line;
			if(ct == 0)
				break;
		}
		
		if(sb.length() == 0)
			return null;
		sb = sb.replaceAll("\\s+", "");
		sb = sb.replaceFirst("\\(", " \\(");
		sb = sb.replaceAll("\\(\\)\\(\\)", "S");
		sb = sb.replaceAll("\\(\\)", "E");
		return sb;
	}
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem112.in"));
		
		String line = "";
		while((line = readInput(br)) != null) {
			String [] tokens = line.split(" ");
			//System.out.println("DEBUG: " + tokens[0] + " " + tokens[1]);
			System.out.println(solve(Long.parseLong(tokens[0]), tokens[1]));
		}
	}
}
