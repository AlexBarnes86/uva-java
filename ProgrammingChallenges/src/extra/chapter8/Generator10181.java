package extra.chapter8;

import java.util.ArrayList;
import java.util.List;

public class Generator10181 {
	public static List<Integer> range(int a, int b) {
		List<Integer> ary = new ArrayList<Integer>();
		
		for(int i = a; i <= b; ++i) {
			ary.add(i);
		}
		
		return ary;
	}
	
	public static List<Integer> range(int a) {
		return range(0, a);
	}
	
	public static void randomize(List list) {
		for(int i = 0; i < list.size(); ++i) {
			int swapIdx = (int)(Math.random() * list.size());
			
			Object o = list.get(i);
			list.set(i, list.get(swapIdx));
			list.set(swapIdx, o);
		}
	}
	
	public static String createPuzzle(int width) {
		List<Integer> ary = range(width*width-1);
		randomize(ary);
		StringBuilder sb = new StringBuilder();
		
		for(int r = 0; r < width; ++r) {
			for(int c = 0; c < width; ++c) {
				sb.append(ary.get(r*width+c) + " ");
			}
			sb.replace(sb.length()-1, sb.length(), "\n");
		}
		sb.delete(sb.length()-1, sb.length());
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		final int NUM_PUZZLES = 10;
		final int PUZZLE_WIDTH = 4;
		
		System.out.println(NUM_PUZZLES);
		for(int i = 0; i < NUM_PUZZLES; ++i) {
			System.out.println(createPuzzle(PUZZLE_WIDTH));
		}
	}
}
