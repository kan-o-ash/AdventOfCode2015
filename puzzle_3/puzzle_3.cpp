#include <map>
#include <iostream>
#include <fstream>
#include <string>
#include <cassert>
#include <unordered_map>
using namespace std;

int VisitHouse(char c, unordered_map<string, int> &m, int *x, int *y) {
    string key;

    if (c == '^')
        *y += 1;
    else if (c == 'v')
        *y -= 1;
    else if (c == '>')
        *x += 1;
    else if (c == '<')
        *x -= 1;

    key = to_string(*x) + "," + to_string(*y);
    if (m[key])
        m[key] += 1;
    else
        m[key] = 1;
}

int HousesVisited (string input) {
    unordered_map<string, int> m;
    int x = 0, y = 0;

    m["0,0"] = 1;

    // for every char in input
    for (char & c : input) {
        // cout << "test";
        VisitHouse(c, m, &x, &y);
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
