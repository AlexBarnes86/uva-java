package vol1.BigNumber;

import java.util.*;
import java.math.*;

public class Problem10494 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean div = false;
		while(sc.hasNext()) {
			String line = sc.nextLine();
			if(line.contains("/"))
				div = true;
			else
				div = false;
			line = line.trim();
			line = line.replaceAll(" +", "");
			String[] tokens = line.split("(/|%)");
			if(tokens.length != 2)
				continue;
			BigInteger first = new BigInteger(tokens[0]);
			BigInteger second = new BigInteger(tokens[1]);
			if(div)
				System.out.println(first.divide(second));
			else
				System.out.println(first.mod(second));
		}
	}
}
