package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;

public class Problem712 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem712.in"));
		String line = "";
		int ct = 1;
		
		while(true) {
			int depth = Integer.parseInt(br.readLine().trim());
			if(depth == 0)
				break;
			
			line = br.readLine().trim(); //ordering
			line = line.replaceAll("x", "");
			String [] tokens = line.split("\\s+");
			int [] ordering = new int [tokens.length];
			for(int i = 0; i < tokens.length; ++i) {
				ordering[i] = Integer.parseInt(tokens[i])-1;
			}
			
			String leaves = br.readLine().trim();
			
			int vva_ct = Integer.parseInt(br.readLine().trim());
			
			System.out.println("S-Tree #" + ct + ":");
			StringBuilder output = new StringBuilder();
			for(int i = 0; i < vva_ct; ++i) {
				StringBuilder sb = new StringBuilder();
				line = br.readLine().trim();
				for(int j = 0; j < ordering.length; ++j) {
					sb.append(line.charAt(ordering[j]));
				}
				line = sb.toString();
				output.append(leaves.charAt(Integer.parseInt(line, 2)));
			}
			
			System.out.println(output.toString());
			System.out.println();
			ct++;
		}
	}
}
