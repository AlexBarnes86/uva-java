//correct
package AOAPC.vol1.String;

import java.util.*;

public class Problem10010 {
	public static boolean N_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return N_match(word, grid, i-1, j, cur+1);
	}
	
	public static boolean NE_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return NE_match(word, grid, i-1, j+1, cur+1);
	}
	
	public static boolean E_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return E_match(word, grid, i, j+1, cur+1);
	}
	
	public static boolean SE_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return SE_match(word, grid, i+1, j+1, cur+1);
	}
	
	public static boolean S_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return S_match(word, grid, i+1, j, cur+1);
	}
	
	public static boolean SW_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return SW_match(word, grid, i+1, j-1, cur+1);
	}
	
	public static boolean W_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return W_match(word, grid, i, j-1, cur+1);
	}
	
	public static boolean NW_match(String word, char[][] grid, int i, int j, int cur) {
		if(cur == word.length())
			return true;
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return false;
		else if(word.charAt(cur) != grid[i][j])
			return false;
		else
			return NW_match(word, grid, i-1, j-1, cur+1);
	}
	
	public static void print_find(String word, char[][] grid) {
		int ct = 0;
		for(int i = 0; i < grid.length; ++i) {
			for(int j = 0; j < grid[i].length; ++j) {
				if(N_match(word, grid, i, j, 0) ||
						NE_match(word, grid, i, j, 0) ||
						E_match(word, grid, i, j, 0) ||
						SE_match(word, grid, i, j, 0) ||
						S_match(word, grid, i, j, 0) ||
						SW_match(word, grid, i, j, 0) ||
						W_match(word, grid, i, j, 0) ||
						NW_match(word, grid, i, j, 0)) {
					System.out.println((i+1) + " " + (j+1));
					return;
				}
			}
		}
		//System.out.println(word + " not found");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sets = Integer.parseInt(sc.nextLine());
		sc.nextLine(); //blank line
		for(int i = 0; i < sets; ++i) {
			String[] tokens = sc.nextLine().split(" ");
			int m = Integer.parseInt(tokens[0]), n = Integer.parseInt(tokens[1]);
			char[][] grid = new char[m][n];
			for(int j = 0; j < m; ++j) {
				String line = sc.nextLine().toLowerCase();
				for(int k = 0; k < n; ++k)
					grid[j][k] = line.charAt(k);
			}
			
			int w = Integer.parseInt(sc.nextLine());
			for(int j = 0; j < w; ++j)
				print_find(sc.nextLine().toLowerCase(), grid);
			if(sc.hasNext())
				sc.nextLine(); //blank line
			if(i != sets-1) //i hate you
				System.out.println();
		}
	}
}
