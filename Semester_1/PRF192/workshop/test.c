#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// Define the structure for product information
struct Product {
    int product_id;
    char product_name[50];
    float price;
    int quantity;
};

void writeProducts(const char *filename, int numProducts) {
    FILE *fp = fopen(filename, "wb");
    if(!fp) {
        perror("File open error.");
        return;
    }

    for(int i = 0; i < numProducts; ++i) {
        struct Product prod;
        printf("\nEnter details for product %d:\n", i+1);
        printf("Product ID: ");
        scanf("%d", &prod.product_id);
        printf("Product Name: ");
        scanf(" %[^\n]", prod.product_name); //scanf(" %[^\n]", ...) means reads entire string include space
        printf("Product Price: ");
        scanf("%f", &prod.price);
        printf("Product Quantity: ");
        scanf("%d", &prod.quantity);
        /*
            This fwrite command performs the following steps:

            Retrieves the address of the variable prod (&prod) that holds the product data.
            Determines the size of one product (sizeof(struct Product)).
            Writes 1 object (the product) into the file.
            Uses the file pointer fp, which was opened in binary write mode.
        */
        fwrite(&prod, sizeof(struct Product), 1, fp);
    }
    fclose(fp);
}

void appendProducts(const char *filename, int numProducts) {
    FILE *fp = fopen(filename, "ab");
    if(!fp) {
        perror("File open error");
        return;
    }

    for(int i = 0; i < numProducts; ++i) {
        struct Product prod;
        printf("\nEnter details for product %d:\n", i+1);
        printf("Product ID: ");
        scanf("%d", &prod.product_id);
        printf("Product Name: ");
        scanf(" %[^\n]", prod.product_name);
        printf("Product Price: ");
        scanf("%f", &prod.price);
        printf("Product Quantity: ");
        scanf("%d", &prod.quantity);
        // It writes one product record from memory to a binary file.
        fwrite(&prod, sizeof(struct Product), 1, fp);
    }
    fclose(fp);
}

void readProducts(const char *filename) {
    FILE *fp = fopen(filename, "rb");
    if(!fp) {
        perror("File open error");
        return;
    }
    struct Product prod;
    /*
        - &prod: Where to store the read product.
        - sizeof(struct Product): How much data to read (size of one product).
        - 1: Read one product record.
        - fp: The file to read from.

        Together, it reads one complete product record from the file into the program.
    */
    printf("Reading products from the file:\n");
    printf("-----------------------------------------------------\n");
    // Use %-15s, %-20s, etc. to left-align text within fixed-width columns.
    printf("%-15s %-17s %-8s %-10s\n", "Product ID", "Product Name", "Price", "Quantity");
    while(fread(&prod, sizeof(struct Product), 1, fp) == 1) {        
        printf("%-15d %-17s %-8.2f %-10d\n",
            prod.product_id, prod.product_name, prod.price, prod.quantity);
    }
    printf("-----------------------------------------------------\n");
    fclose(fp);
}

void modifyProducts(const char *filename) {
    FILE *fp = fopen(filename, "rb+");
    if(!fp) {
        perror("File open error");
        return;
    }
    int targetID;
    printf("Enter Product ID to modify: ");
    scanf("%d", &targetID);

    struct Product prod;
    long pos; int found = 0;

    // It reads one product record from the binary file into the variable prod.
    while(fread(&prod, sizeof(struct Product), 1 ,fp) == 1) {
        if(prod.product_id == targetID) {
            found = 1;
            printf("Product found. Enter new details:\n");
            printf("New Product Name: ");
            scanf(" %[^\n]", prod.product_name);
            printf("New Price: ");
            scanf("%f", &prod.price);
            printf("New Quantity: ");
            scanf("%d", &prod.quantity);
            fseek(fp, -sizeof(struct Product), SEEK_CUR);
            fwrite(&prod, sizeof(struct Product), 1, fp);
            break;
        }
    }
    if(!found) printf("Product with ID %d not found.\n", targetID);
    fclose(fp);
}

int main() {
    const char *filename = "products.bin";
    int choice, numProducts;

    do {
        printf("\n-- Product Management System --\n");
        printf("1. Write Products\n");
        printf("2. Append Products\n");
        printf("3. Read Products\n");
        printf("4. Modify Products\n");
        printf("5. Exit\n");

        printf("> Enter your choice: ");
        scanf("%d", &choice);

        switch(choice) {
            case 1:
                printf("Enter the number of products to write: ");
                scanf("%d", &numProducts);
                writeProducts(filename, numProducts);
                printf("\nProducts have been written to the file successfully.\n");
                break;

            case 2:
                printf("Enter the number of products to append: ");
                scanf("%d", &numProducts);
                appendProducts(filename, numProducts);
                printf("\nProducts have been appended to the file successfully.\n");
                break;

            case 3:
                readProducts(filename);
                break;

            case 4:
                modifyProducts(filename);
                printf("Product updated successfully.\n");
                break;

            case 5:
                printf("Exiting...\n");
                break;

            default:
                printf("Invalid choice. Please try again.\n");
        } 
    } while(choice != 5);
    return 0;
}