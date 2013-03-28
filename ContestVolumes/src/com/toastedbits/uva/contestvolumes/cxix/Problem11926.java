package com.toastedbits.uva.contestvolumes.cxix;

import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;

public class Problem11926 {
	private static Scanner sc;
	private static final int MAX_TIME = 1000000;
	private static BitSet bits = new BitSet(MAX_TIME+1);
	
	private static String solve(int n, int m) throws IOException {
		bits.clear();
		
		for(int i = 0; i < n; ++i) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			for(int j = (start >= 0 ? start : 0); j < (end <= MAX_TIME ? end : MAX_TIME); ++j) {
				if(bits.get(j)) {
					for(; i < n; ++i) {
						sc.nextLine();
					}
					return "CONFLICT";
				}
				bits.set(j);
			}
		}
		
		for(int i = 0; i < m; ++i) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int jump = sc.nextInt();
			
			while(start < MAX_TIME) {
				for(int j = (start >= 0 ? start : 0); j < (end <= MAX_TIME ? end : MAX_TIME); j++) {
					if(bits.get(j)) {
						for(; i < m; ++i) {
							sc.nextLine();
						}
						return "CONFLICT";
					}
					bits.set(j);
				}
				start += jump;
				end += jump;
			}
		}
		
		return "NO CONFLICT";
	}
	
	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0) {
				break;
			}
			System.out.println(solve(n, m));
		}
	}
}