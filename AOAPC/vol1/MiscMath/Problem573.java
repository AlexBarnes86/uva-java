package AOAPC.vol1.MiscMath;

import java.io.File;
import java.util.Scanner;

public class Problem573 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("Problem573.in"));
		//Scanner sc = new Scanner(System.in);
		String line;
		while((line = sc.nextLine()) != null) {
			String[] tokens = line.split("\\s+");
			long height = Integer.parseInt(tokens[0])*100;
			if(height == 0)
				return;
			long climb = Long.parseLong(tokens[1])*100;
			long slide = Long.parseLong(tokens[2])*100;
			long fatigue = Long.parseLong(tokens[3])*climb/100;
			
			long curHeight = 0;
			long days = 0;
			//System.out.println("Height: " + height + " climb: " + climb + " slide: " + slide + " fatigue: " + fatigue);
			while(true) {
				//System.out.println("Day: " + days + " height: " + curHeight + " target: " + height);
				long move = climb - days*fatigue;
				if(move > 0)
					curHeight += move;
				//curHeight += (int)Math.max(0, climb - (days)*(fatigue)); //dependent on double...
				days++;
				if(curHeight > height)
					break;
				curHeight -= slide;
				if(curHeight < 0)
					break;
			}
			if(curHeight > height)
				System.out.println("success on day " + days);
			else
				System.out.println("failure on day " + days);
		}
	}
}
