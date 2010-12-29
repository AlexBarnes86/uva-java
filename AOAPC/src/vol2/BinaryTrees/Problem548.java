package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Problem548 {
	static class Node {
		public long val;
		public Node left, right;
		
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
	}
	
	static class Tree {
		Node root;
		
		private ArrayList<Long> inorder, postorder;
		
		private Node build(int inLeft, int inRight, int postLeft, int postRight) {
			if(inLeft > inRight) {
				if(postLeft == postRight)
					return new Node(inorder.get(postLeft));
				else
					return null;
			}
			if(postLeft > postRight) {
				if(inLeft == inRight)
					return new Node(inorder.get(inLeft));
				else
					return null;
			}
			if(inLeft == inRight)
				return new Node(inorder.get(inLeft));
			if(postLeft == postRight)
				return new Node(inorder.get(postLeft));
			
			long val = postorder.get(postRight);
			Node node = new Node(val);
			
			int idx = inorder.indexOf(val);
			
			node.left = build(inLeft, idx-1, postLeft, idx-1);
			node.right = build(idx+1, inRight, idx, postRight-1);
			
			return node;
		}
		
		public Tree(ArrayList<Long> inorder, ArrayList<Long> postorder) {
			this.inorder = inorder;
			this.postorder = postorder;
			root = build(0, inorder.size()-1, 0, postorder.size()-1);
		}
	}
	
	private static ArrayList<Long> getList(String str) {
		ArrayList<Long> list = new ArrayList<Long>();
		list.ensureCapacity(10000);
		String [] tokens = str.split("\\s+");
		for(String token : tokens) {
			list.add(Long.parseLong(token));
		}
		return list;
	}
	
	public static int solve(String inorder, String postorder) {
		ArrayList<Long> inList = getList(inorder), postList = getList(postorder);

		Tree t = new Tree(inList, postList);
		System.out.println(t.root);
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem548.in"));
		String line1 = "";
		String line2 = "";
		while((line1 = br.readLine()) != null) {
			line1 = line1.trim();
			line2 = br.readLine().trim();
			System.out.println(solve(line1, line2));
		}
	}
}
