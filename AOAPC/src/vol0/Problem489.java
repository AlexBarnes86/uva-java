//time limit... is it do-able in java? see Problem489.cpp, same algorithm correct solution
package vol0;

import java.util.*;

public class Problem489 {
//	public static HashMap<Character, Integer> count(String str) {
//		HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
//		for(int i = 0; i < str.length(); ++i) {
//			char ch = str.charAt(i);
//			if(chars.containsKey(ch))
//				chars.put(ch, new Integer(chars.get(ch).intValue()+1));
//			else
//				chars.put(ch, new Integer(1));
//		}
//		return chars;
//	}
	
	public static int[] count(String str) {
		int [] ct = new int[26];
		for(int i = 0; i < str.length(); ++i) {
			int ch = str.charAt(i) - 97;
			ct[ch]++;
		}
		return ct;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int game = Integer.parseInt(sc.nextLine()); //eat the \n
			if(game == -1)
				return;
			System.out.println("Round "+game);
			String answer = sc.nextLine();
			String input = sc.nextLine();
			int total_correct = 0;
			int wrong = 0;
			boolean chicken = true;
			boolean [] correct = new boolean[26];
			boolean [] incorrect = new boolean[26];
			//HashMap<Character, Integer> char_ct = count(answer);
			int [] char_ct = count(answer);
			
			for(int i = 0; i < input.length(); ++i) {
				int ch = (int)input.charAt(i)-97;
				if(char_ct[ch] != 0) {//correct guess
					if(!correct[ch]) {
						correct[ch] = true;
						total_correct += char_ct[ch];
					}
					if(total_correct == answer.length()) {
						System.out.println("You win.");
						chicken = false;
						break;
					}
				}
				else { //incorrect guess
					if(!incorrect[ch]) {
						incorrect[ch] = true;
						wrong++;
						if(wrong == 7) {
							System.out.println("You lose.");
							chicken = false;
							break;
						}
					}
				}
			}
			if(chicken)
				System.out.println("You chickened out.");
		}
	}
}

/*
//correct
#include <iostream>
#include <string>

using namespace std;

int* count(string str) {
	int* ct = new int[26];
	for(int i = 0; i < 26; ++i)
		ct[i] = 0;
	for(unsigned int i = 0; i < str.length(); ++i) {
		int ch = str[i] - 97;
		ct[ch]++;
	}
	return ct;
}

int main() {
	while(true) {
		int game;// = Integer.parseInt(sc.nextLine()); //eat the \n		
		string answer, input;
		cin >> game;
		getline(cin, input); // eat newline
		if(game == -1)
			return 0;
		cout << "Round " << game << endl;;

		getline(cin, answer);
		getline(cin, input);
		int total_correct = 0;
		int wrong = 0;
		bool chicken = true;
		bool* correct = new bool[26];
		for(int i = 0; i < 26; ++i)
			correct[i] = 0;
		bool* incorrect = new bool[26];
		for(int i = 0; i < 26; ++i)
			incorrect[i] = 0;
		//HashMap<Character, Integer> char_ct = count(answer);
		int* char_ct = count(answer);
		
		for(int i = 0; i < input.size(); ++i) {
			int ch = (int)input[i]-97;
			if(char_ct[ch] != 0) {//correct guess
				if(!correct[ch]) {
					correct[ch] = true;
					total_correct += char_ct[ch];
				}
				if(total_correct == answer.length()) {
					cout << "You win." << endl;
					chicken = false;
					break;
				}
			}
			else { //incorrect guess
				if(!incorrect[ch]) {
					incorrect[ch] = true;
					wrong++;
					if(wrong == 7) {
						cout << "You lose." << endl;
						chicken = false;
						break;
					}
				}
			}
		}
		if(chicken)
			cout << "You chickened out." << endl;

		delete [] char_ct;	char_ct = 0;
		delete [] correct; correct = 0;
		delete [] incorrect; incorrect = 0;
	}
	return 0;
}
*/