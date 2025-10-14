#include<stdio.h>
#include<stdbool.h>
#include<math.h>
#include<stdlib.h>

int gcd(int a, int b) {
    while(b != 0) {
        int temp = b;
        b = a % b; // Get the remainder
        a = temp;
    }
    return abs(a); // When b = 0, a is the GCD
}

int lcm(int a, int b) {
    int gcd_test = gcd(a, b);
    if(gcd_test == 0) return 0;
        else {
            return abs(a * b) / gcd_test;
        }
}

bool determine_prime(int num) {
    for(int i = 2; i <= sqrt(num); ++i) {
        if(num % i == 0) {
            return false;
        }
    }
    return true;
}

int main() {
    int n, a, b;
    printf("a = ");
    scanf("%d", &a);
    printf("b = ");
    scanf("%d", &b);

    printf("Enter n: ");
    scanf("%d", &n);

    while(1) {
        if (n < 2) {
            printf("n must be greater than 2, try again: ");
            scanf("%d", &n);
            continue;
        }
        break;
    }

    printf("\nGCD of %d and %d is: %d", a, b, gcd(a, b));
    printf("\nLCM of %d and %d is: %d", a, b, lcm(a, b));

    int smallest_prime = 3;
    int largest_prime = 3;
    
    for(int i = 2; i < n; ++i) {
        bool is_prime = determine_prime(i);
        if(is_prime && i > largest_prime) {
            largest_prime = i;
        }
    }

    printf("\nThe smallest prime numbers is %d", smallest_prime);
    printf("\nThe largest prime numbers up to %d is: %d", n, largest_prime);

    return 0;
}