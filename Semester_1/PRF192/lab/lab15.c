#include<stdio.h>
#include<string.h>
#include<ctype.h>

int countSpace(const char *str) {
    int count = 0;
    for(int i = 0; i < strlen(str) - 1; ++i) {
        if(isspace((unsigned char)str[i])) {
            count++;
        }
    }
    return count;
}

int upperCase(const char *str) {
    int count = 0;
    for(int i = 0; str[i] != '\0'; ++i) {
        if(isupper((unsigned char)str[i])) {
            count++;
        }
    }
    return count;
}

int lowerCase(const char *str) {
    int count = 0;
    for(int i = 0; str[i] != '\0'; ++i) {
        if(islower((unsigned char)str[i])) {
            count++;
        }
    }
    return count;
}

int main()      {    
    char str[100];

    fgets(str, sizeof(str), stdin);

    printf("There are %d letters in the string.", strlen(str)-1);
    printf("\nThere are %d spaces in the string.", countSpace(str));
    printf("\nThere are %d uppercase letters in the string.", upperCase(str));
    printf("\nThere are %d lowercase letters in the string.", lowerCase(str));

    return 0;
}