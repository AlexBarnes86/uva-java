//doable but frustrating
#include <iostream>
#include <fstream>
#include <list>
#include <vector>
#include <string>
#include <sstream>

using namespace std;

struct Block
{
	Block *next;
	Block *prev;
	int id;

	Block(int n) :next(NULL), prev(NULL), id(n) {}
};

void print_table(const vector<Block*> &table)
{
	for(unsigned int i = 0; i < table.size(); ++i)
	{
		Block *temp = table[i];
		cout << i << ":";
		while(temp != NULL)
		{
			cout << " " << temp->id;
			temp = temp->next;
		}
		cout << endl;
	}
}

void reset_after(const vector<Block*> &blocks, vector<Block*> &table, int a);
bool after(const Block* block, int id);
Block* stack_end(Block* block);
void move_a_onto_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b);
void move_a_over_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b);
void pile_a_onto_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b);
void pile_a_over_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b);

int main(int argc, const char *argv[])
{
	int n;
	cin >> n;
	string line;
	getline(cin, line); //eat the newline

	vector<Block*> blocks; //read-only
	vector<Block*> table;
	for(int i = 0; i < n; ++i)
	{
		Block *temp = new Block(i);
		blocks.push_back(temp);
		table.push_back(temp);
	}

	while(true)
	{
		getline(cin, line);
		if(line == "quit")
			break;

		istringstream iss(line);
		string action, method;
		int a, b;
		iss >> action >> a >> method >> b;
		if(action == "move")
		{
			if(method == "onto")
				move_a_onto_b(blocks, table, a, b);
			else
				move_a_over_b(blocks, table, a, b);
		}
		else
		{
			if(method == "onto")
				pile_a_onto_b(blocks, table, a, b);
			else
				pile_a_over_b(blocks, table, a, b);
		}
	}
	
	print_table(table);

	return 0;
}

void reset_after(const vector<Block*> &blocks, vector<Block*> &table, int a)
{
	//cout << "reset after" << a << endl;
	Block* temp = blocks[a]->next;
	while(NULL != temp)
	{
		if(NULL != temp->prev)
			temp->prev->next = NULL;
		temp->prev = NULL;
		table[temp->id] = temp;
		temp = temp->next;
	}
}

void move_a_onto_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b)
{
	//cout << ":move " << a << " onto " << b << endl;
	reset_after(blocks, table, a);
	reset_after(blocks, table, b);
	blocks[b]->next = blocks[a];
	blocks[a]->prev = blocks[b];
	table[a] = NULL;
}

void move_a_over_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b)
{
	//cout << ":move " << a << " over " << b << endl;
	reset_after(blocks, table, a);
	Block* temp = stack_end(blocks[b]);
	temp->next = blocks[a];
	blocks[a]->prev = temp;

	table[a] = NULL;
}

void pile_a_onto_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b)
{
	//cout << ":pile " << a << " onto " << b << endl;
	if(after(blocks[a], b))
	{
		//cout << ":nothing happens" << endl;
		return;
	}
	
	//vague do we reset all or just the ones between b and a?
	//current resets all
	reset_after(blocks, table, b);

	if(NULL != blocks[a]->prev)
		blocks[a]->prev->next = NULL;
	blocks[b]->next = blocks[a];
	blocks[a]->prev = blocks[b];
	table[a] = NULL;	
}

void pile_a_over_b(const vector<Block*> &blocks, vector<Block*> &table, int a, int b)
{
	//cout << ":pile " << a << " over " << b << endl;
	if(after(blocks[a], b) || after(blocks[b], a))
	{
		//cout << ":nothing happens" << endl;
		return;
	}

	Block* temp = stack_end(blocks[b]);
	temp->next = blocks[a];
	if(NULL != blocks[a]->prev)
		blocks[a]->prev->next = NULL;

	blocks[a]->prev = temp;
	table[a] = NULL;
}

Block* stack_end(Block* block)
{
	Block* temp = block;
	while(temp->next != NULL)
		temp = temp->next;
	return temp;
}

bool after(const Block* block, int id)
{
	Block* temp = block->next;
	while(temp != NULL)
	{
		if(temp->id == id)
			return true;
		temp = temp->next;
	}
	return false;
}
