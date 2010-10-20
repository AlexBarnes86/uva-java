package AOAPC.vol1.MiscMath;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem253 {
	public static int findAxis(String c1, String c2) {
		if(c1.charAt(0) == c2.charAt(0) && c1.charAt(5) == c2.charAt(5))
			return 1;
		if(c1.charAt(1) == c2.charAt(1) && c1.charAt(4) == c2.charAt(4))
			return 2;
		if(c1.charAt(2) == c2.charAt(2) && c1.charAt(3) == c2.charAt(3))
			return 3;
		return -1;
	}
	
	public static String rotateRight(String c) {
		return "" + c.charAt(0) + c.charAt(2) + c.charAt(4) + c.charAt(1) + c.charAt(3) + c.charAt(5);
	}
	
	public static String rotateDown(String c) {
		return "" + c.charAt(4) + c.charAt(0) + c.charAt(2) + c.charAt(3) + c.charAt(5) + c.charAt(1);
	}
	
	public static String rotateClockwise(String c) {
		return "" + c.charAt(3) + c.charAt(1) + c.charAt(0) + c.charAt(5) + c.charAt(4) + c.charAt(2);
	}
	
	public static boolean solve(String c1, String c2) {
		String cube = c1;
		for(int i = 0; i < 4; ++i) {
			int axis = findAxis(cube, c2);
			if(axis > 0) {
				for(int j = 0; j < 4; ++j) {
					if(cube.equals(c2))
						return true;
					switch(axis) {
					//vertical axis
					case 1: cube = rotateRight(cube);
						break;
					//into page axis
					case 2: cube = rotateClockwise(cube);
						break;
					//horizontal axis
					case 3: cube = rotateDown(cube);
						break;
					}
				}
			}
			c1 = rotateRight(c1);
			cube = c1;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while((line = br.readLine()) != null) {
			String cube1 = line.substring(0, 6);
			String cube2 = line.substring(6);
			if(solve(cube1, cube2))
				System.out.println("TRUE");
			else
				System.out.println("FALSE");
		}
	}
}
