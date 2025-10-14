#include<stdio.h>
#define MAX_SIZE 100

void inputArray(int arr[], int *size) {
    int n;
    printf("Enter the number of elements (max %d): ", MAX_SIZE);
    
    while(1) {
        scanf("%d", &n);
        if(1 <= n && n <= MAX_SIZE) {
            break;
        } else {
            printf("Invalid size! ");
            printf("Please enter a number between 1 and 100: ");
            continue;
        }
    }

    *size = n;
    printf("Enter %d number(s):\n", n);
    for(int i = 0; i < n; ++i) {
        printf("Number %d: ", i+1);
        scanf("%d", &arr[i]);
    }
}

void displayArray(int arr[], int size) {
    printf("Array elements: [ ");
    for(int i = 0; i < size-1; ++i) {
        printf("%d, ", arr[i]);
    }
    printf("%d ]\n", arr[size-1]);
}

int searchValue(int arr[], int size, int target) {
    for(int i = 0; i < size; ++i) {
        if(target == arr[i]) {
            return i;
        }
    }
    return -1;
}

void calculateStatistics(int arr[], int size) {
    int sum = 0, count = size, maxValue = arr[0], minValue = arr[0];
    
    for(int i = 0; i < size; ++i) {
        // Calculate the sum of all elements
        sum += arr[i];
        
        // Calculate the max and min values from the array
        if(maxValue < arr[i]) maxValue = arr[i];
        if(minValue > arr[i]) minValue = arr[i];
    }
        // Calculate the average of the elements
        float avg = (float)(sum) / count;

        // Show data
        printf("--- Calculations ---\n");
        printf(" Sum: %d\n", sum);
        printf(" Average: %.2f\n", avg);
        printf(" Maximum: %d\n", maxValue);
        printf(" Minimum: %d\n", minValue);
}

int main() {
    int arr[MAX_SIZE], size = 0, target, index, choice;

    do {
        printf("\n--- Menu ---\n");
        printf("1. Input Data\n");
        printf("2. Display array\n");
        printf("3. Search for a number\n");
        printf("4. Calculate statistics (Sum, Average, Max, Min)\n");
        printf("5. Exit\n");

        printf("Enter your choice: ");
        while(1) {
            scanf("%d", &choice);
            if(1 <= choice && choice <= 5) {
                break;
            } else {
                printf("Invalid Input! Please try again.\n");
                printf("Enter your choice: ");
                continue;
            }
        }

        switch(choice) {
            case 1: //  Input Data
            printf("--------\n");
                inputArray(arr, &size);
                break;

            case 2: // Display Array
            printf("--------\n");
                if(size > 0) {
                    displayArray(arr, size);
                } else {
                    printf("No data to display. Please input data first.\n");
                }
                break;

            case 3: // Search for a number
            printf("--------\n");
                if (size > 0) {
                    printf("Enter the number you want to search for: ");
                    scanf("%d", &target);
                    index = searchValue(arr, size, target);
                    if(index == -1) {
                        printf("The number %d is not found in the array.\n", target);
                    } else {
                        printf("The number %d is found at index %d.\n", target, index);
                    }
                } else {
                    printf("No data available to search. Please input data first.\n");
                }
                break;

            case 4: // Calculate statistics
                if (size > 0) {
                    calculateStatistics(arr, size);
                } else {
                    printf("No data available to calculate statistics. Please input data first.\n");
                }
                break;   

            case 5: // Exit
            printf("--------\n");
                printf("Exiting the program.\n");
                break;

            default:
                printf("--------\n");
                printf("Invalid choice. Please try again.\n");
        }
    } while (choice != 5);

    return 0;
}