package chapter4;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10026 {
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
				String fileName = "ProgrammingChallenges/input/chapter4/Problem10026.in";
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
	
	public static class Job implements Comparable<Job> {
		public int index;
		public int duration;
		public int penalty;
		
		public Job(String line) {
			StringTokenizer st = new StringTokenizer(line);
			duration = Integer.parseInt(st.nextToken());
			penalty = Integer.parseInt(st.nextToken());
		}
		
		public int compareTo(Job other) {
			if(penalty*other.duration == other.penalty*duration) {
				return 0;
			}
			else if(penalty*other.duration < other.penalty*duration) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
		@Override
		public String toString() {
			return "I: " + index + " D: " + duration + " P: " + penalty;
		}
	}
	
	public static void main(String[] args) {
		int cases = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < cases; ++i) {
			sc.nextLine();
			int jobCt = Integer.parseInt(sc.nextLine());
			List<Job> jobs = new ArrayList<Job>();
			for(int j = 0; j < jobCt; ++j) {
				Job job = new Job(sc.nextLine());
				job.index = j+1;
				jobs.add(job);
			}
			
			if(i != 0) {
				System.out.println();
			}
			System.out.println(solve(jobs));
		}
	}
	
	private static String solve(List<Job> jobs) {
		debug("Unsorted:");
		debug(jobs);
		
		Collections.sort(jobs);
		
		StringBuilder sb = new StringBuilder();
		for(Job job : jobs) {
			sb.append(job.index + " ");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}