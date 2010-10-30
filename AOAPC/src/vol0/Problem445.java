//correct
package vol0;

import java.util.*;

public class Problem445 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			int repeat = 0;
			for(int i = 0; i < line.length(); ++i) {
				char ch = line.charAt(i);
				switch(ch) {
				case '0': case '1': case '2': case '3':case '4': case '5':case '6': case '7':case '8': case '9':
					repeat += Integer.valueOf(""+ch);
				break;
				case '!':
					System.out.println();
					repeat = 0;
				break;
				case 'b':
					for(int j = 0; j < repeat; ++j)
						System.out.print(" ");
					if(repeat == 0)
						System.out.print(" ");
					repeat = 0;
				break;
				default:
					for(int j = 0; j < repeat; ++j)
						System.out.print(ch);
					if(repeat == 0)
						System.out.print(ch);
					repeat = 0;
				break;
				}
			}
			System.out.println();
		}
	}
}
