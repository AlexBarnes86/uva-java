package unsolved; //Chapter 3

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem848 {
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
	
	public static final int MAX_CHARS_PER_LINE = 72;
	
	public static String solve(String text) {
		List<Character> in = new LinkedList<Character>();
		for(int i = 0; i < text.length(); ++i) {
			in.add(text.charAt(i));
		}
		
		List<String> out = new ArrayList<String>();
		StringBuilder buffer = new StringBuilder();
		boolean spaceEncountered = false;
		
		while(!in.isEmpty()) {
			char ch = in.remove(0);
			
			if(ch != ' ' && ch != '\n') {
				if(spaceEncountered && buffer.length() > MAX_CHARS_PER_LINE) {
					int idx = buffer.length()-1;
					while(buffer.charAt(idx) != ' ') {
						in.add(0, buffer.charAt(idx));
						buffer.deleteCharAt(idx);
						idx--;
					}
					while(buffer.charAt(idx) == ' ') {
						in.add(0, buffer.charAt(idx));
						buffer.deleteCharAt(idx);
						idx--;
					}
					out.add(buffer.toString());
					buffer = new StringBuilder();
				}
				
				buffer.append(ch);
			}
		}
		
		return out.toString();
	}
	
	public static void main(String args[]) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(System.in);
		StringBuilder sb = new StringBuilder();
		int bt;
		while((bt = bis.read()) != -1) {
			sb.append((char)bt);
		}
		System.out.println(solve(sb.toString()));
	}
}

/*
0         1         2         3         4         5         6         7
0123456789012345678901234567890123456789012345678901234567890123456789012
   Unix fmt

The unix fmt program reads lines of text, combining
and breaking lines so as to create an
output file with lines as close to without exceeding
72 characters long as possible.  The rules for combining and breaking
lines are as follows.

   1.  A new line may be started anywhere there is a space in the input.
If a new line is started, there will be no trailing blanks at the
end of the previous line or at the beginning of the new line.

   2.  A line break in the input may be eliminated in the output, provided
it is not followed by a space or another line break.  If a line
break is eliminated, it is replaced by a space.

0         1         2         3         4         5         6         7
0123456789012345678901234567890123456789012345678901234567890123456789012
   Unix fmt

The unix fmt program reads lines of text, combining and breaking lines
so as to create an output file with lines as close to without exceeding
72 characters long as possible.  The rules for combining and breaking
lines are as follows.

   1.  A new line may be started anywhere there is a space in the input.
If a new line is started, there will be no trailing blanks at the end of
the previous line or at the beginning of the new line.

   2.  A line break in the input may be eliminated in the output,
provided it is not followed by a space or another line break.  If a line
break is eliminated, it is replaced by a space.
*/