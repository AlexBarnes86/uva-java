package vol2.Lists;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem133 {
	public static final boolean DEBUG = false;
	
	public static void debugln() {
		debugln("");
	}
	public static void debugln(String str) {
		debug(str + "\n");
	}
	public static void debug(String str) {
		if(DEBUG) {
			System.out.print(str);
			System.out.flush();
			try {
				Thread.sleep(200);
			} catch (Exception e) {} //not sure why the flush isn't doing its job
		}
	}
	
	static class Node {
		public Node(int id) {
			this.id = id;
		}
		public int id;
		public Node next, prev;
	}
	
	static class Ring {
		Node k, m;
		int size;
		
		public int getSize() {
			return size;
		}
		
		private void printRing(Node n) {
			if(DEBUG) {
				Node temp = n;
				do {
					debug(temp.id + " <-> ");
					temp = temp.next;
				} while(temp != n);
				debugln();
			}
		}
		
		public Ring(int N) {
			size = N;
			Node first = new Node(1);
			Node node = first;
			for(int i = 2; i <= N; ++i) {
				node.next = new Node(i);
				node.next.prev = node;
				node = node.next;
			}
			node.next = first;
			first.prev = node;
			k = first;
			m = node;
			
			printRing(k);
		}
		
		public String update(int kInc, int mInc) {
			StringBuilder sb = new StringBuilder();
			
			if(k == null || m == null || size == 0)
				return null;
			
			printRing(k);
//			kInc %= size;
//			mInc %= size;
			for(int i = 1; i < kInc; ++i) {
				k = k.next;
			}
			
			for(int i = 1; i < mInc; ++i) {
				m = m.prev;
			}
			
			if(k == m) {
				size--;
				sb.append(format(k.id));
				if(k.next != k) { //if there is more than one node
					debugln("Removing (m == k, not last): " + k.id);
					k.next.prev = k.prev;
					k.prev.next = k.next;
					k = k.next;
					m = m.prev;
				}
				else { //last node
					debugln("Removing (m == k, last): " + k.id);
					k = null;
					m = null;
				}
			}
			else {
				size-=2;
				sb.append(format(k.id));
				sb.append(format(m.id));
				if(k.next == m && m.next == k) { //last two nodes
					debugln("Removing (2 last): " + k.id + ", " + m.id);
					k = null;
					m = null;
				}
				else {
					debugln("Removing (k, not last): " + k.id);
					debugln("Removing (m, not last): " + m.id);
					
					if(k.next == m) {
						k.prev.next = m.next;
						m.next.prev = k.prev;
						
						k = m.next;
						m = k.prev;
					}
					else if (m.next == k){
						k.next.prev = m.prev;
						m.prev.next = k.next;
						
						m = m.prev;
						k = k.next;
					}
					else {
						k.next.prev = k.prev;
						k.prev.next = k.next;
						k = k.next;
						
						m.next.prev = m.prev;
						m.prev.next = m.next;
						m = m.prev;
					}
				}
				
			}
			
			return sb.toString();
		}
	}
	
	public static String format(int i) {
		if(i >= 100)
			return "" + i;
		else if(i >= 10)
			return " " + i;
		return "  " + i;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while((line = br.readLine()) != null) {
			String [] tokens = line.trim().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int kInc = Integer.parseInt(tokens[1]);
			int mInc = Integer.parseInt(tokens[2]);
			
			if(N == 0 && kInc == 0 && mInc == 0)
				break;
			
			if(N == 0) {
				System.out.println();
				continue;
			}
			
			Ring ring = new Ring(N);
			StringBuilder sb = new StringBuilder();
			while((line = ring.update(kInc, mInc)) != null) {
				sb.append(line + ",");
			}
			System.out.println(sb.substring(0, sb.length()-1).toString());
		}
	}
}
