#include <iostream>
#include <fstream>
using namespace std;

int main(int argc, char *argv[]) {

	char c;

	ifstream inFile("in.txt", ios::in);

	if (!inFile) {
		cerr << "in.txt could not be opened" << endl;
		exit(1);
	}

	ofstream outFile("out.txt", ios::out);

	if (!outFile) {
		cerr << "out.txt could not be opened" << endl;
		exit(1);
	}

	while (inFile.read(&c, sizeof(char)))
		outFile.write(&c, sizeof(char));

	inFile.close();
	outFile.close();

	return 0;
}
