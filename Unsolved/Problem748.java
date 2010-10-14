package AOAPC.vol1.BigNumber;

import java.util.*;
import java.math.*;

public class Problem748 {
	public static String add(String lhs, String rhs) {
		int carry = 0;
		int max = (lhs.length() > rhs.length() ? lhs.length() : rhs.length());
		lhs = String.format("%"+max+"s", lhs);
		rhs = String.format("%"+max+"s", rhs);
		StringBuilder res = new StringBuilder();
		for(int i = max-1; i >= 0; --i) {
			int first, second;
			if(lhs.charAt(i) == ' ')
				first = 0;
			else
				first = Integer.parseInt(lhs.charAt(i)+"");
			if(rhs.charAt(i) == ' ')
				second = 0;
			else
				second = Integer.parseInt(rhs.charAt(i)+"");
			int add = first+second+carry;
			if(add >= 10)
				carry = 1;
			else
				carry = 0;
			res.append(add%10);
		}
		if(carry > 0)
			res.append(carry);
		res = res.reverse();
		return res.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			String[] tokens = sc.nextLine().split("\\s+");
			String first = tokens[0];
			String second = tokens[1];
			System.out.println(add(first, second));
		}
	}
}
