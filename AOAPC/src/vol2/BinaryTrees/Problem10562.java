package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problem10562 {
	public static class Node {
		char label;
		ArrayList<Node> children = new ArrayList<Node>();
		
		public Node(char label) {
			this.label = label;
		}
		
		public Node readTree(char[][] input, int row, int col) {
			char in = input[row][col];
			if(in == '-' || in == ' ' || in == '|') {
				return null; //no tree rooted at this position
			}
			Node root = new Node(in);
			row+=2;
			if(row >= 199)
				return root;
			
			while(col > 0 && input[row][col] == '-') {
				col--; //shift the cursor all the way to the beginning of the dash marks
			}
			col++;
			while(input[row][col] == '-') {
				in = input[row+1][col];
				if(in == '-' || in == ' ' || in == '|') {
					continue;
				}
				root.children.add(readTree(input, row, col));
				col++;
			}
			
			return root;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem10562.in"));
		int T = Integer.parseInt(br.readLine().trim());
		for(int t = 0; t < T; ++t) {
			char[][] input = readInput(br);
			printAry(input);
		}
	}
	
	private static char[][] readInput(BufferedReader br) throws Exception {
		String line = "";
		char[][] input = new char[200][200];
		boolean read = true;
		for(int i = 0; i < 200; ++i) {
			if(read) {
				line = br.readLine();
				if(line.trim().equals("#")) {
					read = false;
					line = "";
				}
			}
			for(int j = 0; j < line.length(); ++j) {
				input[i][j] = line.charAt(j);
			}
			for(int j = line.length(); j < 200; ++j) {
				input[i][j] = ' ';
			}
		}
		
		return input;
	}
	
	private static void printAry(char[][] input) {
		for(int i = 0; i < input.length; ++i) {
			for(int j = 0; j < input[i].length; ++j) {
				System.out.print(input[i][j]);
			}
			System.out.println();
		}
	}
}
