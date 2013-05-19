package chapter4;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10138 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_INPUT = true;
	public static final Scanner sc = getScanner();
	
	public static final int ACCT_CHARGE = 200;
	public static final int TRIP_CHARGE = 100;
	
	public static class Time implements Comparable<Time> {
		int month;
		int day;
		int hour;
		int minute;
		
		public Time(String line) {
			StringTokenizer st = new StringTokenizer(line);
			month = Integer.parseInt(st.nextToken(":"));
			day = Integer.parseInt(st.nextToken(":"));
			hour = Integer.parseInt(st.nextToken(":"));
			minute = Integer.parseInt(st.nextToken(":"));
		}
		
		public int compareTo(Time other) {
			if(month == other.month) {
				if(day == other.day) {
					if(hour == other.hour) {
						return minute - other.minute;
					}
					return hour - other.hour;
				}
				return day - other.day;
			}
			return month - other.month;
		}
		
		@Override
		public String toString() {
			String monthStr = String.format("%2d", month).replace(" ", "0");
			String dayStr = String.format("%2d", day).replace(" ", "0");
			String hourStr = String.format("%2d", hour).replace(" ", "0");
			String minuteStr = String.format("%2d", minute).replace(" ", "0");
			
			return String.format("%s:%s:%s:%s", monthStr, dayStr, hourStr, minuteStr);
		}
	}
	
	public static class Record implements Comparable<Record> {
		public String licensePlate;
		public Time time;
		public String type; //enter or exit
		public int location;
		
		public Record(String line) {
			StringTokenizer st = new StringTokenizer(line);
			licensePlate = st.nextToken();
			time = new Time(st.nextToken());
			type = st.nextToken();
			location = Integer.parseInt(st.nextToken());
		}
		
		public boolean isEnter() {
			return "enter".equalsIgnoreCase(type);
		}
		
		public boolean isExit() {
			return "exit".equalsIgnoreCase(type);
		}
		
		@Override
		public int compareTo(Record other) {
			if(licensePlate.equals(other.licensePlate)) {
				return time.compareTo(other.time);
			}
			return licensePlate.compareTo(other.licensePlate);
		}
		
		@Override
		public String toString() {
			return licensePlate + " " + time + " " + type + " " + location;
		}
	}
	
	public static Map<String, Integer> solve(List<Record> records, List<Integer> rates) {
		Map<String, Integer> soln = new LinkedHashMap<String, Integer>();
		if(records.size() == 0) {
			return soln;
		}
		debug("Hourly Rate: " + rates);
		Collections.sort(records);
		debug("Sorted:");
		debug(records);
		
		Record prevRecord = records.get(0);
		for(int i = 1; i < records.size(); ++i) {
			Record curRecord = records.get(i);
			if(curRecord.licensePlate.equals(prevRecord.licensePlate)) {
				if(prevRecord.isEnter() && curRecord.isExit()) {
					Integer curTotalFare = soln.get(prevRecord.licensePlate);
					if(curTotalFare == null) {
						curTotalFare = new Integer(0);
					}
					int distance = Math.abs(curRecord.location - prevRecord.location);
					int rate = rates.get(prevRecord.time.hour);
					int fare = distance*rate + TRIP_CHARGE;
					soln.put(prevRecord.licensePlate, curTotalFare + fare);
				}
			}
			prevRecord = curRecord;
		}
		
		debug("");
		return soln;
	}
	
	public static void main(String args[]) {
		int cases = Integer.parseInt(sc.nextLine());
		sc.nextLine(); //blank line between cases in input
		
		for(int i = 0; i < cases; ++i) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			List<Integer> fareRate = new ArrayList<Integer>();
			while(st.hasMoreTokens()) {
				fareRate.add(Integer.parseInt(st.nextToken()));
			}
			
			List<Record> records = new ArrayList<Record>();
			String line;
			while(sc.hasNext() && !(line = sc.nextLine().trim()).isEmpty()) {
				records.add(new Record(line));
			}
			
			Map<String, Integer> solution = solve(records, fareRate);
			StringBuilder sb = new StringBuilder();
			for(Entry<String, Integer> entry : solution.entrySet()) {
				int fare = entry.getValue() + ACCT_CHARGE;
				int dollars = fare/100;
				int cents = fare%100;
				String centStr = String.format("%2d", cents).replace(" ", "0");
				sb.append(String.format("%s $%d.%s\n", entry.getKey(), dollars, centStr));
			}
			if(sb.length() > 0) {
				sb.deleteCharAt(sb.length()-1); // extra newline
			}
			
			if(i != 0) {
				System.out.println(); //blank line between cases in output
			}
			System.out.println(sb.toString());
		}
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
				String debugFile = "ProgrammingChallenges/input/chapter4/Problem10138.in";
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