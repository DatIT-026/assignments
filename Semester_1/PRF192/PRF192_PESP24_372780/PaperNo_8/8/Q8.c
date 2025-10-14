#include <stdio.h>

int main() {
  system("cls");
  int n;
  scanf ("%d", &n);

  int arr[n];
  for(int i = 0; i < n; ++i) {
    scanf("%d", &arr[i]);
  }
  
  for (int i = 0; i < n; ++i) {
    if (arr[i] % 2 != 0) {

      int count = 0;

      for (int j = i; j < n; ++j) {
        if (arr[i] == arr[j]) count++;
      }

      if (count > 1) {
        int printed = 0;
        for (int k = 0; k < i; ++k) {
          if (arr[k] == arr[i]) {
            printed = 1;
            break;
          }
        }
        if (!printed) printf("%d\n", arr[i]);
      }
    }
  }

  
  // Fixed Do not edit anything here.
  printf("\nOUTPUT:\n");
  //@STUDENT: WRITE YOUR OUTPUT HERE:

  
  //--FIXED PART - DO NOT EDIT ANY THINGS HERE
  printf("\n");
  system ("pause");
  return(0);
}
