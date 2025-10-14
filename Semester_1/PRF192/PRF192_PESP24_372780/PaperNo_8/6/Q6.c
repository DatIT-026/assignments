#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>


int main() {
  system("cls");
  //INPUT - @STUDENT:ADD YOUR CODE FOR INPUT HERE:
  
  char s[100];
  scanf("%[^\n]", &s);
  
    for(int i = 0; i < strlen(s); ++i){
        if(i % 2 == 0) {
        	if(s[i] >= 'a' && s[i] <= 'z' ){
            	s[i] -= 32;
        	}
		}
    }

  // Fixed Do not edit anything here.
  printf("\nOUTPUT:\n");
  //@STUDENT: WRITE YOUR OUTPUT HERE:
  printf("%s", s);
  
  //--FIXED PART - DO NOT EDIT ANY THINGS HERE
  printf("\n");
  system ("pause");
  return(0);
}
