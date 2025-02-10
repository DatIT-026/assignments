#include <stdio.h>

int main() {
  int n;

  printf("Enter an integer: ");
  scanf("%i", &n);

  if (n == 0) {
    printf("0 is neither odd nor even");
  } else {
    if (n % 2 == 0) {
      printf("%i is even", n);
    } else {
      printf("%i is odd", n);
    }
  }

  return 0;
}
