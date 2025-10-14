#include <stdio.h>
#include <stdlib.h>

long Income_tax(long ti){	    
  // Write your statements here
  double inc = 0, tax = inc;
  if (ti <= 5000000) inc = ti * 5 / 100;
    else if (ti >= 5000001 || ti <= 10000000) inc = ti * 10 / 100;
    else if (ti >= 10000001 || ti <= 18000000) inc = ti * 15 / 100;
    else inc = ti * 20 / 100;
  return tax; 
  // End your codes  
}
//====DO NOT ADD NEW OR CHANGE STATEMENTS IN THE MAIN FUNCTION
int main()
{
system("cls");
printf("\nTEST Q2 (2 marks):\n");
long pa=9000000, pd=3600000, income, tf, ti,taxable;
int depend;
printf("Your income of this year: ");
scanf("%ld", &income);
printf("Number of dependent: ");
scanf("%d", &depend);
tf=12*(pa+depend*pd);
ti=income-tf;
if (ti<=0) {taxable=0; ti=0; }
else 
 taxable = Income_tax(ti);
  //====DO NOT ADD NEW OR CHANGE STATEMENTS AFTER THIS LINE====
  //==THE OUTPUT AFTER THIS LINE WILL BE USED TO MARK YOUR PROGRAM==
printf("\nOUTPUT:\n");
printf("%ld\n", tf);
printf("%ld\n", ti);
printf("%ld\n", taxable);
printf("\n");
system ("pause");
return(0);
}
