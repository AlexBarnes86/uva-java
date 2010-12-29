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
		
		public void add(Node n) {
			if(n.type == 'f')
				type = 'f';
			else if(n.type == 'p' && type != 'f') {
				if(type == 'p') {
					for(int i = 0; i < 4; ++i) {
						children.get(i).add(n.children.get(i));
					}
				}
				else {
					type = 'p';
					this.children = n.children;
				}
			}
		}
		
		public String toString() {
			return type + "";
		}
	}
	
	static class QuadTree {
		Node root;
		
		public QuadTree(String line) {
			LinkedList<Node> stack = new LinkedList<Node>();
			
			for(int i = 0; i < line.length(); ++i) {
				char ch = line.charAt(i);
				Node next = new Node(ch);
				
				if(ch == 'p') {
					if(root == null) {
						root = next;
					}
					else {
						stack.peek().children.add(next);
						if(stack.peek().children.size() == 4) {
							stack.pop();
						}
					}
					stack.push(next);
				}
				else {
					if(stack.isEmpty()) {
						root = new Node(ch);
						break;
					}
					stack.peek().children.add(next);
					if(stack.peek().children.size() == 4) {
						stack.pop();
					}
				}
			}
		}
		
		public void add(QuadTree t) {
			root.add(t.root);
		}
		
		public int count(Node n, int level) {
			if(n.type == 'p') {
				int sum = 0;
				if(n.children.size() != 4) {
					System.out.println("level: " + level + " has " + n.children.size() + " nodes");
				}
				for(int i = 0; i < 4; ++i)
					sum += count(n.children.get(i), level+1);
				return sum;
			}
			
			else if(n.type == 'f') {
				switch(level) {
				case 0:
					return 1024;
				case 1:
					return 256;
				case 2:
					return 64;
				case 3:
					return 16;
				case 4:
					return 4;
				case 5:
					return 1;
				}
				
				throw new RuntimeException("Wrong"); //should never reach here
			}
			
			return 0;
		}
		
		public int count() {
			return count(root, 0);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem297.in"));
		int N = Integer.parseInt(br.readLine().trim());
		String line;
		
		for(int i = 0; i < N; ++i) {
			line = br.readLine().trim();
			QuadTree qt = new QuadTree(line);
//			System.out.println("DEBUG: " + qt.count());
			line = br.readLine().trim();
			QuadTree qt2 = new QuadTree(line);
			qt.add(qt2);
			System.out.println("There are " + qt.count() + " black pixels.");
		}
	}
}
