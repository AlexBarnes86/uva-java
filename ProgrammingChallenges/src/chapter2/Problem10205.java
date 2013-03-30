package chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem10205 {
	public static final Scanner sc = new Scanner(System.in);
	
	public static enum Suit {
		CLUBS,
		DIAMONDS,
		HEARTS,
		SPADES
	}
	
	public static enum Value {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
	}
	
	public static class Card {
		public Suit suit;
		public Value value;
		
		public Card(Suit suit, Value value) {
			this.suit = suit;
			this.value = value;
		}
		
		@Override
		public String toString() {
			String ret = "";
			
			if(value.compareTo(Value.JACK) < 0) {
				ret += (value.ordinal() + 2);
			}
			else if(value == Value.JACK) {
				ret += "Jack";
			}
			else if(value == Value.QUEEN) {
				ret += "Queen";
			}
			else if(value == Value.KING) {
				ret += "King";
			}
			else if(value == Value.ACE) {
				ret += "Ace";
			}
			
			ret += " of ";
			if(suit == Suit.CLUBS) {
				ret += "Clubs";
			}
			if(suit == Suit.DIAMONDS) {
				ret += "Diamonds";
			}
			if(suit == Suit.HEARTS) {
				ret += "Hearts";
			}
			if(suit == Suit.SPADES) {
				ret += "Spades";
			}
			
			return ret;
		}
	}
	
	public static class Deck {
		public List<Card> cards = new ArrayList<Card>();
		
		public Deck() {
			for(Suit suit : Suit.values()) {
				for(Value value : Value.values()) {
					cards.add(new Card(suit, value));
				}
			}
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < cards.size(); ++i) {
				if(i != 0) {
					sb.append("\n");
				}
				sb.append(cards.get(i).toString());
			}
			return sb.toString();
		}
		
		public void shuffle(List<Integer> ordering) {
			List<Card> shuffled = new ArrayList<Card>(52);
			shuffled.addAll(cards); // dummy data to enforce size 52
			for(int i = 0; i < ordering.size(); ++i) {
				shuffled.set(i, cards.get(ordering.get(i)-1));
			}
			cards = shuffled;
		}
	}
	
	private static int[] getInput() {
		String line = null;
		StringBuffer sb = new StringBuffer();
		while(sc.hasNext() && (line = sc.nextLine().trim()) != null && !line.isEmpty()) {
			sb.append(line + " ");
		}
		String [] tokens = sb.toString().trim().split("\\s+");
		int [] input = new int[tokens.length];
		for(int i = 0; i < input.length; ++i) {
			input[i] = Integer.parseInt(tokens[i]);
		}
		
		return input;
	}
	
	public static void main(String[] args) {
		int testCases = Integer.parseInt(sc.nextLine().trim());
		sc.nextLine();
		
		for(int test = 0; test < testCases; ++test) {
			int inputPtr = 0;
			int[] input = getInput();
			
			int nShuffles = input[inputPtr++];
			List<List<Integer>> shuffles = new ArrayList<List<Integer>>();
			for(int idx = 0; idx < nShuffles; ++idx) {
				List<Integer> shuffle = new ArrayList<Integer>();
				for(int j = 0; j < 52; ++j) {
					shuffle.add(input[inputPtr++]);
				}
				shuffles.add(shuffle);
			}
			
			Deck deck = new Deck();
			while(inputPtr < input.length) {
				deck.shuffle(shuffles.get(input[inputPtr++]-1));
			}
			
			if(test != 0) {
				System.out.println();
			}
			System.out.println(deck);
		}
	}
}