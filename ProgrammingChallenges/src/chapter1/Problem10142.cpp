#include <string>
#include <sstream>
#include <iostream>
#include <algorithm> 
#include <functional> 
#include <cctype>
#include <locale>

//#define DEBUG false
#define MAX_BALLOTS 1000
#define MAX_CANDIDATES 80
 
using namespace std;

//void debug(string msg) {
//	if(DEBUG) {
//		cout << "DEBUG: " << msg << endl;
//	}
//}
//
//void debug(string msg, int value) {
//	if(DEBUG) {
//		cout << "DEBUG: " << msg << value << endl;
//	}
//}

int main(int argc, char** args) {
	string line;
	int cases = 0;
	
	getline(cin, line);
	istringstream(line) >> cases;
	
	getline(cin, line); //blank line
	
	for(int i = 0; i < cases; ++i) {
		if(i != 0) {
			cout << endl;
		}
		
//		debug("Input case: ", i+1);
		int nCandidates = 0;
		getline(cin, line);
		istringstream(line) >> nCandidates;
//		debug("Candidates: ", nCandidates);
		
		string* names = new string[nCandidates];
		for(int j = 0; j < nCandidates; ++j) {
			getline(cin, names[j]);
//			debug(names[j]);
		}
		
		bool eliminated[MAX_CANDIDATES];				//Create eliminations and init to false
		for(int i = 0; i < nCandidates; ++i) {
			eliminated[i] = false;
		}
		
		int ballots[MAX_BALLOTS][MAX_CANDIDATES];
		string ballot = "";
		int nBallots = 0;
		while(getline(cin, line)) {
			if(line.empty()) {
				break;
			}
			
//			debug("Ballot: " + line);
			istringstream iss(line);
			for(int j = 0; j < nCandidates; ++j) {
				iss >> ballots[nBallots][j];
			}
			nBallots++;
		}
		
//		for(int i = 0; i < nBallots; ++i) {				//Debug: Display ballots
//			cout << "Ballot: ";
//			for(int j = 0; j < nCandidates; ++j) {
//				cout << ballots[i][j] << " ";
//			}
//			cout << endl;
//		}
		
		for(int i = 0; i < nCandidates; ++i) {
			int votes[MAX_CANDIDATES];					//Create votes and init to 0
			for(int j = 0; j < nCandidates; ++j) {
				votes[j] = 0;
			}
				
			for(int j = 0; j < nBallots; ++j) {			//Count votes
				int top = 0;
				int candidateId = ballots[j][top]-1;
				while(eliminated[candidateId]) {
					top++;
					candidateId = ballots[j][top]-1;
				}
				votes[candidateId]++;
			}
			
//			cout << "Votes: ";							//Debug: display votes
//			for(int j = 0; j < nCandidates; ++j) {
//				cout << votes[j] << " ";
//			}
//			cout << endl;
			
			int max = 0;
			int min = nBallots;
			for(int j = 0; j < nCandidates; ++j) {
				if(eliminated[j]) {
					continue;
				}
				
				int count = votes[j];
				
				if(max < count) {
					max = count;
				}
				if(min > count) {
					min = count;
				}
			}
			
//			debug("Max: ", max);
//			debug("Min: ", min);
			
			if(min == max) {
				for(int j = 0; j < nCandidates; ++j) {
					if(!eliminated[j]) {
						cout << names[j] << endl;
					}
				}
				
				break;
			}
			
			if(max > (nBallots/2)) {
				//return candidate with simple majority
				for(int j = 0; j < nCandidates; ++j) {
					if(votes[j] == max) {
						cout << names[j] << endl;
						break;
					}
				}
				
				break;
			}
			
			for(int j = 0; j < nCandidates; ++j) {
				if(votes[j] == min) {
//					debug("Eliminating: ", j+1);
					eliminated[j] = true;
				}
			}
		}
	}
	
	return 0;
}
