//correct
package vol1.String;

import java.util.*;

public class Problem401 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean first = true;
		while(sc.hasNext()) {
			String line = sc.nextLine();
			StringBuilder sb = new StringBuilder(line);
			String reverse = sb.reverse().toString();
			boolean mirror = true, palindrome = true;
			for(int i = 0; i < sb.length(); ++i) {
				char ch1 = line.charAt(i);
				if(ch1 == '0')
					ch1 = 'O';
				char ch2 = reverse.charAt(i);
				if(ch2 == '0')
					ch2 = 'O';
				if(ch1 != ch2)
					palindrome = false;
				if((ch1 == 'E' && ch2 != '3') || (ch1 == '3' && ch2 != 'E') ||
				   (ch1 == 'J' && ch2 != 'L') || (ch1 == 'L' && ch2 != 'J') ||
				   (ch1 == 'S' && ch2 != '2') || (ch1 == '2' && ch2 != 'S') ||
				   (ch1 == 'Z' && ch2 != '5') || (ch1 == '5' && ch2 != 'Z'))
					mirror = false;
				switch(ch1) {
				case 'B': case 'C': case 'D': case 'F': case 'G': case 'K': case 'N':
				case 'P': case 'Q': case 'R': case '4': case '6': case '7': case '9':
					mirror = false;
				}
			}
			if(mirror && palindrome)
				System.out.println(line + " -- is a mirrored palindrome.");
			else if(!mirror && palindrome)
				System.out.println(line + " -- is a regular palindrome.");
			else if(mirror && !palindrome) 
				System.out.println(line + " -- is a mirrored string.");
			else
				System.out.println(line + " -- is not a palindrome.");
			System.out.println();
		}
	}
}
