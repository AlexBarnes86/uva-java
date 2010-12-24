package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Problem11234 {
	static class Node {
		public Node right, left;
		public char val;
		public boolean visited;
		
		public Node() {}
		public Node(char val) {
			this.val = val;
		}
	}
	
	static class Tree {
		Node root;
		
		public Tree(String expr) { //accepts postfix notation
			root = new Node(expr.charAt(expr.length()-1));
			LinkedList<Node> rightStack = new LinkedList<Node>();
			Node n = root;
			
			for(int i = expr.length()-2; i >= 0; --i) {
				char ch = expr.charAt(i);
				if(ch <= 'Z') {
					if(n.left == null) {
						rightStack.push(n);
						n.left = new Node(ch);
						n = n.left;
					} else if(n.right == null) {
						n.right = new Node(ch);
						n = n.right;
					} else {
						n = rightStack.pop();
						n.right = new Node(ch);
						n = n.right;
					}
				} else {
					if(n.left == null) {
						n.left = new Node(ch);
					}
					else {
						n.right = new Node(ch);
						if(rightStack.size() > 0)
							n = rightStack.pop();
					}
				}
			}
		}
		
		public String reversePrefix() {
			StringBuilder sb = new StringBuilder();
			LinkedList<Node> queue = new LinkedList<Node>();
			queue.addFirst(root);
			while(!queue.isEmpty()) {
				Node n = queue.removeLast();
				sb.append(n.val);
				if(n.right != null)
					queue.addFirst(n.right);
				if(n.left != null)
					queue.addFirst(n.left);
			}
			return sb.reverse().toString();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem11234.in"));
		String line;
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			line = br.readLine();
			Tree t = new Tree(line);
			System.out.println(t.reversePrefix());
		}
	}
}
