#include<stdio.h>
#include<stdlib.h>

#define MAX_SIZE 100

int readFileToArray(const char *read_filename, int arr[], int *size) {
    FILE *fp = fopen(read_filename, "r"); // Open a file for reading

    // Checking if cannot open file
    if(fp == NULL) {
        fprintf("Error: cannot open file %s\n", read_filename);
        return 1;
    }

    int num, result, i = 0;

    //Loop to continously attempt reading.
    while(i < MAX_SIZE) {
        // Try to read an integer from the file.
        result = fscanf(fp, "%d", &num);
        // Break if did not successfully read an integer.
        if(result != 1) break;
        // Store the integer in the array.
        arr[i++] = num;
    }

    *size = i; // Store number of elements read
    fclose(fp);
    return 0;
}

int writeTheResultInAFile(const char *result_filename, int *sum, int *max, int *min, float *avg) {
    FILE *fp = fopen(result_filename, "w");
    if(fp == NULL) {
        perror("Error: Cannot open file.");
        return 1;
    }
    
    fprintf(fp, "Sum: %d", *sum);
    fprintf(fp, "\nAverage: %.2f", *avg);
    fprintf(fp, "\nMax: %d", *max);
    fprintf(fp, "\nMin: %d", *min);

    fclose(fp);
    return 0;
}

void displayArray(int arr[], int size) {
    if (size <= 0) {
        printf("There is no data in the array.\n");
        return;
    }

    printf("Data in the array: [ ");
    for(int i = 0; i < size-1; ++i) {
        printf("%d, ", arr[i]);
    }
    printf("%d ]\n", arr[size-1]);
}

void calculateStatistics(int arr[], int size, int *sum, int *max, int *min, float *avg){
    *sum = 0;
    *max = *min = arr[0];

    // Handle empty array
    if(size == 0) {
        *sum = *max = *min = 0;
        *avg = 0.0;
        return;
    }

    for(int i = 0; i < size; ++i) {
        *sum += arr[i];
        if(arr[i] > *max) *max = arr[i];
        if(arr[i] < *min) *min = arr[i];
    }
    *avg = (float)(*sum) / size;
}

int main() {
    const char *read_filename = "data.txt";
    const char *result_filename = "result.txt";

    int arr[MAX_SIZE], size = 0;
    int sum, max, min;
    float avg;

    if(readFileToArray(read_filename, arr, &size) != 0) {
        return 1; // Error reading file
    }

    // Export the result
    calculateStatistics(arr, size, &sum, &max, &min, &avg);
    writeTheResultInAFile(result_filename, &sum, &max, &min, &avg);

    // Display data
    displayArray(arr, size);

    printf("\n--- Statistics ---\n");
    printf("Sum: %d", sum);
    printf("\nAverage: %.2f", avg);
    printf("\nMax: %d", max);
    printf("\nMin: %d", min);    

    return 0;
}