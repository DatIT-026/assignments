#include <stdio.h>

int binaryToDecimal (int n) {
  int num = n;
  int base = 1, temp = num;
  int dec_value = 0;

  while (temp) {
    int last_digit = temp % 10;
    temp /= 10;
    dec_value += last_digit * base;
    base *= 2; // 2^i
  }

  return dec_value;
}

int main() {
  system("cls");
  int num = 110110111;
  printf("%d", binaryToDecimal(num));
  return(0);
}
