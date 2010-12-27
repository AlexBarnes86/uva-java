package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Problem297 {
	static class Node {
		ArrayList<Node> children;
		char type;
		
		public Node(char ch) {
			type = ch;
			children = new ArrayList<Node>();
		}
	}
	
	static class QuadTree {
		Node root;
		
		public QuadTree(String line) {
			LinkedList<Node> stack = new LinkedList<Node>();
			Node top = null;
			
			for(int i = 0; i < line.length(); ++i) {
				char ch = line.charAt(i);
				Node next = new Node(ch);
				
				if(ch == 'p') {
					if(root == null) {
						root = next;
					}
					else {
						top.children.add(next);
					}
					stack.push(next);
					top = next;
				}
				else {
					top.children.add(next);
					if(top.children.size() == 4) {
						stack.pop();
						top = stack.peek();
					}
				}
			}
		}
		
		public void add(String line) {
			
		}
		
		public int count(Node n, int level) {
			if(n.type == 'p') {
				int sum = 0;
				for(int i = 0; i < 4; ++i)
					sum += count(n.children.get(i), 4*level);
				return sum;
			}
			else if(n.type == 'f') {
				return 1024 / level;
			}
			return 0;
		}
		
		public int count() {
			return count(root, 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem297.in"));
		int N = Integer.parseInt(br.readLine().trim());
		String line;
		
		for(int i = 0; i < N; ++i) {
			line = br.readLine().trim();
			QuadTree qt = new QuadTree(line);
			line = br.readLine().trim();
			qt.add(line);
			System.out.println("There are " + qt.count() + " black pixels.");
		}
	}
}

