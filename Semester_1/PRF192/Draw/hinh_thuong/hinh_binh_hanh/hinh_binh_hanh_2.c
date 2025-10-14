// Hinh binh hanh nguoc
#include <stdio.h>
int main(){
    int rows, cols;
    scanf("%d %d", &rows, &cols);
    
    for (int i = 0; i < rows; i++){
        for (int j = 0; j < rows - i - 1; j++) printf("  ");
        for (int j = 0; j < cols; j++) printf("* ");
        printf("\n");
    }
    return 0;
}
