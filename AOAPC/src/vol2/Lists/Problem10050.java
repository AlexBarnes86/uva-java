package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problem10050 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem10050.in"));

		int T = Integer.parseInt(br.readLine().trim());
		for(int i = 0; i < T; ++i) {
			int D = Integer.parseInt(br.readLine().trim());
			int P = Integer.parseInt(br.readLine().trim());
			
			boolean [] days = new boolean[D];
			int hartals = 0;
			for(int j = 0; j < P; ++j) {
				int inc = Integer.parseInt(br.readLine());
				for(int d = inc-1; d < D; d+=inc) {
					if(d%7 == 5 || d%7 == 6)
						continue;
					if(days[d] == false)
						hartals++;
					days[d] = true;
				}
			}
			
			System.out.println(hartals);
		}
	}
}
