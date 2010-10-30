package vol1.MiscMath;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem253 {
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
		for(int i = 0; i < 4; ++i) {//rotate right
			for(int j = 0; j < 4; ++j) {//rotate down
				if(c1.equals(c2))
					return true;
				c1 = rotateDown(c1);
			}
			for(int j = 0; j < 4; ++j) {//rotate clockwise
				if(c1.equals(c2))
					return true;
				c1 = rotateClockwise(c1);
			}
			c1 = rotateRight(c1);
		}

		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		
//		String abc = "abcdef";
//		for(int i = 0; i < 4; ++i) {
//			abc = rotateRight(abc);
//		}
//		System.out.println(abc);
//		for(int i = 0; i < 4; ++i) {
//			abc = rotateDown(abc);
//		}
//		System.out.println(abc);
//		for(int i = 0; i < 4; ++i) {
//			abc = rotateClockwise(abc);
//		}
//		System.out.println(abc);
		
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
