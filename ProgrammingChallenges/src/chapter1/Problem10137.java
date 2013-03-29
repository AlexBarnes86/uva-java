package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem10137 {
	private static final Scanner sc = new Scanner(System.in);
	
	public static String format(long cents) {
		long dollars = (cents / 100);
		String decimal = (cents % 100) + "";
		if(decimal.length() < 2) {
			decimal += "0";
		}
		
		return "$" + dollars + "." + decimal;
	}
	
	public static void main(String[] args) {
		while(sc.hasNext()) {
			int students = Integer.parseInt(sc.nextLine().trim());
			if(students == 0) {
				break;
			}
			
			long total = 0;
			List<Long> money = new ArrayList<Long>(students);
			
			for(int i = 0; i < students; ++i) {
				long val = Long.parseLong(sc.nextLine().trim().replace(".", ""));
				money.add(val);
				total += val;
			}
			
			long avg = (long)(total * 1.0 / students + 0.5);
			long posTransfer = 0;
			long negTransfer = 0;
			
			for(int i = 0; i < students; ++i) {
				long val = money.get(i);
				if(val > avg) {
					posTransfer += (val - avg);
				}
				else {
					negTransfer += (avg - val);
				}
			}
			
			long transfer = (negTransfer < posTransfer ? negTransfer : posTransfer);
			
			System.out.println(format(transfer));
		}
	}
}