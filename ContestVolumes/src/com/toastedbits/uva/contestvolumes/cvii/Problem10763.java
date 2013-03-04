package com.toastedbits.uva.contestvolumes.cvii;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem10763 {
	private static Scanner sc = new Scanner(System.in);
	
	public static class Pair {
		private int a;
		private int b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}
	}
	
	private static String solve(int n) {
		Map<Pair, Integer> map = new HashMap<Pair, Integer>();
		
		for(int i = 0; i < n; ++i) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(a == b) {
				continue;
			}
			int add = (a > b ? 1 : -1);
			Pair p = (a > b ? new Pair(a, b) : new Pair(b, a));
			if(!map.containsKey(p)) {
				map.put(p, add);
			}
			else {
				Integer val = map.get(p) + add;
				if(val == 0) {
					map.remove(p);
				}
				else {
					map.put(p, val);
				}
			}
		}
		
		return (map.isEmpty() ? "YES" : "NO");
	}
	
	public static void main(String [] args) throws Exception {
		int n = 0;
		while((n = sc.nextInt()) != 0) {
			System.out.println(solve(n));
		}
	}
}