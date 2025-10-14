#include<stdio.h>

#define MAX_SIZE 100

typedef struct {
    int accountNumber;
    char accountType[20], accountHolderName[40];
    double balance;
} customer_account;

int main() {

    customer_account customers[MAX_SIZE];  

    int num_customers;

    printf("Enter the number of customers: ");
    scanf("%d", &num_customers);

    if(num_customers > MAX_SIZE) {
        printf("Number exceeds limit.\n");
        return 1;
    }

    for(int i = 0; i < num_customers; ++i) {
        customers[i].accountNumber = i + 1;
        printf("Customer %d:\n", i + 1);
        printf("Enter Account Type: ");
        scanf("%s", customers[i].accountType);
        printf("Enter Account Holder Name: ");
        scanf("%s", customers[i].accountHolderName);
        printf("Enter Balance: ");
        scanf("%lf", &customers[i].balance);
    }

    printf("\n==== All Customer Accounts ====\n");
    for(int i = 0; i < num_customers; ++i) {
        printf("Account Number: %d\n", customers[i].accountNumber);
        printf("Account Type: %s\n", customers[i].accountType);
        printf("Account Holder Name: %s\n", customers[i].accountHolderName);
        printf("Balance: %.2lf\n\n", customers[i].balance);
    }

    printf("\n==== Customer with balance is greater than $1000 ====\n");
    for(int i = 0; i < num_customers; ++i) {
        if (customers[i].balance > 1000) {
            printf("Account Number: %d\n", customers[i].accountNumber);
            printf("Account Type: %s\n", customers[i].accountType);
            printf("Account Holder Name: %s\n", customers[i].accountHolderName);
            printf("Balance: %.2lf\n\n", customers[i].balance);
        }
    }

    return 0;
}