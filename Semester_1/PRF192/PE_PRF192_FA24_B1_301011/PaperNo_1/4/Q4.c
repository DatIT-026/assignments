#include <math.h>
#include <stdio.h>
#include <stdlib.h>
//----------------------------------------
 double Series(int n){
//Begin your statements here
double sums = 0;
for(int i = 1; i <= n; i++) {
  sums += 1 / (double)(pow(i, i));
}

return sums;
 //End your statements	
}
 //===============DO NOT ADD NEW OR CHANGE STATEMENTS IN THE MAIN FUNCTION===========
int main() {
system("cls");
 printf("\nTEST Q5 (2 marks):\n");
 int n;
 printf("Enter a positive number: ");
 scanf("%d",&n); 
 double res = Series(n);  
  //=====DO NOT ADD NEW OR CHANGE STATEMENTS AFTER THIS LINE=====
  //==THE OUTPUT AFTER THIS LINE WILL BE USED TO MARK YOUR PROGRAM== 
  printf("\nOUTPUT:\n");
  printf("%.5f", res);
  printf("\n");
  system ("pause");
  return(0);
}
