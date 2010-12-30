package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Problem548 {
	static class Node {
		public long val;
		public Node left, right;
		public long psum;
		
		public Node(long val) {
			this.val = val;
		}
		
		private static void inorder(Node n, StringBuilder sb) {
			if(n == null)
				return;
			inorder(n.left, sb);
			sb.append(n.val + " ");
			inorder(n.right, sb);
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			inorder(this, sb);
			return sb.toString();
		}
		
		private static void getLeaves(Node n, LinkedList<Node> leaves, long sum) {
			if(n == null)
				return;
			
			n.psum = sum+n.val;
			
			getLeaves(n.left, leaves, n.psum);
			if(n.left == null && n.right == null)
				leaves.add(n);
			getLeaves(n.right, leaves, n.psum);
		}
		
		public LinkedList<Node> getLeaves() {
			LinkedList<Node> ll = new LinkedList<Node>();
			getLeaves(this, ll, 0);
			return ll;
		}
		
		public Node largestPathLeaf() {
			LinkedList<Node> leaves = getLeaves();
			Node min = leaves.get(0);
			//System.out.print("leaves: ");
			for(Node n : leaves) {
				//System.out.print(n.val + ":" + n.psum + " ");
				if(n.psum < min.psum)
					min = n;
			}
			//System.out.println();
			return min;
		}
	}
	
	static class Tree {
		Node root;
		
		private Node build(List<Long> inorder, List<Long> postorder) {
			if(postorder.isEmpty())
				return null;
			
			Node node = new Node(postorder.get(postorder.size()-1));
			if(inorder.size() == 1)
				return node;
			
			int idx = inorder.indexOf(node.val);
			
			if(idx == -1 || idx+1 > inorder.size())
				return node;
			
			List<Long> left = inorder.subList(0, idx);
			List<Long> right = inorder.subList(idx+1, inorder.size());
			
			node.left = build(left, postorder.subList(0, idx));
			node.right = build(right, postorder.subList(idx, postorder.size()-1));
			
			return node;
		}
		
		public Tree(LinkedList<Long> inorder, LinkedList<Long> postorder) {
			root = build(inorder, postorder);
		}
	}
	
	private static LinkedList<Long> getList(String str) {
		LinkedList<Long> list = new LinkedList<Long>();
		String [] tokens = str.split("\\s+");
		for(String token : tokens) {
			list.add(Long.parseLong(token));
		}
		return list;
	}
	
	public static long solve(String inorder, String postorder) {
		LinkedList<Long> inList = getList(inorder), postList = getList(postorder);

		Tree t = new Tree(inList, postList);
		//System.out.println(t.root);
		return t.root.largestPathLeaf().val;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem548.in"));
		String line1 = "";
		String line2 = "";
		while((line1 = br.readLine()) != null) {
			line1 = line1.trim();
			line2 = br.readLine().trim();
			System.out.println(solve(line1, line2));
		}
	}
}
