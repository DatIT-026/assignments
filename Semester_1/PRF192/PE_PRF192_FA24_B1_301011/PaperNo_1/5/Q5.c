#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

//----------------------------------------

int isSubstring(char* s1, char* s2) {
//Begin your statements here
  char *pos = strstr(s2, s1);
  if (!pos) return -1;
  return (int)(pos - s2) + 1;
 //End your statements	   
}
 //===============DO NOT ADD NEW OR CHANGE STATEMENTS IN THE MAIN FUNCTION===========
int main() {
 system("cls");
 printf("\nTEST Q4 (2 marks):\n");
 char s1[20];
 char s2[50];
 printf("Please enter a string s1: ");
 scanf("%20[^\n]",s1); 
 fflush(stdin);
 printf("Please enter a string s2: ");
 scanf("%50[^\n]",s2); 
 int res = isSubstring(s1, s2);	    
  //=====DO NOT ADD NEW OR CHANGE STATEMENTS AFTER THIS LINE=====
  //==THE OUTPUT AFTER THIS LINE WILL BE USED TO MARK YOUR PROGRAM== 
  printf("\nOUTPUT:\n");
  if (res == -1)
        printf("Not present\n");
    else
        printf("%d\n", res);
  printf("\n");
  system ("pause");
  return(0);
}
