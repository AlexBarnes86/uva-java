package com.toastedbits.uva.programmingchallenges.chapter1;

import java.util.Scanner;

public class Problem10189 {
	public static Scanner sc = new Scanner(System.in);
	
	public static class Board {
		private char [][] board;
		private int width;
		private int height;
		
		private void readBoard() {
			for(int i = 0; i < height; ++i) {
				String line = sc.nextLine().trim();
				for(int j = 0; j < width; ++j) {
					board[i][j] = line.charAt(j);
				}
			}
		}
		
		public Board(int r, int c) {
			height = r;
			width = c;
			board = new char [height][width];
			
			readBoard();
		}
		
		private int countMines(int r, int c) {
			int mines = 0;
			
			for(int i = r-1; i <= r+1; ++i) {
				if(i < 0 || i >= height) {
					continue;
				}
				for(int j = c-1; j <= c+1; ++j) {
					if(j < 0 || j >= width || (i == r && j == c)) {
						continue;
					}
					if(board[i][j] == '*') {
						mines++;
					}
				}
			}
			
			return mines;
		}
		
		public String solve() {
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0; i < height; ++i) {
				for(int j = 0; j < width; ++j) {
					if(board[i][j] == '*') {
						sb.append('*');
					}
					else {
						sb.append(countMines(i, j));
					}
				}
				if(i != height -1) {
					sb.append("\n");
				}
			}
			
			return sb.toString();
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0; i < width; ++i) {
				for(int j = 0; j < height; ++j) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		int ct = 1;
		while(sc.hasNext()) {
			String [] tokens = sc.nextLine().trim().split("\\s+");
			int height = Integer.parseInt(tokens[0]);
			int width = Integer.parseInt(tokens[1]);
			if(height == 0 && width == 0) {
				return;
			}
			
			Board b = new Board(height, width);
			if(ct != 1) {
				System.out.println();
			}
			System.out.println("Field #"+ct+":");
			System.out.println(b.solve());
			ct++;
		}
	}
}
