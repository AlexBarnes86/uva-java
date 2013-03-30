package chapter1;

import java.util.Scanner;

public class Problem706 {
	public static final Scanner sc = new Scanner(System.in);
	
	public static final int [][] segments = {
		{1,2,3,4,5,6},
		{3,4},
		{2,3,5,6,7},
		{2,3,4,5,7},
		{1,3,4,7},
		{1,2,4,5,7},
		{1,2,4,5,6,7},
		{2,3,4},
		{1,2,3,4,5,6,7},
		{1,2,3,4,5,7}
	};
	
	public static String render(char[][] buffer) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < buffer.length; ++i) {
			if(i != 0) {
				sb.append("\n");
			}
			for(int j = 0; j < buffer[i].length; ++j) {
				sb.append(buffer[i][j]);
			}
		}
		
		return sb.toString();
	}
	
	public static void fill(char[][] buffer, char ch) {
		for(int i = 0; i < buffer.length; ++i) {
			for(int j = 0; j < buffer[i].length; ++j) {
				buffer[i][j] = ch;
			}
		}
	}
	
//	 2 
//	1 3
//	 7 
//	6 4
//	 5 
	public static void renderSegment(int segment, int size, char[][] buffer, int cursor) {
		if(segment == 1) {
			for(int i = 0; i < size; ++i) {
				buffer[i+1][cursor] = '|';
			}
		}
		else if(segment == 2) {
			for(int i = 0; i < size; ++i) {
				buffer[0][i+cursor+1] = '-';
			}
		}
		else if(segment == 3) {
			for(int i = 0; i < size; ++i) {
				buffer[i+1][cursor+size+1] = '|';
			}
		}
		else if(segment == 4) {
			for(int i = 0; i < size; ++i) {
				buffer[i+size+2][cursor+size+1] = '|';
			}
		}
		else if(segment == 5) {
			for(int i = 0; i < size; ++i) {
				buffer[2*size+2][i+cursor+1] = '-';
			}
		}
		else if(segment == 6) {
			for(int i = 0; i < size; ++i) {
				buffer[i+size+2][cursor] = '|';
			}
		}
		else if(segment == 7) {
			for(int i = 0; i < size; ++i) {
				buffer[size+1][i+cursor+1] = '-';
			}
		}
	}
	
	public static void renderDigit(int digit, int size, char[][] buffer, int cursor) {
		for(int segment : segments[digit]) {
			renderSegment(segment, size, buffer, cursor);
		}
	}
	
	public static void render(char ch, int size, char[][] buffer, int cursor) {
		renderDigit(Integer.parseInt(ch+""), size, buffer, cursor);
	}
	
	public static String render(int size, String number) {
		int nCols = number.length()*(size+2)+number.length()-1; //number.length()-1 to account for blank cols between digits
		int nRows = 2*size+3;
		char[][] buffer = new char[nRows][nCols];
		fill(buffer, ' ');
		int cursor = 0;
		
		for(char ch : number.toCharArray()) {
			render(ch, size, buffer, cursor);
			cursor += (size+3);
		}
		
		return render(buffer);
	}
	
	public static void main(String[] args) {
		while(sc.hasNext()) {
			String[] tokens = sc.nextLine().trim().split("\\s+");
			if("0".equals(tokens[0]) && "0".equals(tokens[1])) {
				break;
			}
			
			int size = Integer.parseInt(tokens[0]);
			String number = tokens[1];
			System.out.println(render(size, number));
			System.out.println();
		}
	}
}