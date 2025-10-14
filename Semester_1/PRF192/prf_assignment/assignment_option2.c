#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAX_NAME_LEN 50
#define MAX_RECORDS 100

typedef struct {
    int id;
    char firstName[MAX_NAME_LEN];
    char lastName[MAX_NAME_LEN];
    float gpa;
} Student;

void addStudent(const char *filename) {
    FILE *fp = fopen(filename, "a"); // Append mode
    if(!fp) {
        perror("File open error");
        return;
    }

    Student s;
    printf("Enter Student ID: ");
    scanf("%d", &s.id);
    printf("Enter First Name: ");
    scanf("%s", s.firstName);
    printf("Enter Last Name: ");
    scanf("%s", s.lastName);
    printf("Enter GPA: ");
    scanf("%f", &s.gpa);

    /*
        &s: Gets the memory address of s.
        sizeof(Student): Finds out how many bytes one Student record uses.
        1: Writes one record.
        fp: The file opened for writing.
    */
    fwrite(&s, sizeof(Student), 1, fp);
    printf("\nStudent added successfully.\n");

    fclose(fp);
}

void searchStudentById(const char *filename, int id) {
    FILE *fp = fopen(filename, "r");
    if(!fp) {
        perror("File open error.");
        return;
    }

    int found = 0;

    Student s;
    
    while(fread(&s, sizeof(Student), 1, fp)) {
        if(s.id == id) {
            found = 1;
            printf("Student Found:\n");
            printf("\n----------------------------------");
            printf("\nID: %d", s.id);
            printf("\nFirst Name: %s", s.firstName);
            printf("\nLast Name: %s", s.lastName);
            printf("\nGPA: %.2f\n", s.gpa);
            break;
        }
    }

    if(!found) {
        printf("Student with ID %d not found.\n", id);
    }
    
    fclose(fp);
}

void displayStudents(const char *filename) {
    
}

void searchStudentByLastName(const char *filename, const char *lastName) {
    FILE *fp = fopen(filename, "r");
    if(!fp) {
        perror("File open error");
        return;
    }

    int found = 0;

    Student s;

    while(fread(&s, sizeof(Student), 1, fp)) {
        if(strcmp(s.lastName, lastName) == 0) {
            found = 1;
            printf("Student Found:\n");
            printf("\n----------------------------------");
            printf("\nID: %d", s.id);
            printf("\nFirst Name: %s", s.firstName);
            printf("\nLast Name: %s", s.lastName);
            printf("\nGPA: %.2f\n", s.gpa);
            break;
        }
    }
    if(!found) {
        printf("Student with the last name %s is not found.\n", lastName);
    }
    
    fclose(fp);
}

void sortStudentByLastName(const char *filename) {

}

int main() {
    const char *filename = "students.txt";
    int choice, id;
    char lastName[MAX_NAME_LEN];

    do {
        printf("\n-- Student Management System --\n");
        printf("1. Add Student\n");
        printf("2. Search Student by ID\n");
        printf("3. Search Student by Last Name\n");
        printf("4. Display Students Sorted by Last Name\n");
        printf("5. Exit\n");

        printf("> Enter your choice: ");
        scanf("%d", &choice);

        switch(choice) {
            case 1:
                addStudent(filename);
                break;
            case 2:
                printf("Enter Student ID to search: ");
                scanf("%d", &id);
                searchStudentById(filename, id);
                break;
            case 3:
                printf("Enter Last Name to search: ");
                scanf("%s", lastName);
                searchStudentByLastName(filename, lastName);
                break;
            case 4:
                sortStudentByLastName(filename);
                displayStudents(filename);
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