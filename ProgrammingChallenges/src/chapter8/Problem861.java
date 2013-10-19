package chapter8;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem861 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_INPUT = false;
	public static final Scanner sc = getScanner();
	private static int total = 0;
	
	public static class DiscreteMath {
		private static List<List<Integer>> choices;
		
		private static boolean isSolution(List<Integer> a, int k) {
			return a.size() == k;
		}
		
		private static List<Integer> range(int a, int b) {
			List<Integer> list = new ArrayList<Integer>(b-a);
			for(int i = a; i < b; ++i) {
				list.add(i);
			}
			
			return list;
		}
		
		private static List<Integer> candidates(List<Integer> a, int n) {
			int max = -1;
			for(int i : a) {
				if(max < i) {
					max = i;
				}
			}
			
			return range(max+1, n);
		}
		
		private static void choose(List<Integer> a, int n, int k) {
			if(isSolution(a, k)) {
				choices.add(a);
			}
			else {
				for(int c : candidates(a, n)) {
					List<Integer> l = new ArrayList<Integer>(a);
					l.add(c);
					choose(l, n, k);
				}
			}
		}
		
		public static List<List<Integer>> choose(int n, int k) {
			choices = new ArrayList<List<Integer>>();
			choose(new ArrayList<Integer>(), n, k);
			return choices;
		}
	}
	
//	public static void testChoose(int n, int k) {
//		List<List<Integer>> all = DiscreteMath.choose(n, k);
//		for(List<Integer> list : all) {
//			System.out.println(list);
//		}
//		System.out.println("Total: " + all.size());
//	}
	
	private static class Position {
		public int x;
		public int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	
	private static Position getPosition(int diagonal, int ct, int boardSize) {
		int x = 0, y = 0;
		if(diagonal < boardSize) {
			y = diagonal;
		}
		else {
			x = diagonal - boardSize + 1;
			y = boardSize-1;
		}
		
		return new Position(x + ct, y - ct);
	}
	
//	public static void testGetPosition(int boardSize) {
//		for(int d = 0; d < boardSize*2-1; ++d) {
//			int ct = 0;
//			while(true) {
//				Position p = getPosition(d, ct, boardSize);
//				if(p.y < 0 || p.x >= boardSize) {
//					break;
//				}
//				System.out.println("Diagonal: " + d + ", Ct: " + ct + " -> (" + p.x + ", " + p.y + ")");
//				ct++;
//			}
//		}
//	}
	
	private static boolean attacking(Position p1, Position p2) {
		return (p2.y - p1.y) == (p2.x - p1.x);
	}
	
	private static boolean attacking(List<Position> prevBishops, Position p) {
		for(Position bishop : prevBishops) {
			if(attacking(bishop, p)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static List<Position> candidates(List<Position> prevBishops, int diagonal, int boardSize) {
		List<Position> c = new ArrayList<Position>(boardSize-1);
		for(int ct = 0; ct < boardSize; ++ct) {
			Position p = getPosition(diagonal, ct, boardSize);
			if(p.y < 0 || p.x >= boardSize) {
				break;
			}
			
			if(!attacking(prevBishops, p)) {
				c.add(p);
			}
		}
		
		return c;
	}
	
	private static boolean isSolution(List<Position> positions, int nBishops) {
		return positions.size() == nBishops;
	}
	
	private static void countSolutions(List<Position> bishops, List<Integer> diagonals, int boardSize) {
		if(isSolution(bishops, diagonals.size())) {
			total++;
		}
		else {
			int curBishopDiagonal = bishops.size();
			List<Position> candidateList = candidates(bishops, diagonals.get(curBishopDiagonal), boardSize);
//			debug("Bishops: " + bishops + ", candidateList: " + candidateList);
			for(Position c : candidateList) {
				List<Position> prevBishops = new ArrayList<Position>(bishops);
				prevBishops.add(c);
				countSolutions(prevBishops, diagonals, boardSize);
			}
		}
	}
	
	public static int solve(int boardSize, int totalBishops) {
		if(boardSize == 1 && totalBishops == 1) { //hardcoded solution for this edge case
			return 1;
		}
		
		total = 0;
		int numDiagonals = boardSize*2-1;
//		debug("Solving boardSize: " + boardSize + ", totalBishops: " + totalBishops + ", numDiagonals: " + numDiagonals);
		if(numDiagonals <= totalBishops) {
			return 0;
		}
		
		List<List<Integer>> allDiagonalCombos = DiscreteMath.choose(numDiagonals, totalBishops);
		
		for(List<Integer> diagonals : allDiagonalCombos) {
			countSolutions(new ArrayList<Position>(), diagonals, boardSize);
		}
		
		return total;
	}
	
	public static void buildLUT() {
		List<List<Integer>> solutions = new ArrayList<List<Integer>>();
		List<Integer> row = new ArrayList<Integer>();
		solutions.add(row);
		
		for(int boardSize = 1; boardSize <= 8; boardSize++) {
			row = new ArrayList<Integer>();
			for(int nBishops = 0; nBishops <= boardSize*boardSize; ++nBishops) {
				row.add(solve(boardSize, nBishops));
			}
			solutions.add(row);
		}
		
		for(List<Integer> r : solutions) {
			System.out.println(r);
		}
	}
	
	public static List<List<Integer>> getLUT() {
		List<List<Integer>> solns = new ArrayList<List<Integer>>();
		solns.add(Arrays.asList(new Integer [] {1}));
		solns.add(Arrays.asList(new Integer [] {1, 1}));
		solns.add(Arrays.asList(new Integer [] {1, 4, 4, 0, 0}));
		solns.add(Arrays.asList(new Integer [] {1, 9, 26, 26, 8, 0, 0, 0, 0, 0}));
		solns.add(Arrays.asList(new Integer [] {1, 16, 92, 232, 260, 112, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
		solns.add(Arrays.asList(new Integer [] {1, 25, 240, 1124, 2728, 3368, 1960, 440, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
		solns.add(Arrays.asList(new Integer [] {1, 36, 520, 3896, 16428, 39680, 53744, 38368, 12944, 1600, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
		solns.add(Arrays.asList(new Integer [] {1, 49, 994, 10894, 70792, 282248, 692320, 1022320, 867328, 389312, 81184, 5792, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
		solns.add(Arrays.asList(new Integer [] {1, 64, 1736, 26192, 242856, 1444928, 5599888, 14082528, 22522960, 22057472, 12448832, 3672448, 489536, 20224, 256, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
		return solns;
	}
	
	public static void main(String args[]) {
//		testChoose(5, 3);
//		testGetPosition(5);
//		buildLUT();
		
		PrintWriter pw = new PrintWriter(System.out);
		List<List<Integer>> solns = getLUT();
		while(true) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int boardSize = Integer.parseInt(st.nextToken());
			int totalBishops = Integer.parseInt(st.nextToken());
			if(boardSize == 0 && totalBishops == 0) {
				break;
			}
			pw.println(solns.get(boardSize).get(totalBishops));
		}
		
		pw.flush();
	}
	
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
	
	public static Scanner getScanner() {
		if(!DEBUG_INPUT) {
			return new Scanner(System.in);
		}
		else {
			try {
				String debugFile = "ProgrammingChallenges/input/chapter8/Problem861.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
}