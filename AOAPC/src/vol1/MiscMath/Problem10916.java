package vol1.MiscMath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Problem10916 {
	public static final int [] table = 
	   {3, 5, 8, 12, 20, 34, 57, 98, 170, 300, 536, 966, 1754, 3210, 5910, 10944, 20366, 38064, 71421, 134480, 254016};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = 1;
//		BigDecimal logSum = new BigDecimal("0");
//		for(int i = 2; i <= 22; ++i) {
//			int bits = (int)Math.pow(2,i);
//			while(logSum.compareTo(new BigDecimal(bits)) < 0) {
//				n++;
//				logSum = logSum.add(new BigDecimal((Math.log(n))/(Math.log(2))));
//			}
//			System.out.print(n-1 + ", ");
//		}
		int n = 0;
		while((n = Integer.parseInt(br.readLine())) != 0) {
			int v = (n - 1960)/10;
			System.out.println(table[v]);
		}
	}
}
