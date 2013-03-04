#include <iostream>
#include <string>
#include <vector>

using namespace std;

void hartal();

int main(int argc, const char *argv[])
{
	string line;
	int T = 0;
	cin >> T;
	getline(cin, line); //eat newline
	for(int test = 0; test < T; ++test)
	{
		hartal();
	}
	return 0;
}

void hartal()
{
	string line;
	int N;
	cin >> N;
	getline(cin, line);
	vector<boolean> hartals;

}
