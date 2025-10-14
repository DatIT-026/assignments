// This is an alternative version of workshop 5
#include<stdio.h>
#include<stdlib.h>

#define MAX_SIZE 100

int readFileToArray(const char *read_filename, int arr[], int *size) {
    FILE *fr = fopen(read_filename, "r");
    if(fr == NULL) {
        fprintf("Error: cannot open file %s\n", read_filename);
    }

    int num, result, i = 0;
    while(i < MAX_SIZE) {
        result = fscanf(fr, "%d", &num);
        if(result != 1) break;
        arr[i++] = num;
    }

    *size = i;

    fclose(fr);
    return 0;
}

void displayArray(int arr[], int size) {
    if(size <= 0) {
        printf("There's no data in the array.");
        return;
    }

    printf("Data in the array:\n[ ");
    for(int i = 0; i < size-1; ++i) {
        printf("%d, ", arr[i]);
    }
    printf("%d ]", arr[size-1]);
}

int calculateStatistics(int arr[], int size, int *sum, int *max, int *min, float *avg) {
    *sum = 0;
    *max = *min = arr[0];

    if(size == 0) {
        *sum = *max = min = 0;
        *avg = 0.0;
        return;
    }

    for(int i = 0; i < size; ++i) {
        *sum += arr[i];
        if(arr[i] > *max) *max = arr[i];
        if(arr[i] < *min) *min = arr[i];    
    }

    *avg = (float)(*sum) / (size);
}

int main() {
    const char *read_filename = "data.txt";
    const char *write_filename = "result_1.txt";

    int arr[MAX_SIZE], size = 0;
    int sum, max, min;
    float avg;

    if(readFileToArray(read_filename, arr, &size) != 0) {
        return 1;
    }
    
    displayArray(arr, size);
    calculateStatistics(arr, size, &sum, &max, &min, &avg);
    
    // Write data into "result.txt"
    FILE *fw = fopen(write_filename, "w");
    if(fw == NULL) {
        printf("Error: Cannot open file %s\n.", write_filename);
        return 1;
    }

    // no pointer, no addressing referencing
    fprintf(fw, "Sum: %d", sum);
    fprintf(fw, "\nAverage: %.2f", avg);
    fprintf(fw, "\nMax: %d", max);
    fprintf(fw, "\nMin: %d", min);

    fclose(fw);
    
    // Display data in console
    printf("\n--- Statistics ---\n");
    printf("Sum: %d", sum);
    printf("\nAverage: %.2f", avg);
    printf("\nMax: %d", max);
    printf("\nMin: %d", min);

    return 0;
}