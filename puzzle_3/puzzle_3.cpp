#include <map>
#include <iostream>
#include <fstream>
#include <string>
#include <cassert>
#include <unordered_map>
using namespace std;

int HousesVisited (string input) {
    std::unordered_map<std::string, int> m;
    int x = 0, y = 0;
    string key;

    m['0,0'] = 1;

    // for every char in input
    for (char & c : input) {
        if (c == '^')
            y += 1;
        else if (c == 'v')
            y -= 1;
        else if (c == '>')
            x += 1;
        else if (c == '<')
            x -= 1;

        key = to_string(x) + "," + to_string(y);
        if (m[key])
            m[key] += 1;
        else
            m[key] = 1;
    }

    return m.size();
}

int main(int argc, char **argv) {
    ifstream myfile;
    string line;
    
    myfile.open ("input.txt", ios::in);

    if (myfile.is_open()) {
        while (getline(myfile, line, '\n')) {
            cout << HousesVisited(line) << '\n';
        }
    }

    myfile.close();
    return 0;

}
