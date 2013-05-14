package chapter3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Problem10188 {
	public static List<Byte> readLine(BufferedInputStream bis) throws IOException {
		List<Byte> bytes = new ArrayList<Byte>();
		int bt;
		
		while((bt = bis.read()) != -1) {
			char ch = (char)bt;
			if(ch == '\n') {
				break;
			}
			
			bytes.add((byte)bt);
		}
		
		return bytes;
	}
	
	public static Integer readIntLine(BufferedInputStream bis) throws IOException {
		List<Byte> buffer = readLine(bis);
		StringBuilder sb = new StringBuilder();
		
		for(Byte bt : buffer) {
			sb.append((char)bt.byteValue());
		}
		
		return Integer.parseInt(sb.toString());
	}
	
	public static void main(String args[]) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(System.in);
		
		int ct = 1;
		int n = 0;
		while((n = readIntLine(bis)) != 0) {
			List<List<Byte>> solution = new ArrayList<List<Byte>>();
			StringBuilder solutionNumbers = new StringBuilder();
			
			for(int i = 0; i < n; ++i) {
				List<Byte> line = readLine(bis);
				solution.add(line);
				for(Byte bt : line) {
					char ch = (char)bt.byteValue();
					if(Character.isDigit(ch)) {
						solutionNumbers.append(ch);
					}
				}
			}
			
			int m = readIntLine(bis);
			List<List<Byte>> output = new ArrayList<List<Byte>>();
			StringBuilder outputNumbers = new StringBuilder();
			
			for(int i = 0; i < m; ++i) {
				List<Byte> line = readLine(bis);
				output.add(line);
				for(Byte bt : line) {
					char ch = (char)bt.byteValue();
					if(Character.isDigit(ch)) {
						outputNumbers.append(ch);
					}
				}
			}
			
			if(n == m && solution.equals(output)) {
				System.out.println("Run #" + ct + ": Accepted");
			}
			else if(solutionNumbers.toString().equals(outputNumbers.toString())) {
				System.out.println("Run #" + ct + ": Presentation Error");
			}
			else {
				System.out.println("Run #" + ct + ": Wrong Answer");
			}
			
			ct++;
		}
	}
}