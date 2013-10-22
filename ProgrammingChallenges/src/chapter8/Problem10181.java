package chapter8;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

//WARNING: If you wish to experiment with a width larger than 4, you must swap out the getCode()
//with something more generalized, it is currently optimized with bit shifts to meet the time limit
public class Problem10181 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_FILE = true;
	public static final Scanner sc = getScanner();
	private static final int BOARD_WIDTH = 4; //WARNING: see above
	
	private enum Direction {
		LEFT, RIGHT, UP, DOWN;
	}
	
	static class Board implements Comparable<Board> {
		private int [][] pieces;
		private int width;
		private int blankRow, blankCol;
		private String path;
		
		public Board(int width) {
			this.width = width;
			pieces = new int[width][width];
			path = "";
		}
		
		public Board(Board cur, Direction direction) {
			width = cur.width;
			pieces = new int[width][width];
			blankRow = cur.blankRow;
			blankCol = cur.blankCol;
			for(int i = 0; i < width; ++i) {
				for(int j = 0; j < width; ++j) {
					pieces[i][j] = cur.pieces[i][j];
				}
			}
			
			if(direction == Direction.LEFT && blankCol > 0) {
				pieces[blankRow][blankCol] = pieces[blankRow][blankCol-1];
				pieces[blankRow][blankCol-1] = 0;
				blankCol--;
				path = cur.path + "L";
			}
			else if(direction == Direction.RIGHT && blankCol < width-1) {
				pieces[blankRow][blankCol] = pieces[blankRow][blankCol+1];
				pieces[blankRow][blankCol+1] = 0;
				blankCol++;
				path = cur.path + "R";
			}
			else if(direction == Direction.UP && blankRow > 0) {
				pieces[blankRow][blankCol] = pieces[blankRow-1][blankCol];
				pieces[blankRow-1][blankCol] = 0;
				blankRow--;
				path = cur.path + "U";
			}
			else if(direction == Direction.DOWN && blankRow < width - 1) {
				pieces[blankRow][blankCol] = pieces[blankRow+1][blankCol];
				pieces[blankRow+1][blankCol] = 0;
				blankRow++;
				path = cur.path + "D";
			}
		}
		
		public void read(Scanner sc) {
			for(int i = 0; i < width; ++i) {
				for(int j = 0; j < width; ++j) {
					int piece = sc.nextInt();
					pieces[i][j] = piece;
					if(piece == 0) {
						blankRow = i;
						blankCol = j;
					}
				}
			}
		}
		
		public Board moveLeft() {
			if(blankCol <= 0) {
				return null;
			}
			
			return new Board(this, Direction.LEFT);
		}
		
		public Board moveRight() {
			if(blankCol >= width - 1) {
				return null;
			}
			
			return new Board(this, Direction.RIGHT);
		}
		
		public Board moveUp() {
			if(blankRow <= 0) {
				return null;
			}
			
			return new Board(this, Direction.UP);
		}
		
		public Board moveDown() {
			if(blankRow >= width - 1) {
				return null;
			}
			
			return new Board(this, Direction.DOWN);
		}
		
		public int manhattanDistance() {
			int total = 0;
			
			for(int r = 0; r < width; ++r) {
				for(int c = 0; c < width; ++c) {
					if(pieces[r][c] == 0) {
						continue;
					}
					else {
						int xDist = Math.abs(c - ((pieces[r][c]-1)%width));
						int yDist = Math.abs(r - ((pieces[r][c]-1)/width));
						total += xDist + yDist;
					}
				}
			}
			
			return total;
		}
		
		private boolean isSolution() {
			int ct = 1;
			
			for(int i = 0; i < width*width-1; ++i) { //Intentionally skip the zero at the end
				if(pieces[i/width][i%width] != ct) {
					return false;
				}
				ct++;
			}
			
			return true;
		}
		
		//Quick and dirty O(n^2) version, could be done in O(n*log(n)) with more complexity 
		public int inversions() {
			int total = 0;
			
			for(int i = 0; i < width*width; ++i) {
				if(pieces[i/width][i%width] == 0) {
					continue;
				}
				
				int ct = 0;
				for(int j = i+1; j < width*width; ++j) {
					if(pieces[j/width][j%width] == 0) {
						continue;
					}
					if(pieces[i/width][i%width] > pieces[j/width][j%width]) {
						ct++;
					}
				}
				
				total += ct;
			}
			
			return total;
		}
		
		//Counting with 0 - Even, being the first row
		private boolean blankOnEvenRow() {
			return (width - blankRow) % 2 == 0;
		}
		
		/*
			From: http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
				If the grid width is odd, then the number of inversions in a solvable situation is even.
				If the grid width is even, and the blank is on an even row counting from the bottom (second-last, fourth-last etc), then the number of inversions in a solvable situation is odd.
				If the grid width is even, and the blank is on an odd row counting from the bottom (last, third-last, fifth-last etc) then the number of inversions in a solvable situation is even.
			
			We are dealing only with Even sized squares in this UVa problem, included the odd case here for completeness and generalization
		 */
		public boolean solvable() {
			if(width % 2 != 0) {				//Board with odd width
				return inversions() % 2 == 0;	//Solvable when #inversions is even
			}
			else {									//Board with even width
				if(blankOnEvenRow()) {				//Empty cell on even row counting from the bottom
					return inversions() % 2 == 1;	//Solvable when #inversions is odd
				}
				else {
					return inversions() % 2 == 0;	//Solvable when #inversions is even
				}
			}
		}
		
		public String solve() {
			if(!solvable()) {
				return null;
			}
			
			Set<Long> visited = new HashSet<Long>();
			
			PriorityQueue<Board> fringe = new PriorityQueue<Board>();
			fringe.add(this);
			
			while(!fringe.isEmpty()) {
				Board curBoard = fringe.remove();
				
				if(curBoard.isSolution()) {
					return curBoard.getPath();
				}
				
				Board next = curBoard.moveRight();
				if(next != null && !visited.contains(next.getCode())) {
					fringe.add(next);
				}
				
				next = curBoard.moveUp();
				if(next != null && !visited.contains(next.getCode())) {
					fringe.add(next);
				}
				
				next = curBoard.moveLeft();
				if(next != null && !visited.contains(next.getCode())) {
					fringe.add(next);
				}
				
				next = curBoard.moveDown();
				if(next != null && !visited.contains(next.getCode())) {
					fringe.add(next);
				}
				
				visited.add(curBoard.getCode());
			}
			
			return null;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for(int r = 0; r < width; ++r) {
				for(int c = 0; c < width; ++c) {
					sb.append(pieces[r][c] + " ");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n");
			}
			sb.delete(sb.length()-1, sb.length());
			
			return sb.toString();
		}
		
		public String getPath() {
			return path;
		}
		
		@Override
		public int compareTo(Board other) {
			return (manhattanDistance() + path.length()) - (other.manhattanDistance() + other.getPath().length());
		}
		
		/* WARNING: optimization for time limit reasons, cannot use width larger than 4 */
		public Long getCode() {
			long state = 0;
			
			for (int r = 0; r < width; r++) { // transform 16 numbers into 64 bits, exactly into unsigned long long
				for(int c = 0; c < width; c++) {
					state <<= 4; // move left 4 bits (allows for max of 2^4 = 16)
					state += pieces[r][c]; // add this digit (max 15 or 1111)
				}
			}
			
			return state;
		}
	}
	
	public static void testBoardDirs(Board board) {
		Scanner sc2 = new Scanner(System.in);
		
		Board curBoard = board;
		while(true) {
			if(curBoard != null) {
				System.out.println(curBoard);
				System.out.println();
			}
			
			Board next = null;
			String str = sc2.nextLine();
			if(str.startsWith("w")) {
				next = curBoard.moveUp();
			}
			else if(str.startsWith("a")) {
				next = curBoard.moveLeft();
			}
			else if(str.startsWith("s")) {
				next = curBoard.moveDown();
			}
			else if(str.startsWith("d")) {
				next = curBoard.moveRight();
			}
			else if(str.startsWith("q")) {
				break;
			}
			
			if(next != null) {
				curBoard = next;
			}
			else {
				System.out.println("Bump!");
			}
		}
		
		sc2.close();
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		int nPuzzles = sc.nextInt();
		for(int i = 0; i < nPuzzles; ++i) {
			Board board = new Board(BOARD_WIDTH);
			board.read(sc);
//			debug(board);
//			debug("Inversions: " + board.inversions());
//			debug("Manhattan Distance: " + board.manhattanDistance());
//			testBoardDirs(board);
			String solution = board.solve();
			pw.println(solution == null ? "This puzzle is not solvable." : solution);
		}
		
		pw.flush();
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			System.out.print("DEBUG: ");
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				String fileName = "ProgrammingChallenges/input/chapter8/Problem10181.in";
				Scanner sc = new Scanner(new File(fileName));
				System.out.println("Reading from: " + fileName);
				return sc;
			}
			else {
				return new Scanner(System.in);
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
}