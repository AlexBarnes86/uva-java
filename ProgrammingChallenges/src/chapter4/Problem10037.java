package chapter4;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//Goal is to always get the two slowest across on each iteration
//Can either send the fastest back and forth twice or send the two fastest and then the two slowest
//Pick whichever option is faster each iteration
public class Problem10037 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_INPUT = true;
	public static final Scanner sc = getScanner();
	
	public static void main(String args[]) {
		PrintWriter pw = new PrintWriter(System.out);
		int cases = Integer.parseInt(sc.nextLine());
		sc.nextLine();
		
		for(int i = 0; i < cases; ++i) {
			String line;
			LinkedList<Integer> left = new LinkedList<Integer>();
			int n = Integer.parseInt(sc.nextLine());
			while(sc.hasNext() && !(line = sc.nextLine()).isEmpty()) {
				left.add(Integer.parseInt(line));
			}
			Collections.sort(left);
			
			if(i != 0) {
				pw.println();
			}
			
			List<String> send = new ArrayList<String>();
			
			int totalTime = 0;
			if(left.size() == 1) {
				totalTime += left.remove();
				send.add(totalTime+"");
			}
			
			while(!left.isEmpty()) {
				Integer fastest = left.get(0);
				Integer secondFastest = left.get(1);
				Integer slowest = left.removeLast();
				Integer secondSlowest = left.removeLast();
				
				if(left.isEmpty()) {
					totalTime += secondFastest;
					send.add(fastest + " " + secondFastest);
					continue;
				}
				
				//Send the fastest back and forth twice taking the slowest people with them
				int option1 = slowest + 2*fastest + secondSlowest; //(fastest, slowest), (fastest), (fastest, secondSlowest), (fastest)
				//Send the two fastest, then the two slowest
				int option2 = 2*secondFastest + fastest + slowest; //(fastest, secondFastest), (fastest), (secondSlowest, slowest), (secondFastest)
				
				if(option1 <= option2) { //in the case that there are only 3 left option1 <= option2 is always true
					send.add(fastest + " " + slowest);			totalTime += slowest;
					send.add(fastest.toString());				totalTime += fastest;
					send.add(fastest + " " + secondSlowest);	totalTime += secondSlowest;
					if(left.size() == 1) { //No reason to send them back
						left.remove();
						break;
					}
					send.add(fastest.toString());			totalTime += fastest;
				}
				else {
					send.add(fastest + " " + secondFastest);	totalTime += secondFastest;
					send.add(fastest.toString());				totalTime += fastest;
					send.add(secondSlowest + " " + slowest);	totalTime += slowest;
					send.add(secondFastest.toString());			totalTime += secondFastest;
				}
			}
			
			pw.println(totalTime);
			for(String str : send) {
				pw.println(str);
			}
		}
		
		pw.flush();
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		if(!DEBUG_INPUT) {
			return new Scanner(System.in);
		}
		else {
			try {
				String debugFile = "ProgrammingChallenges/input/chapter4/Problem10037.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
}