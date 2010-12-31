package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Problem699 {
	static class Node {
		Node left, right;
		int pos;
		long val;
		
		public Node(long val, int pos) {
			this.val = val;
			this.pos = pos;
		}
	}
	
	public static Node build(LinkedList<Long> list, int pos, HashMap<Integer, Long> leaves) {
		long val = list.removeFirst();
		if(val == -1)
			return null;
		
		Node n = new Node(val, pos);
		
		if(leaves.get(pos) == null)
			leaves.put(pos, val);
		else
			leaves.put(pos, leaves.get(pos)+val);
		
		n.left = build(list, pos-1, leaves);
		n.right = build(list, pos+1, leaves);
		return n;
	}
	
	public static String solve(LinkedList<Long> input) {
		HashMap<Integer, Long> leaves = new HashMap<Integer, Long>();
		Node root = build(input, 0, leaves);
		if(root == null)
			return null;
		ArrayList<Integer> keys = new ArrayList<Integer>();
		for(Integer i : leaves.keySet())
			keys.add(i);
		Collections.sort(keys);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < keys.size(); ++i) {
			sb.append(leaves.get(keys.get(i)));
			if(i != keys.size()-1)
				sb.append(" ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem699-2.in"));
		String line = "";
		int ct = 1;
		
		LinkedList<Long> input = new LinkedList<Long>();
		while((line = br.readLine()) != null) {
			line = line.trim();
			if(line.isEmpty())
				continue;
			String [] tokens = line.split("\\s+");
			for(String token : tokens) {
				input.add(Long.parseLong(token));
			}
		}

		while(true) {
			line = solve(input);
			if(line == null)
				break;
			System.out.println("Case " + ct + ":");
			System.out.println(line);
			System.out.println();
			ct++;
		}
	}
}
