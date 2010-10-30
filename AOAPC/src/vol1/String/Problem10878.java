//correct
package vol1.String;

import java.io.File;
import java.util.*;

public class Problem10878 {
	public static void convert(String line) {
		if(!line.contains("."))
			return;
		line = line.substring(1, line.length()-1).replaceAll("\\.", "");
		line = line.replaceAll(" ", "0");
		line = line.replaceAll("o", "1");
		StringBuilder temp = new StringBuilder(line);
		line = temp.reverse().toString();
		
		int sum = 0;
		for(int i = 0; i < line.length(); ++i) {
			if(line.charAt(i) == '1')
				sum += Math.pow(2, i);
		}
		System.out.print((char)sum);
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);//new File("Problem10878.input"));
		while(sc.hasNext()) {
			convert(sc.nextLine());
		}
	}
}
