#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>
void filter_string(char *s) {
    int j = 0;
    for (int i = 0; s[i] != '\0'; i++) {
        if (isalpha(s[i]) || s[i] == ' ') {
            s[j++] = s[i];
        }
    }
    s[j] = '\0';
}

int main() {
  system("cls");
  char a[1000];
  fgets(a,1000,stdin);
  filter_string(a);
  printf("\nOUTPUT:\n%s", a);
}