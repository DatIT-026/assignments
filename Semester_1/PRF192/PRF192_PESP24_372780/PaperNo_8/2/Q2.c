#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>


int main() {
  system("cls");
  //INPUT - @STUDENT:ADD YOUR CODE FOR INPUT HERE:
  int arr[4];
  int count = 0;
  
  for(int i = 0; i < 5; ++i) {
  	scanf("%d", &arr[i]);
  }
  
  // Fixed Do not edit anything here.
  printf("\nOUTPUT:\n");
  //@STUDENT: WRITE YOUR OUTPUT HERE:
  
  for(int i = 0; i < 5; ++i) {
  	if (arr[i] % 3 != 0 && arr[i] % 5 == 0) {
  		count++;
	  }
  }
  printf("%d", count);

  
  //--FIXED PART - DO NOT EDIT ANY THINGS HERE
  printf("\n");
  system ("pause");
  return(0);
}
