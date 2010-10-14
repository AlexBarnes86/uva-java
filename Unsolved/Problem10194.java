package AOAPC.vol1.SortingSearching;

import java.util.*;

public class Problem10194 {
	public static class Team implements Comparable<Team> {
		public static int [][] goals = null;
		public String name;
		public int points;
		public int wins;
		public int plays;

		public int compareTo(Team t) {
			if(points != t.points)
				return points - t.points;
			else if(wins != t.wins)
				return wins - t.wins;
			//goal difference goes here
			else if(goals != t.goals) {
				//return total_goals - t.total_goals;
			}
			else if(plays != t.plays)
				return plays - t.plays;
			else
				return name.compareTo(t.name);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < N; ++i) {
			String tournament = sc.nextLine();
			int T = Integer.parseInt(sc.nextLine());
			for(int j = 0; j < T; ++j) {
				String team = sc.nextLine();
			}
			int G = Integer.parseInt(sc.nextLine());
			for(int j = 0; j < G; ++j) {
				String game = sc.nextLine();
			}
			System.out.println(tournament);
			System.out.println();
		}
	}
}
