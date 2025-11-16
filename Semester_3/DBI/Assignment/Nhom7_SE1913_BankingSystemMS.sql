CREATE TABLE BRANCH (
    BranchID INT PRIMARY KEY,
    BranchName VARCHAR(255) NOT NULL UNIQUE,
    Phone VARCHAR(20),
    Address VARCHAR(255)
);

CREATE TABLE CUSTOMER (
    CustomerID INT PRIMARY KEY,
    FullName VARCHAR(255) NOT NULL,
    DOB DATE,
    Address VARCHAR(255),
    Phone VARCHAR(20) NOT NULL UNIQUE,
    Sex VARCHAR(10) CHECK (Sex IN ('Male', 'Female')) NOT NULL
);

CREATE TABLE EMPLOYEE (
    Employee_ID INT PRIMARY KEY,
    Full_Name VARCHAR(255) NOT NULL,
    Address VARCHAR(255),
    Phone_Number VARCHAR(20) NOT NULL UNIQUE,
    Sex VARCHAR(10) CHECK (Sex IN ('Male', 'Female')),
    Birthdate DATE,
    Salary DECIMAL(15,2) NOT NULL CHECK (Salary > 0),
    
    BranchID INT NOT NULL,
    FOREIGN KEY (BranchID) REFERENCES BRANCH(BranchID)
);

CREATE TABLE BRANCH_MANAGEMENT (
    BranchID INT PRIMARY KEY,
    ManagerID INT NOT NULL UNIQUE,
    
    FOREIGN KEY (BranchID) REFERENCES BRANCH(BranchID),
    FOREIGN KEY (ManagerID) REFERENCES EMPLOYEE(Employee_ID)
);

CREATE TABLE ACCOUNT (
    AccountID INT PRIMARY KEY,
    AccountType VARCHAR(100) NOT NULL,
    Balance DECIMAL(12,2) NOT NULL CHECK (Balance >= 0) DEFAULT 0,
    Status VARCHAR(50) NOT NULL CHECK (Status IN ('Active', 'Closed', 'Frozen')),
    OpenDate DATE NOT NULL,
    
    CustomerID INT NOT NULL,
    BranchID INT NOT NULL,
    
    FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID),
    FOREIGN KEY (BranchID) REFERENCES BRANCH(BranchID)
);

CREATE TABLE LOAN (
    LoanID INT PRIMARY KEY,
    LoanType VARCHAR(100) NOT NULL,
    LoanAmount DECIMAL(12,2) NOT NULL CHECK (LoanAmount > 0),
    StartDate DATE NOT NULL,
    EndDate DATE,
    Term VARCHAR(50),
    Status VARCHAR(50) NOT NULL CHECK (Status IN ('Pending', 'Approved', 'Paid Off', 'Default')),
    InterestRate DECIMAL(5,2) NOT NULL CHECK (InterestRate >= 0),

    BranchID INT NOT NULL,
    FOREIGN KEY (BranchID) REFERENCES BRANCH(BranchID)
);


CREATE TABLE CUSTOMER_LOAN (
    CustomerID INT NOT NULL,
    LoanID INT NOT NULL,
    
    PRIMARY KEY (CustomerID, LoanID), 
    FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID),
    FOREIGN KEY (LoanID) REFERENCES LOAN(LoanID)
);

CREATE TABLE TRANSACTIONS (
    TransactionID INT PRIMARY KEY,
    TransactionDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    Status VARCHAR(50) NOT NULL,
    TransactionType VARCHAR(50) NOT NULL,
    Amount DECIMAL(12,2) NOT NULL CHECK (Amount > 0),
    
    fromAccountID INT, 
    toAccountID INT,   
    
    FOREIGN KEY (fromAccountID) REFERENCES ACCOUNT(AccountID),
    FOREIGN KEY (toAccountID) REFERENCES ACCOUNT(AccountID),
    CHECK (fromAccountID IS NOT NULL OR toAccountID IS NOT NULL)
);

GO

INSERT INTO BRANCH (BranchID, BranchName, Phone, Address)
VALUES
(1, 'Ha Noi Branch', '0241234567', '12 Ly Thuong Kiet, Ha Noi'),
(2, 'Da Nang Branch', '0236123456', '56 Tran Hung Dao, Da Nang'),
(3, 'HCM Branch', '0289123456', '101 Nguyen Hue, HCM');

INSERT INTO CUSTOMER (CustomerID, FullName, Address, Phone, DOB, Sex)
VALUES
(1, 'Nguyen Van An', '123 Le Loi, Ha Noi', '0905123456', '1988-03-15', 'Male'),
(2, 'Tran Thi Bich', '45 Nguyen Trai, Da Nang', '0905789123', '1992-07-09', 'Female'),
(3, 'Le Hoang Nam', '78 Hai Ba Trung, HCM', '0912345678', '1985-12-22', 'Male'),
(4, 'Pham Minh Chau', '23 Nguyen Van Cu, Hue', '0978123987', '1998-04-01', 'Female'),
(5, 'Do Quang Huy', '89 Phan Dinh Phung, Ha Noi', '0904556677', '1990-09-30', 'Male');

INSERT INTO EMPLOYEE (Employee_ID, Full_Name, Address, Phone_Number, Sex, Birthdate, Salary, BranchID)
VALUES
(1, 'Pham Quang Tuan', 'Ha Noi', '0906789001', 'Male', '1990-11-02', 18000000, 1),
(2, 'Nguyen Thi Hoa', 'Da Nang', '0906123455', 'Female', '1995-02-20', 15000000, 2),
(3, 'Tran Van Khoa', 'HCM', '0912789001', 'Male', '1987-08-08', 22000000, 3),
(4, 'Le Thi My Linh', 'HCM', '0908123459', 'Female', '1992-03-10', 16000000, 3),
(5, 'Do Duc Hieu', 'Ha Noi', '0905789990', 'Male', '1994-12-25', 17000000, 1);

INSERT INTO BRANCH_MANAGEMENT (BranchID, ManagerID)
VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO ACCOUNT (AccountID, AccountType, Balance, OpenDate, Status, CustomerID, BranchID)
VALUES
(1, 'Savings', 5000000, '2022-01-10', 'Active', 1, 1),
(2, 'Checking', 12000000, '2021-05-22', 'Active', 2, 2),
(3, 'Savings', 3500000, '2023-03-15', 'Frozen', 3, 3),
(4, 'Checking', 7000000, '2020-11-05', 'Closed', 4, 1),
(5, 'Savings', 25000000, '2024-02-12', 'Active', 5, 2);

INSERT INTO LOAN (LoanID, LoanType, LoanAmount, StartDate, EndDate, Term, Status, InterestRate, BranchID)
VALUES
(1, 'Home Loan', 500000000, '2021-08-10', '2041-08-10', '20 Years', 'Approved', 7.5, 1),
(2, 'Car Loan', 300000000, '2020-05-15', '2025-05-15', '5 Years', 'Paid Off', 5.0, 2),
(3, 'Education Loan', 150000000, '2023-06-20', '2033-06-20', '10 Years', 'Pending', 4.5, 3),
(4, 'Business Loan', 800000000, '2022-09-01', '2032-09-01', '10 Years', 'Approved', 8.0, 1),
(5, 'Personal Loan', 100000000, '2021-02-18', '2024-02-18', '3 Years', 'Default', 12.0, 2);

INSERT INTO CUSTOMER_LOAN (CustomerID, LoanID)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(1, 2);

INSERT INTO TRANSACTIONS (TransactionID, TransactionDate, Amount, Status, TransactionType, fromAccountID, toAccountID)
VALUES
(1, '2024-10-01 10:00:00', 200000, 'Success', 'Withdrawal', 1, NULL),
(2, '2024-10-05 14:30:00', 1500000, 'Success', 'Withdrawal', 2, NULL),
(3, '2024-10-10 09:45:00', 500000, 'Success', 'Deposit', NULL, 3),
(4, '2024-10-12 16:00:00', 700000, 'Success', 'Withdrawal', 2, NULL),
(5, '2024-10-15 11:20:00', 3000000, 'Success', 'Deposit', NULL, 5),
(6, '2024-10-20 08:00:00', 1000000, 'Success', 'Transfer', 2, 1);