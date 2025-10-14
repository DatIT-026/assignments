// Ve hinh binh hanh rong nguoc
#include <stdio.h>
int main(){
    int rows, cols;
    scanf("%d %d", &rows, &cols);
    for (int i = 0; i < rows; i++){
        for (int k = 0; k < rows - i - 1; k++) printf("  ");
        for (int j = 0; j < cols; j++){
            if(i == 0 || i == rows - 1 || j == 0 || j == cols - 1) printf("* ");
            else printf("  ");
        }
        printf("\n");
    }
    return 0;
}
