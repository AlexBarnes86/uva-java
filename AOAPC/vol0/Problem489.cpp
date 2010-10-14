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
