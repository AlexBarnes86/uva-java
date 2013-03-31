package chapter2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Problem10258 {
	public static final boolean DEBUG_FILE = false;
	
	public static final Scanner sc = getScanner();
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				return new Scanner(new File("ProgrammingChallenges/input/chapter2/Problem10258.in"));
			}
			else {
				return new Scanner(System.in);
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
	public static final int N_PROBLEMS = 9;
	
	public static class Problem {
		private List<Integer> submissions = new ArrayList<Integer>();
		private boolean solved = false;
		
		public boolean isSolved() {
			return solved;
		}
		
		public void addSubmission(Integer submissionTime, char status) {
			if(status == 'I' && !solved) {
				submissions.add(20);
			}
			else if(!solved && status == 'C') {
				submissions.add(submissionTime);
				solved = true;
			}
		}
		
		public int getTime() {
			int total = 0;
			
			if(solved) {
				for(Integer time : submissions) {
					total += time;
				}
			}
			
			return total;
		}
	}
	
	public static class Contestant implements Comparable<Contestant> {
		public int id;
		private List<Problem> problems;
		
		public Contestant(int id) {
			this.id = id;
			initProblems();
		}
		
		private void initProblems() {
			problems = new ArrayList<Problem>();
			for(int i = 0; i < N_PROBLEMS; ++i) {
				problems.add(new Problem());
			}
		}
		
		public void submit(int problemId, int time, char status) {
			problems.get(problemId-1).addSubmission(time, status);
		}
		
		public int totalSolved() {
			int total = 0;
			for(Problem problem : problems) {
				if(problem.isSolved()) {
					total++;
				}
			}
			
			return total;
		}
		
		public int totalTime() {
			int total = 0;
			
			for(Problem problem : problems) {
				total += problem.getTime();
			}
			
			return total;
		}
		
		@Override
		public int compareTo(Contestant other) {
			int solved = totalSolved();
			int otherSolved = other.totalSolved();
			
			if(solved == otherSolved) {
				int time = totalTime();
				int otherTime = other.totalTime();
				
				if(time == otherTime) {
					return id - other.id;
				}
				
				return time - otherTime;
			}
			
			return otherSolved - solved;
		}
		
		@Override
		public String toString() {
			return id + " " + totalSolved() + " " + totalTime();
		}
	}
	
	public static void main(String[] args) {
		int testCases = Integer.parseInt(sc.nextLine().trim());
		sc.nextLine();
		
		for(int idx = 0; idx < testCases; ++idx) {
			String line = null;
			HashMap<Integer, Contestant> contestants = new HashMap<Integer, Contestant>();
			
			while(sc.hasNext() && (line = sc.nextLine()) != null && !(line = line.trim()).isEmpty()) {
				String[] tokens = line.split("\\s+");
				int contestantId = Integer.parseInt(tokens[0]);
				int problemId = Integer.parseInt(tokens[1]);
				int time = Integer.parseInt(tokens[2]);
				char status = tokens[3].charAt(0);
				
				if(!contestants.containsKey(contestantId)) {
					contestants.put(contestantId, new Contestant(contestantId));
				}
				
				Contestant contestant = contestants.get(contestantId);
				contestant.submit(problemId, time, status);
			}
			
			if(idx != 0) {
				System.out.println();
			}
			List<Contestant> chart = new ArrayList<Contestant>(contestants.values());
			Collections.sort(chart);
			for(Contestant contestant : chart) {
				System.out.println(contestant);
			}
		}
	}
}
