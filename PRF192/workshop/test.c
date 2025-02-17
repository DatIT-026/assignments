#include<stdio.h>

void Multiplication_table () {
    int table[8][9];

    for(int i = 0; i < 8; ++i) {
        // (i + 2) means multipler (from 2 to 9)
        // (j + 1) is the multiplicand (from 1 to 9)
        // The result is the multiplication of (i+2) and (j+1)
        for(int j = 0; j < 9; ++j) {
            table[i][j] = (i + 2) * (j + 1);
        }
    }

    for(int i = 0; i < 8; ++i) {
        // \t means tab = 4 spaces
        for(int j = 0; j < 9; ++j) printf("%2d x %d = %2d\t", i+2, j+1, table[i][j]);
                printf("\n");
        }
}

int main() {
    Multiplication_table();
    return 0;
}