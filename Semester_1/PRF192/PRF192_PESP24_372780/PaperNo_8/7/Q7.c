#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>

void BubbleSort(int n, int arr[]){
    for(int i = 0; i < n; ++i){
        for(int j = i + 1; j < n; ++j){
            if(arr[i] < arr[j]){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}

int main() {
  system("cls");
  //INPUT - @STUDENT:ADD YOUR CODE FOR INPUT HERE:
  int n;
  printf("n = ");
  scanf("%d", &n);
  
  if(n < 1 || n >= 10) {
  	printf("Out of range.");
  	return -1;
  }
  
  int arr[n];
  printf("Array: ");
  for (int i = 0; i < n; ++i) {
  	scanf("%d", &arr[i]);
  }
  
  // Fixed Do not edit anything here.
  printf("\nOUTPUT:\n");
  //@STUDENT: WRITE YOUR OUTPUT HERE:
  for(int i = 0; i < n; ++i) {
  	BubbleSort(n, arr);
  	if(arr[i] % 2 != 0) {
  	    printf("%.f ", pow(arr[i], 2));
	  }
  }
  
  //--FIXED PART - DO NOT EDIT ANY THINGS HERE
  printf("\n");
  system ("pause");
  return(0);
}
