#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>

int main() {
  int n;
  printf("n = ");
  scanf("%d", &n);
  
  printf("Array = ");
  int arr[n + 1]; // More space for the array
  for (int i = 0; i < n; ++i) {
  	scanf("%d", &arr[i]);
  }
  
  int v, p;
  printf("v and p = ");
  scanf("%d %d", &v, &p);
  
  // Dich phan tu de tao cho trong cho phan tu moi
  for(int i = n; i > p; i--) arr[i] = arr[i - 1];
  
  arr[p] = v; 
  n++; // Tang kich thuoc arr
  
  printf("\n");
    
  printf("Array after insertion:\n");
  for(int i = 0; i < n; ++i) printf("%d ", arr[i]);
  
  printf("\n\n");
  
  // Bonus, delete an element in the array
  int d;
  printf("d = ");
  scanf("%d", &d);
  // Xoa phan tu bang cach dich sang trai
  for(int i = d; i < n - 1; i++) arr[i] = arr[i + 1];
  n--; // Giam kich thuoc mang
  
  printf("\n");
  
  printf("Array after deletion:\n");
  for (int i = 0; i < n; i++) printf("%d ", arr[i]);
    
  return 0;
}
