package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problem101 {
	static class Block {
		public Block(int n) {
			id = n;
		}
		
		public int id;
		public Block next = null, prev = null;
		
		public String toString() {
			if(next == null)
				return id + "";
			return id + " " + next;
		}
		
		public boolean below(Block block) {
			Block temp = this;
			while(temp != null) {
				if(temp == block)
					return true;
				temp = temp.next;
			}
			return false;
		}
	}
	
	public static Block find(ArrayList<Block> list, int id) {
		for(int i = 0; i < list.size(); ++i) {
			Block block = list.get(i);
			while(block != null) {
				if(block.id == id)
					return block;
				block = block.next;
			}
		}
		return null;
	}
	
	public static void clearAbove(ArrayList<Block> list, Block block) {
		Block temp = block.next;
		block.next = null;
		block = temp;
		while(block != null) {
			list.set(block.id, block);
			temp = block.next;
			block.prev = null;
			block.next = null;
			block = temp;
		}
	}
	
	public static void clearUpTo(ArrayList<Block> list, Block block, Block term) {
		block = block.next;
		//System.out.print("DEBUG: clearing from " + block.id + " to " + term.id + " (or null)");
		while(block != null) {
			if(block == term) {
				//System.out.println();
				return;
			}
			//System.out.print(block.id + " ");
			block.prev.next = block.next;
			list.set(block.id, block);
			Block temp = block.next;
			if(temp != null)
				temp.prev = block.prev;
			block.prev = null;
			block.next = null;
			block = temp;
		}
		//System.out.println();
	}
	
	public static void moveAOntoB(ArrayList<Block> list, int a, int b) {
		//System.out.println("DEBUG: Move " + a + " onto " + b);
		Block A = find(list, a);
		Block B = find(list, b);
		
		if(A.below(B)) {
			return;
		}
		
		clearAbove(list, A);
		clearAbove(list, B);
		
		if(A.prev != null)
			A.prev.next = A.next;
		else
			list.set(A.id, null);
		
		B.next = A;
		A.prev = B;
		
		//printList(list);
	}
	
	public static void moveAOverB(ArrayList<Block> list, int a, int b) {
		//System.out.println("DEBUG: Move " + a + " over " + b);
		Block A = find(list, a);
		Block B = find(list, b);
		
		if(A.below(B)) {
			return;
		}
		
		clearAbove(list, A);
		while(B.next != null) {
			B = B.next;
			if(B == A)
				return;
		}
		
		if(A.prev != null)
			A.prev.next = null;
		else
			list.set(A.id, null);
		
		B.next = A;
		A.prev = B;
		//printList(list);
	}
	
	//TODO: Pile functions contain a null pointer issue
	public static void pileAOntoB(ArrayList<Block> list, int a, int b) {
		//System.out.println("DEBUG: Pile " + a + " onto " + b);
		Block A = find(list, a);
		Block B = find(list, b);
		if(A.below(B))
			return;
		
		clearUpTo(list, B, A);
		
		if(A.prev != null)
			A.prev.next = null;
		else
			list.set(A.id, null);
		
		B.next = A;
		A.prev = B;
		//printList(list);
	}
	
	public static void pileAOverB(ArrayList<Block> list, int a, int b) {
		//System.out.println("DEBUG: Pile " + a + " over " + b);
		Block A = find(list, a);
		Block B = find(list, b);
		if(A.below(B) || B.below(A))
			return;
		
		if(A.prev != null)
			A.prev.next = null;
		else
			list.set(A.id, null);
		
		while(B.next != null)
			B = B.next;
		
		B.next = A;
		A.prev = B;
		//printList(list);
	}
	
	public static void printList(ArrayList<Block> list) {
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i) == null) {
				System.out.println(i + ":");
			}
			else {
				System.out.println(i + ": " + list.get(i));
			}
		}
	}
	
	public static String randLine(int N) {
		int r = (int)(Math.random()*2);
		String res = "";
		if(r == 0)
			res += "move ";
		else
			res += "pile ";
		
		res += (int)(Math.random()*N);
		r = (int)(Math.random()*2);
		if(r == 0)
			res += " onto ";
		else
			res += " over ";
		
		res += (int)(Math.random()*N);
		return res;
	}
	
	public static boolean checkAll(ArrayList<Block> list) {
		boolean [] check = new boolean[list.size()];
		for(int i = 0; i < list.size(); ++i) {
			Block block = list.get(i);
			if(block == null)
				continue;
			while(block != null) {
				if(check[block.id])
					return false;
				check[block.id] = true;
				block = block.next;
			}
		}
		for(int i = 0; i < check.length; ++i) {
			if(!check[i])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem101.in"));
		String line = "";
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Block> list = new ArrayList<Block>();
		for(int i = 0; i < N; ++i) {
			list.add(new Block(i));
		}
		
		while((line = br.readLine()) != null) {
//			line = randLine(N);
//			System.out.println("DEBUG: " + line);
			line = line.trim();
			if(line.equals("quit"))
				break;
			
			String [] tokens = line.split("\\s+");
			String cmd = tokens[0].toLowerCase();
			String method = tokens[2].toLowerCase();
			int a = Integer.parseInt(tokens[1]);
			int b = Integer.parseInt(tokens[3]);
			if(a == b)
				continue;
			
			if(cmd.equals("move")) {
				if(method.equals("onto")) {
					moveAOntoB(list, a, b);
				}
				else { //over
					moveAOverB(list, a, b);
				}
			}
			else { //pile
				if(method.equals("onto")) {
					pileAOntoB(list, a, b);
				}
				else { //over
					pileAOverB(list, a, b);
				}
			}
//			printList(list);
//			if(!checkAll(list))
//				throw new Exception("List is invalid!");
		}
		
		printList(list);
	}
}
