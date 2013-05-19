package unsolved; //Chapter 2

import java.io.File;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem10149 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_INPUT = true;
	
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
	
	public static final Scanner sc = getScanner();
	public static Scanner getScanner() {
		if(!DEBUG_INPUT) {
			return new Scanner(System.in);
		}
		else {
			try {
				String debugFile = "ProgrammingChallenges/input/chapter2/Problem10149.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
	
	public static ArrayList<Integer> sort(List<Integer> dice) {
		ArrayList<Integer> diceList = new ArrayList<Integer>(dice);
		Collections.sort(diceList);
		
		return diceList;
	}
	
	public static interface Rule {
		public int eval(List<Integer> dice);
	}
	
	public static class SumRule implements Rule {
		private int scoringValue;
		
		public SumRule(int scoringValue, boolean minRequired, int minSpotsRequired) {
			this.scoringValue = scoringValue;
		}
		
		public SumRule(int scoringValue) {
			this.scoringValue = scoringValue;
		}
		
		@Override
		public int eval(List<Integer> dice) {
			int score = 0;
			
			for(int i = 0; i < dice.size(); ++i) {
				if(dice.get(i) == scoringValue) {
					score += scoringValue;
				}
			}
			
			return score;
		}
		
		@Override
		public String toString() {
			return "SumRule {scoringValue: " + scoringValue + "}";
		}
	}
	
	public static class OfAKindRule implements Rule {
		private int minSpotsRequired;
		
		public OfAKindRule(int minSpotsRequired) {
			this.minSpotsRequired = minSpotsRequired;
		}
		
		@Override
		public int eval(List<Integer> dice) {
			int [] dieCt = new int[6];
			int scoringValue = 0;
			for(int i = 0; i < dice.size(); ++i) {
				dieCt[dice.get(i)-1]++;
				if(dieCt[dice.get(i)-1] >= minSpotsRequired) {
					scoringValue = dice.get(i);
				}
			}
			
			if(scoringValue > 0) {
				return scoringValue * dieCt[scoringValue-1];
			}
			
			return 0;
		}
		
		@Override
		public String toString() {
			return "OfAKindRule {minSpotsRequired: " + minSpotsRequired + "}";
		}
	}
	
	public static class StraightRule implements Rule {
		private int straightSize;
		private int scoreValue;
		
		public StraightRule(int straightSize, int scoreValue) {
			this.straightSize = straightSize;
			this.scoreValue = scoreValue;
		}
		
		@Override
		public int eval(List<Integer> dice) {
			ArrayList<Integer> diceList = sort(dice);
			
			int run = 1;
			int longestRun = 0;
			for(int i = 0; i < diceList.size()-1; ++i) {
				if(diceList.get(i) == diceList.get(i+1)-1) {
					run++;
					if(run > longestRun) {
						longestRun = run;
					}
				}
				else {
					run = 1;
				}
			}
			
			if(longestRun >= straightSize) {
				return scoreValue;
			}
			
			return 0;
		}
		
		@Override
		public String toString() {
			return "StraightRule {straightSize: " + straightSize + ", scoreValue: " + scoreValue + "}";
		}
	}
	
	public static class ChanceRule implements Rule {
		@Override
		public int eval(List<Integer> dice) {
			int total = 0;
			for(int i = 0; i < dice.size(); ++i) {
				total += dice.get(i);
			}
			
			return total;
		}
		
		@Override
		public String toString() {
			return "ChanceRule";
		}
	}
	
	public static class YahtzeeRule implements Rule {
		@Override
		public int eval(List<Integer> dice) {
			int val = dice.get(0);
			for(int i = 1; i < dice.size(); ++i) {
				if(dice.get(i) != val) {
					return 0;
				}
			}
			
			return 50;
		}
		
		@Override
		public String toString() {
			return "YahtzeeRule";
		}
	}
	
	public static class FullHouseRule implements Rule {
		@Override
		public int eval(List<Integer> dice) {
			ArrayList<Integer> diceList = sort(dice);
			int first = diceList.get(0);
			int second = diceList.get(1);
			int third = diceList.get(2);
			int fourth = diceList.get(3);
			int fifth = diceList.get(4);
			
			if((first == second && second == third && fourth == fifth) ||
				(first == second && third == fourth && fourth == fifth)) {
				return 40;
			}
			
			return 0;
		}
		
		@Override
		public String toString() {
			return "FullHouseRule";
		}
	}
	
	private static final List<Rule> rules = createRules();
	
	public static ArrayList<Rule> createRules() {
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new SumRule(1));
		rules.add(new SumRule(2));
		rules.add(new SumRule(3));
		rules.add(new SumRule(4));
		rules.add(new SumRule(5));
		rules.add(new SumRule(6));
		rules.add(new ChanceRule());
		rules.add(new OfAKindRule(3));
		rules.add(new OfAKindRule(4));
		rules.add(new YahtzeeRule());
		rules.add(new StraightRule(4, 25));
		rules.add(new StraightRule(5, 35));
		rules.add(new FullHouseRule());
		
		return rules;
	}
	
	public static class Node {
		public int rulePosition;
		public Rule rule;
		public Stack<Integer> attempts;
		public Node next;
		public int maxScore;
		
		public Node(Rule rule, int rulePosition, Stack<Integer> attempts) {
			this.rule = rule;
			this.attempts = attempts;
			this.rulePosition = rulePosition;
		}
		
		public int solve(List<List<Integer>> game) {
			BitSet usedRolls = new BitSet();
			usedRolls.clear(0, game.size());
			BitSet usedRules = new BitSet();
			usedRules.clear(0, rules.size());
			return solve(game, usedRolls, usedRules);
		}
		
		public int solve(List<List<Integer>> game, BitSet usedRolls, BitSet usedRules) {
			BitSet newUsedRules = new BitSet();
			newUsedRules.or(usedRules);
			newUsedRules.set(rulePosition);
			
			if(attempts.size() == 0) {
				if(next != null) {
					return next.solve(game, usedRolls, newUsedRules);
				}
				
				return 0;
			}
			
			int maxTotal = 0;
			
			for(Integer roll : attempts) {
				int maxRoll = -1;
				
				if(!usedRolls.get(roll)) {
					int score = rule.eval(game.get(roll));
					if(maxScore < score) {
						maxScore = score;
						maxRoll = roll;
					}
				}
				
				if(next != null) {
					BitSet newUsedRolls = new BitSet();
					newUsedRolls.or(usedRolls);
					
					if(maxRoll > 0) {
						newUsedRolls.set(maxRoll);
					}
					
					int total = maxScore + next.solve(game, newUsedRolls, newUsedRules);
					if(maxTotal < total) {
						maxTotal = total;
					}
				}
			}
			
			return maxTotal;
		}
		
		@Override
		public String toString() {
			return "Node: {rulePosition: " + rulePosition + ", rule: " + rule + ", attempts: " + attempts + "}";
		}
	}
	
	public static String solve(List<List<Integer>> game) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		for(int i = 0; i < rules.size(); ++i) {
			Rule rule = rules.get(i);
			Stack<Integer> attempts = new Stack<Integer>();
			for(int j = 0; j < game.size(); ++j) {
				List<Integer> row = game.get(i);
				if(rule.eval(row) > 0) {
					attempts.add(j);
				}
			}
			
			nodes.add(new Node(rule, i, attempts));
		}
		
		List<Node> originalOrder = new ArrayList<Node>(nodes);
		Collections.sort(nodes, new Comparator<Node>() {
			public int compare(Node lhs, Node rhs) {
				return lhs.attempts.size() - rhs.attempts.size();
			}
		});
		
		for(int i = 0; i < nodes.size() - 1; ++i) {
			nodes.get(i).next = nodes.get(i+1);
		}
		
		int total = nodes.get(0).solve(game);
		
		StringBuilder sb = new StringBuilder();
		for(Node node : originalOrder) {
			sb.append(node.maxScore + " ");
		}
		return sb.toString() + "total: " + total;
	}
	
	public static void main(String args[]) {
		while(sc.hasNext()) {
			long timeStart = System.currentTimeMillis();
			List<List<Integer>> game = new ArrayList<List<Integer>>();
			for(int i = 0; i < 13; ++i) {
				List<Integer> row = new ArrayList<Integer>();
				String line = sc.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				
				for(int j = 0; j < 5; ++j) {
					row.add(Integer.parseInt(st.nextToken()));
				}
				
				game.add(row);
			}
			
			System.out.println(solve(game));
			debug(System.currentTimeMillis()-timeStart + "ms");
		}
	}
}