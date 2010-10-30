package vol1.BigNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Problem748 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("Problem748.in"));
		
		String line = "";
		while((line = br.readLine()) != null) {
			String[] tokens = line.split("\\s+");
			BigDecimal bd = new BigDecimal(tokens[0]);
			int power = Integer.parseInt(tokens[1]);
			String output = bd.pow(power).stripTrailingZeros().toPlainString();
			if(output.startsWith("0."))
				output = output.substring(1);
			System.out.println(output);
		}
	}
}
