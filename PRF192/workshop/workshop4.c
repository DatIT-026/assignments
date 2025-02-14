#include <stdio.h>
#include <stdlib.h>

// Constants
#define MAX_DAY 100

// Struct definition
typedef struct {
    int sales[MAX_DAY];
    int days[MAX_DAY];
    int count;
} SalesData;

// Func to input sales data
SalesData inputSales() {
    SalesData data;
    int n;

    printf("Enter the number of days (1-100): ");
    while(1) {
        scanf("%d", &n);
        if(1 <= n && n <= 100) {
            break;
        }
        printf("Invalid number of days! Please try again: ");
        continue;
    }

    data.count = n;
    printf("Enter sales data for %d day(s):\n", n);

    for(int i = 0; i < data.count; ++i) {
        data.days[i] = i + 1;
        printf("Day %d (Sales Value): ", i+1);
        scanf("%d", &data.sales[i]);
    }

    return data; // return all data, including data members
}

// Func to display sales data
void displaySales(SalesData data) {
    printf("--- Sales data ---");
    for(int i = 0; i < data.count; ++i) {
        printf("\nDay %d: %d", i+1, data.sales[i]);
    }
    printf("\n");
}

// Func to sort sales data in ascending order
SalesData sortedAscending(SalesData data) {
    for(int i = 0; i < data.count-1; ++i) {
        for(int j = 0; j < (data.count-i)-1; ++j) {
            if (data.sales[j] > data.sales[j+1]) {
                int temp = data.sales[j];
                data.sales[j] = data.sales[j+1];
                data.sales[j+1] = temp;
            }
        }
    }
    return data;
}

// descending order
SalesData sortedDescending(SalesData data) {
    for(int i = 0; i < data.count-1; ++i) {
        for(int j = 0; j < (data.count-i)-1; ++j) {
            if(data.sales[j] < data.sales[j+1]) {
                int temp = data.sales[j];
                data.sales[j] = data.sales[j+1];
                data.sales[j+1] = temp;
            }
        }
    }
    return data;
}

// Func to search for valyes greater than the target
void searchGreaterThan(SalesData data, int target) {
    printf("Sales values greater than %d:\n", target);
    for(int i = 0; i < data.count; ++i) {
        if(target < data.sales[i]) {
            printf("Day %d: %d\n", i+1, data.sales[i]);
        }
    }
}

int main() {
    SalesData data;
    int choice, target;

    do {
        printf("\n--- Enhanced Sales Data Management Menu ---\n");
        printf("1. Input Sales Data\n");
        printf("2. Display Sales Data\n");
        printf("3. Sort Sales Data in Ascending Order\n");
        printf("4. Sort Sales Data in Descending Order\n");
        printf("5. Search for Sales Greater than a Target\n");
        printf("6. Exit\n");
        printf("> Enter your choice: ");
        scanf("%d", &choice);
    
        switch(choice) {
            case 1:
                system("cls");
                data = inputSales();
                break;
            case 2:
                system("cls");
                displaySales(data);
                break;
            case 3:
                system("cls");
                data = sortedAscending(data);
                printf("Sales data sorted in ascending order:\n");
                displaySales(data);
                break;
            case 4:
                system("cls");
                data = sortedDescending(data);
                displaySales(data);
                break;
            case 5:
                system("cls");
                printf("Enter the target sales value: ");
                scanf("%d", &target);
                searchGreaterThan(data, target);
                break;
            case 6:
                printf("Exiting program...\n");
                break;
            default:
                system("cls");
                printf("Invalid Input! Try again.\n");
        }
    } while (choice != 6);
    
    return 0;
}