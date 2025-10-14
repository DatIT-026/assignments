#include <stdio.h>
#include <math.h>
#include <stdlib.h>
double triangle(double a,  double b,  double c){
	double p, s;
	//Begin your statements here   
  p = (double)(a + b + c) / 2;
  s = sqrt(p * (p - a) * (p - b) * (p - c));
	//End your statements 
 return s;
}
//========DO NOT ADD NEW OR CHANGE THE STATEMENTS IN THE MAIN FUNCTION========
int main()
{ 
  system("cls");
  printf("\nTEST Q1 (1.5 marks):\n");
  double a, b, c;
  double  area;
  printf("Enter a = "); scanf("%lf",&a);
  printf("Enter b = "); scanf("%lf",&b);
  printf("Enter c = "); scanf("%lf",&c);   
  area = triangle(a,b,c); 
  printf("\nOUTPUT:\n");
    if (area > 0) {
        printf("%.2lf\n", area);
    } else {
        printf("Entered values of sides do not form a valid triangle.\n");
    }
  printf("\n");
  system ("pause");
  return 0; 
}
//========//==================================================================
