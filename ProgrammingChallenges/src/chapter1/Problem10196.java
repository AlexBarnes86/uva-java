package chapter1;

import java.io.File;
import java.util.Scanner;

public class Problem10196 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_INPUT = false;
	
	public static void debug(Object msg) {
		if(DEBUG) {
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static final Scanner sc = getScanner();
	public static Scanner getScanner() {
		try {
			if(!DEBUG_INPUT) {
				return new Scanner(System.in);
			}
			else {
				return new Scanner(new File("ProgrammingChallenges/input/chapter1/problem10196.in"));
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
	
	public static final int WIDTH = 8, HEIGHT = 8;
	
	public enum Color {
		NONE,
		BLACK,
		WHITE
	};
	
	public static class ChessBoard {
		char[][] board = new char[HEIGHT][WIDTH];
		
		public ChessBoard(char[][] board) {
			this.board = board;
		}
		
		public boolean isBlank() {
			for(int i = 0; i < HEIGHT; ++i) {
				for(int j = 0; j < WIDTH; ++j) {
					if(board[i][j] != '.') {
						return false;
					}
				}
			}
			
			return true;
		}
		
		public char getPiece(int i, int j) {
			if(i < 0 || i >= HEIGHT || j < 0 || j >= WIDTH) {
				return 'X';
			}
			
			return board[i][j];
		}
		
		public char getFirstNonBlank(int i, int j, int incI, int incJ) {
			char cell = '.';
			
			while(cell == '.') {
				i += incI;
				j += incJ;
				cell = getPiece(i, j);
			}
			
			return cell;
		}
		
//		If it's a black pawn, it can only move one square diagonally down the board.
//		Pawn
//		........
//		........
//		........
//		........
//		...p....
//		..*.*...
//		........
//		........
		private Color checkPawn(int i, int j, Color color) {
			debug("checkPawn " + i + ", " + j+ ", " + (color == Color.BLACK ? "Black" : "White"));
			if(Character.toLowerCase(getPiece(i, j)) != 'p') {
				debug("Not a pawn");
				return Color.NONE;
			}
			
			if(color == Color.BLACK) {
				if(getPiece(i+1, j-1) == 'K' ||
					getPiece(i+1, j+1) == 'K') {
					debug("White Attacked");
					return Color.WHITE;
				}
			}
			else {
				if(getPiece(i-1, j-1) == 'k' ||
					getPiece(i-1, j+1) == 'k') {
					debug("Black attacked");
					return Color.BLACK;
				}
			}
			
			debug("None attacked");
			return Color.NONE;
		}
		
//		Knight
//		........
//		........
//		..*.*...
//		.*...*..
//		...n....
//		.*...*..
//		..*.*...
//		........
		private Color checkKnight(int i, int j, Color color) {
			if(Character.toLowerCase(getPiece(i, j)) != 'n') {
				return Color.NONE;
			}
			
			char king = (color == Color.BLACK ? 'K' : 'k'); //king we are looking to check
			
			if(getPiece(i-1, j-2) == king ||
				getPiece(i-2, j-1) == king ||
				getPiece(i-2, j+1) == king ||
				getPiece(i-1, j+2) == king ||
				getPiece(i+1, j+2) == king ||
				getPiece(i+2, j+1) == king ||
				getPiece(i+2, j-1) == king ||
				getPiece(i+1, j-2) == king) {
				return (color == Color.BLACK ? Color.WHITE : Color.BLACK);
			}
			
			return Color.NONE;
		}
		
//		Rook
//		...*....
//		...*....
//		...*....
//		...*....
//		***r****
//		...*....
//		...*....
//		...*....
		private Color checkRook(int i, int j, Color color) {
			if(Character.toLowerCase(getPiece(i, j)) != 'r') {
				return Color.NONE;
			}
			
			char king = (color == Color.BLACK ? 'K' : 'k');
			
			if(getFirstNonBlank(i, j, 0, -1) == king ||
				getFirstNonBlank(i, j, -1, 0) == king ||
				getFirstNonBlank(i, j, 0, 1) == king ||
				getFirstNonBlank(i, j, 1, 0) == king) {
				return (color == Color.BLACK ? Color.WHITE : Color.BLACK);
			}
			
			return Color.NONE;
		}
		
//		Bishop
//		.......*
//		*.....*.
//		.*...*..
//		..*.*...
//		...b....
//		..*.*...
//		.*...*..
//		*.....*.
		private Color checkBishop(int i, int j, Color color) {
			if(Character.toLowerCase(getPiece(i, j)) != 'b') {
				return Color.NONE;
			}
			
			char king = (color == Color.BLACK ? 'K' : 'k');
			
			if(getFirstNonBlank(i, j, -1, -1) == king ||
				getFirstNonBlank(i, j, -1, 1) == king ||
				getFirstNonBlank(i, j, 1, 1) == king ||
				getFirstNonBlank(i, j, 1, -1) == king) {
				return (color == Color.BLACK ? Color.WHITE : Color.BLACK);
			}
			
			return Color.NONE;
		}
		
//		Queen
//		...*...*
//		*..*..*.
//		.*.*.*..
//		..***...
//		***q****
//		..***...
//		.*.*.*..
//		*..*..*.
		private Color checkQueen(int i, int j, Color color) {
			if(Character.toLowerCase(getPiece(i, j)) != 'q') {
				return Color.NONE;
			}
			
			char king = (color == Color.BLACK ? 'K' : 'k');
			
			if(getFirstNonBlank(i, j, 0, -1) == king ||
				getFirstNonBlank(i, j, -1, -1) == king ||
				getFirstNonBlank(i, j, -1, 0) == king ||
				getFirstNonBlank(i, j, -1, 1) == king ||
				getFirstNonBlank(i, j, 0, 1) == king ||
				getFirstNonBlank(i, j, 1, 1) == king ||
				getFirstNonBlank(i, j, 1, 0) == king ||
				getFirstNonBlank(i, j, 1, -1) == king) {
				return (color == Color.BLACK ? Color.WHITE : Color.BLACK);
			}
			
			return Color.NONE;
		}
/*
There won't be a configuration where both kings are in check.

White pieces will be represented by uppercase letters whereas black pieces will be represented by lowercase letters.

Pawn (p or P): can only move straight ahead, one square at a time. But it takes pieces diagonally (and that's what concerns to you in this problem).
Knight (n or N): have a special movement and it's the only piece that can jump over other pieces. The knight movement can be viewed as an "L". See the example bellow.
Bishop (b or B): can move any number of squares diagonally (forward or backward).
Rook (r or R): can move any number of squares vertically or horizontally (forward or backward).
Queen (q or Q): can move any number of squares in any direction (diagonally, horizontally or vertically, forward or backward).
King (k or K): can move one square at a time, in any direction (diagonally, horizontally or vertically, forward or backward). 
*/
		public Color inCheck() {
			Color result = Color.NONE;
			
			for(int i = 0; i < HEIGHT; ++i) {
				for(int j = 0; j < WIDTH; ++j) {
					char cell = getPiece(i, j);
					Color pieceColor = (Character.isLowerCase(cell) ? Color.BLACK : Color.WHITE);
					if(cell == '.') {
						continue;
					}
					switch(Character.toLowerCase(cell)) {
					case 'p':
						result = checkPawn(i, j, pieceColor);
						break;
					case 'n':
						result = checkKnight(i, j, pieceColor);
						break;
					case 'b':
						result = checkBishop(i, j, pieceColor);
						break;
					case 'r':
						result = checkRook(i, j, pieceColor);
						break;
					case 'q':
						result = checkQueen(i, j, pieceColor);
						break;
					//No need to check king. They would both be in check, which is not a valid board configuration
					}
					
					if(result != Color.NONE) {
						return result;
					}
				}
			}
			
			return result;
		}
	}
	
	public static void main(String[] args) {
		String line = "";
		int ct = 1;
		while(sc.hasNext()) {
			char[][] board = new char[HEIGHT][WIDTH];
			
			debug("input:");
			for(int i = 0; i < HEIGHT; ++i) {
				line = sc.nextLine().trim();
				board[i] = line.toCharArray();
				debug(line);
			}
			
			ChessBoard chess = new ChessBoard(board);
			
			if(chess.isBlank()) {
				return;
			}
			
			debug("");
			debug("solution:");
			switch(chess.inCheck()) {
			case NONE:
				System.out.println("Game #" + ct + ": no king is in check.");
				break;
			case BLACK:
				System.out.println("Game #" + ct + ": black king is in check.");
				break;
			case WHITE:
				System.out.println("Game #" + ct + ": white king is in check.");
				break;
			}
			
			sc.nextLine(); //blank line between boards
			ct++;
		}
	}
}
