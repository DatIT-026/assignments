#include<stdio.h>
#include<stdlib.h> //strtod (string-to-double), malloc, exit
#include<string.h> // strcpy, strlen, strcmp
#include<ctype.h> // Func to check/convert character types (e.g isplace, isalpha)

// Declare getDouble function
double getDouble(double lower, double upper) {
    double value;
    char buffer[256]; // Store the user's input as text
    char *endptr; // helps detect any leftover char after converting the text to a number

    while(1) {
        printf("Enter a number between %.2f and %.2f: ", lower, upper);

        // Read Input, if reading fails, print an error and keep gonna try again
        char* readInput = fgets(buffer, sizeof(buffer), stdin); // `readInput` stores the memory address of the buffer after reading input.
        if(readInput == NULL) {
            printf("Error reading input.\n");
            continue;
        }

        // Convert Text to Number
        // endptr points to wherever the number part ends. Anything after that might be invalid
        value = strtod(buffer, &endptr);

        // Skip extra spaces
        while (isspace((unsigned char) * endptr)) {
            endptr++;
        }

        // Check for Leftover characters
        if(*endptr != '\0') {
            printf("Invalid Input. Try again.\n");
            continue;
        }

        // Check the range
        if(value < lower || value > upper) {
            printf("Out of range. Try again.\n");
            continue;
        }

        return value;
    }
}

int main() {
    double result = getDouble(-20.0, 20.0);
    printf("Accepted! %.2f\n", result);
    return 0;
}