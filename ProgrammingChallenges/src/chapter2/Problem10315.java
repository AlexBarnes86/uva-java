package chapter2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//get methods for hands assume their input is sorted and hand size is 5
//get methods for hands return value of their high card if it matches, 0 otherwise
public class Problem10315 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_INPUT = false;
	
	public static String TIE = "Tie.";
	public static String BLACK_WINS = "Black wins.";
	public static String WHITE_WINS = "White wins.";
	
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
		try {
			if(!DEBUG_INPUT) {
				return new Scanner(System.in);
			}
			else {
				return new Scanner(new File("ProgrammingChallenges/input/chapter2/Problem10315.in"));
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
	
	public static class Card implements Comparable<Card> {
		private char suit;
		private char value;
		
		public char getSuit() {
			return suit;
		}
		
		public int intVal() {
			if(value == 'T') {
				return 10;
			}
			else if(value == 'J') {
				return 11;
			}
			else if(value == 'Q') {
				return 12;
			}
			else if(value == 'K') {
				return 13;
			}
			else if(value == 'A') {
				return 14;
			}
			else {
				return Integer.parseInt(value + "");
			}
		}
		
		public Card(String card) {
			this.value = card.charAt(0);
			this.suit = card.charAt(1);
		}
		
		@Override
		public String toString() {
			return value + "" + suit;
		}
		
		@Override
		public int compareTo(Card other) {
			return intVal() - other.intVal();
		}
	}
	
	//This method assumes the hands are sorted and are of the same type of winning hand aside from flushes
	public static String highCardShowdown(ArrayList<Card> blackHand, ArrayList<Card> whiteHand) {
		for(int i = blackHand.size()-1; i >= 0; --i) {
			int compare = blackHand.get(i).compareTo(whiteHand.get(i));
			if(compare > 0) {
				return BLACK_WINS;
			}
			else if(compare < 0) {
				return WHITE_WINS;
			}
		}
		
		return TIE;
	}
	
	public static int getPair(ArrayList<Card> hand) {
		for(int i = hand.size()-1; i > 0; --i) {
			if(hand.get(i).intVal() == hand.get(i-1).intVal()) {
				return hand.get(i).intVal();
			}
		}
		
		return 0;
	}
	
	public static int getTwoPairs(ArrayList<Card> hand) {
		int firstMatch = 0;
		
		for(int i = hand.size()-1; i >= 2; --i) {
			int card = hand.get(i).intVal();
			int nextCard = hand.get(i-1).intVal();
			firstMatch = card;
			if(card == nextCard) {
				for(int j = i-2; j > 0; --j) {
					card = hand.get(j).intVal();
					nextCard = hand.get(j-1).intVal();
					if(card == nextCard) {
						return firstMatch;
					}
				}
			}
		}
		
		return 0;
	}
	
	public static int getThreeOfKind(ArrayList<Card> hand) {
		int prevCard = hand.get(hand.size()-1).intVal();
		int prevPrevCard = hand.get(hand.size()-2).intVal();
		for(int i = hand.size()-3; i >= 0; --i) {
			int card = hand.get(i).intVal();
			if(card == prevCard && prevCard == prevPrevCard) {
				return card;
			}
			prevPrevCard = prevCard;
			prevCard = card;
		}
		
		return 0;
	}
	
	public static int getStraight(ArrayList<Card> hand) {
		for(int i = 1; i < hand.size(); ++i) {
			if(hand.get(i-1).intVal() != hand.get(i).intVal()-1) {
				return 0;
			}
		}
		
		return hand.get(hand.size()-1).intVal();
	}
	
	public static int getFlush(ArrayList<Card> hand) {
		char suit = hand.get(0).getSuit();
		for(int i = 1; i < hand.size(); ++i) {
			if(hand.get(i).getSuit() != suit) {
				return 0;
			}
		}
		
		return hand.get(hand.size()-1).intVal();
	}
	
	public static int getFullHouse(ArrayList<Card> hand) {
		int first = hand.get(0).intVal();
		int second = hand.get(1).intVal();
		int third = hand.get(2).intVal();
		int fourth = hand.get(3).intVal();
		int fifth = hand.get(4).intVal();
		
		if(fifth == fourth && fourth == third && second == first) {
			return fifth;
		}
		if(fifth == fourth && third == second && second == first) {
			return third;
		}
		
		return 0;
	}
	
	public static int getFourOfKind(ArrayList<Card> hand) {
		int first = hand.get(0).intVal();
		int second = hand.get(1).intVal();
		int third = hand.get(2).intVal();
		int fourth = hand.get(3).intVal();
		int fifth = hand.get(4).intVal();
		
		if(fifth == fourth && fourth == third && third == second) {
			return fifth;
		}
		
		if(fourth == third && third == second && second == first) {
			return fourth;
		}
		
		return 0;
	}
	
	public static int getStraightFlush(ArrayList<Card> hand) {
		int flush = getFlush(hand);
		if(flush != 0) {
			return getStraight(hand);
		}
		
		return 0;
	}
	
	public static String getWinner(int blackValue, int whiteValue) {
		if(blackValue > whiteValue) {
			return BLACK_WINS;
		}
		
		return WHITE_WINS; 
	}
	
	public static String compareHands(ArrayList<Card> blackHand, ArrayList<Card> whiteHand) {
		int blackValue = getStraightFlush(blackHand);
		int whiteValue = getStraightFlush(whiteHand);
		if(blackValue != 0 || whiteValue != 0) {
			if(blackValue == whiteValue) { //They both make the same straight
				return TIE;
			}
			
			return getWinner(blackValue, whiteValue);
		}
		
		blackValue = getFourOfKind(blackHand);
		whiteValue = getFourOfKind(whiteHand);
		if(blackValue != 0 || whiteValue != 0) { //Impossible to tie on 4 of a kind
			return getWinner(blackValue, whiteValue);
		}
		
		blackValue = getFullHouse(blackHand);
		whiteValue = getFullHouse(whiteHand);
		if(blackValue != 0 || whiteValue != 0) {//Impossible to tie on a full house
			return getWinner(blackValue, whiteValue);
		}
		
		blackValue = getFlush(blackHand);
		whiteValue = getFlush(whiteHand);
		if(blackValue != 0 || whiteValue != 0) {
			if(blackValue == whiteValue) { //Can't have pairs in a flush
				return highCardShowdown(blackHand, whiteHand);
			}
			
			return getWinner(blackValue, whiteValue);
		}
		
		blackValue = getStraight(blackHand);
		whiteValue = getStraight(whiteHand);
		if(blackValue != 0 || whiteValue != 0) {
			if(blackValue == whiteValue) { //They both make the same straight
				return TIE;
			}
			
			return getWinner(blackValue, whiteValue);
		}
		
		blackValue = getThreeOfKind(blackHand);
		whiteValue = getThreeOfKind(whiteHand);
		if(blackValue != 0 || whiteValue != 0) {//Impossible to tie on 3 of a kind
			return getWinner(blackValue, whiteValue);
		}
		
		blackValue = getTwoPairs(blackHand);
		whiteValue = getTwoPairs(whiteHand);
		if(blackValue != 0 || whiteValue != 0) {
			if(blackValue == whiteValue) {
				return highCardShowdown(blackHand, whiteHand);
			}
			
			return getWinner(blackValue, whiteValue);
		}
		
		blackValue = getPair(blackHand);
		whiteValue = getPair(whiteHand);
		if(blackValue != 0 || whiteValue != 0) {
			if(blackValue == whiteValue) {
				return highCardShowdown(blackHand, whiteHand);
			}
			return getWinner(blackValue, whiteValue);
		}
		
		return highCardShowdown(blackHand, whiteHand);
	}
	
	public static ArrayList<Card> buildDeck() {
		char [] vals = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
		char [] suits = {'C', 'D', 'H', 'S'};
		ArrayList<Card> deck = new ArrayList<Card>();
		
		for(char val : vals) {
			for(char suit : suits) {
				deck.add(new Card(val + "" + suit));
			}
		}
		
		return deck;
	}
	
	public static void test() {
		ArrayList<Card> deck = buildDeck();
		ArrayList<Card> hand = new ArrayList<Card>();
		
		for(int i = 0; i < 5; ++i) {
			int randCard = (int)(Math.random()*deck.size());
			hand.add(deck.remove(randCard));
		}
		
		Collections.sort(hand);
		int value = getFullHouse(hand); //change this up to test different hand types
		if(value > 0) {
			System.out.println(hand + ", with value: " + value);
		}
	}
	
	public static void main(String args[]) {
//		while(true) {
//			try {
//				test();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		String line = null;
		
		while(sc.hasNext() && (line = sc.nextLine()) != null) {
			line = line.trim();
			
			ArrayList<Card> blackHand = new ArrayList<Card>();
			ArrayList<Card> whiteHand = new ArrayList<Card>();
			
			String [] tokens = line.split("\\s+");
			for(int i = 0; i < 5; ++i) {
				blackHand.add(new Card(tokens[i]));
			}
			Collections.sort(blackHand);
			
			for(int i = 5; i < 10; ++i) {
				whiteHand.add(new Card(tokens[i]));
			}
			Collections.sort(whiteHand);
			
			debug("Black Hand: " + blackHand);
			debug("White Hand: " + whiteHand);
			
			System.out.println(compareHands(blackHand, whiteHand));
			debug("");
		}
	}
}

/*
High Card.
Hands which do not fit any higher category are ranked by the value of
their highest card. If the highest cards have the same value, the hands are ranked
by the next highest, and so on.

Pair.
Two of the five cards in the hand have the same value. Hands which both contain
a pair are ranked by the value of the cards forming the pair. If these values are
the same, the hands are ranked by the values of the cards not forming the pair,
in decreasing order.

Two Pairs.
The hand contains two different pairs. Hands which both contain two pairs
are ranked by the value of their highest pair. Hands with the same highest pair
are ranked by the value of their other pair. If these values are the same the hands
are ranked by the value of the remaining card.

Three of a Kind.
Three of the cards in the hand have the same value. Hands which
both contain three of a kind are ranked by the value of the three cards.

Straight.
Hand contains five cards with consecutive values. Hands which both contain
a straight are ranked by their highest card.

Flush.
Hand contains five cards of the same suit. Hands which are both flushes are
ranked using the rules for High Card.

Full House.
Three cards of the same value, with the remaining two cards forming a
pair. Ranked by the value of the three cards.

Four of a Kind.
Four cards with the same value. Ranked by the value of the four
cards.

Straight Flush.
Five cards of the same suit with consecutive values. Ranked by the
highest card in the hand.
*/