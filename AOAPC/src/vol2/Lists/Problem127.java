package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Problem127 {
	public static final int HEARTS = 0;
	public static final int CLUBS = 1;
	public static final int DIAMONDS = 2;
	public static final int SPADES = 3;
	static class Card {
		private int suite;
		private int value;
		
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
	}
	
	public static void placeCard(ArrayList<ArrayList<Card>> tableau, int pos) {
		ArrayList<Card> stack = tableau.get(pos);
		Card topCard = stack.get(stack.size()-1);
		
		if(pos - 3 >= 0) {
			ArrayList<Card> leftStack = tableau.get(pos-3);
			Card topLeftCard = leftStack.get(leftStack.size()-1);
			if(topCard.sameSuiteOrValue(topLeftCard)) {
				leftStack.add(topCard);
				stack.remove(stack.size()-1);
				if(stack.isEmpty())
					tableau.remove(pos);
				placeCard(tableau, pos - 3);
				for(int i = pos - 2; i < tableau.size(); ++i)
					placeCard(tableau, i);
				return;
			}
		}
		
		if(pos - 1 >= 0) {
			ArrayList<Card> leftStack = tableau.get(pos-1);
			Card topLeftCard = leftStack.get(leftStack.size()-1);
			if(topCard.sameSuiteOrValue(topLeftCard)) {
				leftStack.add(topCard);
				stack.remove(stack.size()-1);
				if(stack.isEmpty())
					tableau.remove(pos);
				placeCard(tableau, pos - 1);
				for(int i = pos; i < tableau.size(); ++i)
					placeCard(tableau, i);
				return;
			}
		}
		
	}
	
	private static ArrayList<ArrayList<Card>> solve(ArrayList<Card> deck) {
		ArrayList<ArrayList<Card>> tableau = new ArrayList<ArrayList<Card>>();
		for(Card card : deck) {
			ArrayList<Card> stack = new ArrayList<Card>();
			stack.add(card);
			tableau.add(stack);
			placeCard(tableau, tableau.size()-1);
		}
		return tableau;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem127.in"));
		String line = "";
		
		while(true) {
			ArrayList<String> strings = new ArrayList<String>();
			ArrayList<Card> deck = new ArrayList<Card>();
			line = br.readLine().trim();
			if(line.equals("#"))
				break;
			Collections.addAll(strings, line.split(" "));
			line = br.readLine().trim();
			Collections.addAll(strings, line.split(" "));
			for(String str : strings) {
				deck.add(new Card(str));
			}
			ArrayList<ArrayList<Card>> solution = solve(deck);
			System.out.print(solution.size() + " piles remaining: ");
			for(int i = 0; i < solution.size(); ++i) {
				if(i != solution.size()-1)
					System.out.print(solution.get(i).size() + " ");
				else
					System.out.println(solution.get(i).size());
			}
		}
	}
}