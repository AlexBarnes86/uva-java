package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class Problem127 {
	public static final int HEARTS = 0;
	public static final int CLUBS = 1;
	public static final int DIAMONDS = 2;
	public static final int SPADES = 3;
	static class Card {
		private int suite;
		private int value;
		public Card left, right, below;
		
		public Card(String val) {
			switch(val.charAt(0)) { //value
			case '2': case '3' : case '4': case '5': case '6': case '7': case '8': case '9':
				value = Integer.parseInt(val.charAt(0)+"");
				break;
			case 'A':
				value = 1;
				break;
			case 'T':
				value = 10;
				break;
			case 'J':
				value = 11;
				break;
			case 'Q':
				value = 12;
				break;
			case 'K':
				value = 13;
				break;
			}
			switch(val.charAt(1)) { //suite
			case 'H':
				suite = HEARTS;
				break;
			case 'C':
				suite = CLUBS;
				break;
			case 'D':
				suite = DIAMONDS;
				break;
			case 'S':
				suite = SPADES;
				break;
			}
		}
		
		public Card(int s, int v) {
			suite = s;
			value = v;
		}
		
		public boolean sameSuite(Card c) {
			return suite == c.suite;
		}
		
		public boolean sameValue(Card c) {
			return value == c.value;
		}
		
		public boolean sameSuiteOrValue(Card c) {
			return sameSuite(c) || sameValue(c);
		}
		
		public String toString() {
			String ret = "";
			switch(value) {
			case 2: case 3 : case 4: case 5: case 6: case 7: case 8: case 9:
				ret += value;
				break;
			case 1:
				ret += "A";
				break;
			case 10:
				ret += "T";
				break;
			case 11:
				ret += "J";
				break;
			case 12:
				ret += "Q";
				break;
			case 13:
				ret += "K";
				break;
			}
			switch(suite) {
			case HEARTS:
				ret += "H";
				break;
			case CLUBS:
				ret += "C";
				break;
			case DIAMONDS:
				ret += "D";
				break;
			case SPADES:
				ret += "S";
				break;
			}
			return ret;
		}
	}
	
	static class Tableau {
		//first and last refer to top card in the first and last stacks
		private Card first, last;
		
		private boolean is3Match(Card c) {
			return c.left != null && c.left.left != null && c.left.left.left != null && c.sameSuiteOrValue(c.left.left.left);
		}
		
		private boolean is1Match(Card c) {
			return c.left != null && c.sameSuiteOrValue(c.left);
		}
		
		private void makeLast(Card c) {
			last.right = c;
			c.left = last;
			last = c;
		}
		
		private void join(Card c) {
			if(c.left != null)
				c.left.right = c.right;
			if(c.right != null)
				c.right.left = c.left;
		}
		
		private void detach(Card c) {
			if(c.left != null)
				c.left.right = c.below;
			if(c.right != null)
				c.right.left = c.below;
			c.below.left = c.left;
			c.below.right = c.right;
		}
		
		private void move3left(Card c) {
			c.below = c.left.left.left;
			if(c.below == first)
				first = c;
			c.left = c.below.left;
			c.right = c.below.right;
			if(c.left != null)
				c.left.right = c;
			if(c.right != null)
				c.right.left = c;
		}
		
		private void move1left(Card c) {
			c.below = c.left;
			if(c.below == first)
				first = c;
			c.left = c.below.left;
			c.right = c.below.right;
			if(c.left != null)
				c.left.right = c;
			if(c.right != null)
				c.right.left = c;
		}
		
		private void placeCard(Card c) {
			if(c == null)
				return;
			
			if(is3Match(c)) {
				if(c.below == null) { //if stack is empty join left and right stacks
					if(last == c)
						last = last.left;
					join(c);
				}
				else { //else make card below top of stack
					if(last == c)
						last = last.below;
					detach(c);
				}
				
				move3left(c);
				
				placeCard(c);
				return;
			} else if(is1Match(c)) {				
				if(c.below == null) {
					join(c);
				}
				else {
					if(last == c)
						last = last.below;
					detach(c);
				}
				
				move1left(c);
				
				placeCard(c);
				return;
			}
			
			placeCard(c.right);
		}
		
		public void add(Card c) {
			if(first == null) {
				first = c;
				last = c;
			}
			else {
				makeLast(c);
				placeCard(c);
			}
		}
		
		private int countStack(Card c) {
			int sum = 1;
			Card temp = c;
			while(temp.below != null) {
				sum++;
				temp = temp.below;
			}
			return sum;
		}
		
		private void debugStack(Card temp) {
			while(temp != null) {
				System.out.print(temp + " ");
				temp = temp.below;
			}
		}
		
		public void debug() {
			Card temp = first;
			while(temp != null) {
				debugStack(temp);
				temp = temp.right;
				System.out.println();
			}
			System.out.println("----------");
		}
		
		public String toString() {
			Card temp = first;
			ArrayList<Integer> stacks = new ArrayList<Integer>();
			while(temp.right != null) {
				stacks.add(countStack(temp));
				temp = temp.right;
			}
			stacks.add(countStack(temp));
			StringBuilder sb = new StringBuilder();
			sb.append(stacks.size() + " piles remaining: ");
			for(int i = 0; i < stacks.size(); ++i) {
				if(i != stacks.size() - 1) {
					sb.append(stacks.get(i) + " ");
				}
				else
					sb.append(stacks.get(i));
			}
			return sb.toString();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//long startTime = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem127.in"));
		String line = "";
		
		while(true) {
			LinkedList<String> strings = new LinkedList<String>();
			LinkedList<Card> deck = new LinkedList<Card>();
			line = br.readLine().trim();
			if(line.equals("#"))
				break;
			Collections.addAll(strings, line.split(" "));
			line = br.readLine().trim();
			Collections.addAll(strings, line.split(" "));
			for(String str : strings) {
				deck.add(new Card(str));
			}
			Tableau solution = new Tableau();
			for(Card c : deck) {
				//System.out.print("Adding: " + c + " ");
				solution.add(c);
				//System.out.println(solution);
				//solution.debug();
			}
			System.out.println(solution);
		}
		//System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + "ms");
	}
}