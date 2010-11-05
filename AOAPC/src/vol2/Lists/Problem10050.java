package vol2.Lists;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problem10050 {
	public static String getday(int d) {
		switch(d) {
		case 0: return "Sunday";
		case 1: return "Monday";
		case 2: return "Tuesday";
		case 3: return "Wednesday";
		case 4: return "Thursday";
		case 5: return "Friday";
		case 6: return "Saturday";
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem10050.in"));

		int T = Integer.parseInt(br.readLine().trim());
		for(int i = 0; i < T; ++i) {
			int days = Integer.parseInt(br.readLine().trim());
			int P = Integer.parseInt(br.readLine().trim());
			
			ArrayList<Integer> parties = new ArrayList<Integer>();
			for(int j = 0; j < P; ++j) {
				parties.add(Integer.parseInt(br.readLine().trim()));
			}
			
			int hartals = 0;
			for(int d = 1; d <= days; ++d) {
				System.out.print(d + " " + getday(d%7) + ": ");
				if(d % 7 == 5) { //skip fridays
					System.out.println();
					continue;
				}
				for(int p = 0; p < P; ++p) {
					if(d % parties.get(p) == 0) {
						System.out.print("hartal from " + p + " (" + parties.get(p) + ")");
						hartals++;
						break;
					}
				}
				System.out.println();
			}
			
			System.out.println(hartals);
		}
	}
}
