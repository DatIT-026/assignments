#include<stdio.h>
#include<stdlib.h>

#define MAX_SIZE 100

int input(int n, int *arr) {
    FILE *fp = fopen("input.txt", "r");
    if(!fp) return 1;

    fscanf(fp, "%d", &n);
    for(int i = 0; i < n; ++i){
        fscanf(fp, "%d", &arr[i]);
    }
    fclose(fp);
    return 0;
}

int bubbleSort(int n, int *arr) {
    for(int i = 0; i < n - 1; ++i) {
        for(int j = 0; j < n - i - 1; ++j) {
            if(arr[i] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    return 0;
}

int output(int n, int *arr) {
    FILE *fp = fopen("output.txt", "w");
    if(!fp) return 1;
    for(int i = 0; i < n; ++i) {
        fprintf(fp, "%d", arr[i]);
    }
    fclose(fp);
    return 0;
}

int main() {
    int n;
    int arr[MAX_SIZE];

    if(input(&n, arr)) return 1;
    bubbleSort(n, arr);
    if(output(n, arr)) return 1;
    return 0;
}