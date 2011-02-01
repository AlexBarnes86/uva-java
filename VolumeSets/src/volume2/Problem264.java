package volume2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Problem264 {
	public static void main(String [] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/volume2/Problem264.in"));
		
		String line;
		while((line = br.readLine()) != null) {
			line = line.trim();
			long i = Long.parseLong(line);
			long diagonal = (long)((1+Math.sqrt(1+8*(i-1)))/2.0);
			long ct = (diagonal*(diagonal + 1)) / 2;
			long diff = ct - i;
//			System.out.print(i + ") diagonal: " + diagonal + " ct: " + ct + " diff: " + diff + " ");
			if(diagonal % 2 == 0) { //even, diagonal counts down
				System.out.println("TERM " + i + " IS " + (diagonal - diff) + "/" + (1+diff));
			}
			else { //odd, diagonal counts up
				System.out.println("TERM " + i + " IS " + (1+diff) + "/" + (diagonal - diff));
			}
		}
	}
}
