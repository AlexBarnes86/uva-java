package chapter4;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10191 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	
	public static void debug(Object msg) {
		if(DEBUG) {
			System.out.print("DEBUG: ");
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static final Scanner sc = getScanner();
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				String fileName = "ProgrammingChallenges/input/chapter4/Problem10191.in";
				Scanner sc = new Scanner(new File(fileName));
				System.out.println("Reading from: " + fileName);
				return sc;
			}
			else {
				return new Scanner(System.in);
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
	
	public static class Time {
		public int hour;
		public int minute;
		
		private Time() {}
		
		public Time(String time) {
			StringTokenizer st = new StringTokenizer(time);
			hour = Integer.parseInt(st.nextToken(":"));
			minute = Integer.parseInt(st.nextToken());
		}
		
		public int minus(Time other) {
			return (hour*60+minute) - (other.hour*60+other.minute);
		}
		
		public static Time randomTime() {
			Time t = new Time();
			t.hour = (int)(Math.random()*8)+10;
			t.minute = (int)(Math.random()*60);
			return t;
		}
		
		@Override
		public String toString() {
			return String.format("%2d:%2d", hour, minute).replace(" ", "0");
		}
	}
	
	public static class TimeSlot implements Comparable<TimeSlot> {
		public Time start;
		public Time end;
		public String activity;
		
		public TimeSlot(String line) {
			StringTokenizer st = new StringTokenizer(line);
			start = new Time(st.nextToken());
			end = new Time(st.nextToken());
			activity = "";
			if(line.length() > 12) {
				activity = line.substring(12);
			}
		}
		
		public static TimeSlot randomSlot() {
			Time startTime = Time.randomTime();
			Time endTime = Time.randomTime();
			
			while(endTime.minus(startTime) < 0) {
				startTime = Time.randomTime();
				endTime = Time.randomTime();
			}
			
			return new TimeSlot(startTime + " " + endTime + " Randomly created"); 
		}
		
		@Override
		public int compareTo(TimeSlot other) {
			return start.minus(other.start);
		}
		
		@Override
		public String toString() {
			return start + " " + end + " " + activity;
		}
	}
	
	public static List<TimeSlot> randomSchedule() {
		List<TimeSlot> schedule = new ArrayList<TimeSlot>();
		
		for(int i = 0; i < 5; ++i) {
			schedule.add(TimeSlot.randomSlot());
		}
		
		return schedule;
	}
	
	public static void main(String[] args) {
		int day = 1;
		while(sc.hasNext()) {
			int n = Integer.parseInt(sc.nextLine());
			
			List<TimeSlot> schedule = new ArrayList<TimeSlot>();
//			List<TimeSlot> schedule = randomSchedule();
//			
			for(int i = 0; i < n; ++i) {
				TimeSlot ts = new TimeSlot(sc.nextLine());
				schedule.add(ts);
			}
			
			schedule.add(new TimeSlot("00:00 10:00 Morning"));
			schedule.add(new TimeSlot("18:00 24:00 Evening"));
			Collections.sort(schedule);
			
			int max = 0;
			Time sleepTime = null;
			for(int i = 0; i < schedule.size() - 1; ++i) {
				Time endFirst = schedule.get(i).end;
				Time startSecond = schedule.get(i+1).start;
				
				int duration = startSecond.minus(endFirst);
				if(max < duration) {
					max = duration;
					sleepTime = endFirst;
				}
			}
			
			String durationStr = "";
			if(max < 60) {
				durationStr = max + " minutes.";
			}
			else {
				durationStr = (max / 60) + " hours and " + (max % 60) + " minutes.";
			}
			
			System.out.println("Day #" + day + ": the longest nap starts at " + sleepTime + " and will last for " + durationStr);
			day++;
		}
	}
}