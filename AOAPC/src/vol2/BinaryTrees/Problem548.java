package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem548 {

	static class Node {
		public long val;
		public long psum;
		public Node left, right;
		
		public Node(long val) {
			this.val = val;
		}
		
		public String toString() {
			return val+"";
		}
		
		public static void inorder(Node n) {
			if(n == null)
				return;
			inorder(n.left);
			System.out.print(n.val + " ");
			inorder(n.right);
		}
	}
	
	static class Tree {
		long smallest = Long.MAX_VALUE;
		long smallest_val;
		Node root;
		
		private Node build(List<Long> inorder, List<Long> postorder, long sum) {
			if(postorder.size() == 0)
				return null;
			
			long val = postorder.get(postorder.size()-1);
			int idx = inorder.indexOf(val);
			
//			System.out.println("inorder: " + inorder);
//			System.out.println("postorder: " + postorder);
//			System.out.println("val: " + val + " idx: " + idx);
			
			if(idx < 0)
				return null;
			
			Node n = new Node(val);
			n.psum = sum + val;
			
			n.left = build(inorder.subList(0, idx), postorder.subList(0, idx), n.psum);
			n.right = build(inorder.subList(idx+1, inorder.size()), postorder.subList(idx, postorder.size()-1), n.psum);
			
			if(n.left == null && n.right == null) { //leaf node
				if(n.psum < smallest) {
					smallest = n.psum;
					smallest_val = n.val;
				}
			}
			
			return n;
		}
		
		public Tree(ArrayList<Long> inorder, ArrayList<Long> postorder) {
			root = build(inorder, postorder, 0);
//			Node.inorder(root);
		}
		
		public long getSmallest() {
			return smallest_val;
		}
	}
	
	private static ArrayList<Long> getList(String str) {
		ArrayList<Long> list = new ArrayList<Long>();
		String [] tokens = str.split("\\s+");
		for(String token : tokens) {
			list.add(Long.parseLong(token));
		}
		return list;
	}
	
	public static long solve(String inorder, String postorder) {
		ArrayList<Long> inList = getList(inorder), postList = getList(postorder);

		Tree t = new Tree(inList, postList);
		return t.getSmallest();
	}
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem548-evil.in"));
		String line1 = "";
		String line2 = "";
		while((line1 = br.readLine()) != null) {
			line1 = line1.trim();
			line2 = br.readLine().trim();
//			System.out.println(line1);
//			System.out.println(line2);
			System.out.println(solve(line1, line2));
			//System.out.println(solve(line1, line2));
		}
		System.out.println("Real time: " + (System.currentTimeMillis()-start) + "ms");
	}
}
