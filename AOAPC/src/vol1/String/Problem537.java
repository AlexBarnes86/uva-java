//correct
package vol1.String;

import java.util.*;
import java.text.DecimalFormat;
import java.util.regex.*;

public class Problem537 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setGroupingUsed(false);
		int n = Integer.parseInt(sc.nextLine());
		Pattern pattern = Pattern.compile("(P|U|I)=([0-9]+(\\.[0-9]+)?)(m|k|M)?(A|V|W)");
		for(int i = 0; i < n; ++i) {
			boolean power = false, voltage = false, current = false;
			double ans = 0;
			String line = sc.nextLine();
			Matcher matcher = pattern.matcher(line);
			//first val
			matcher.find();
			double val1 = Double.parseDouble(matcher.group(2));
			String expr = matcher.group(0);
			
			if(expr.contains("m"))
				val1 /= 1000;
			else if(expr.contains("k"))
				val1 *= 1000;
			else if(expr.contains("M"))
				val1 *= 1000000;
			
			if(expr.contains("P"))
				power = true;
			else if(expr.contains("U"))
				voltage = true;
			else if(expr.contains("I"))
				current = true;
			
			//second val
			matcher.find();
			double val2 = Double.parseDouble(matcher.group(2));
			expr = matcher.group(0);
			if(expr.contains("m"))
				val2 /= 1000;
			else if(expr.contains("k"))
				val2 *= 1000;
			else if(expr.contains("M"))
				val2 *= 1000000;
			
			if(expr.contains("P")) {
				power = true;
				ans = val2/val1;
			}
			else if(expr.contains("U")) {
				voltage = true;
				if(power)
					ans = val1/val2;
				else
					ans = val1*val2;
			}
			else if(expr.contains("I")) {
				current = true;
				if(power)
					ans = val1/val2;
				else
					ans = val1*val2;
			}
			
			String output;
			if(!power)
				output = "P="+df.format(ans)+"W";
			else if(!voltage)
				output = "U="+df.format(ans)+"V";
			else
				output = "I="+df.format(ans)+"A";
			System.out.println("Problem #"+(i+1));
			System.out.println(output);
			System.out.println();
		}
	}
}
