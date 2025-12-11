CREATE TABLE CUSTOMER (
    CustomerID INT PRIMARY KEY,
    FullName VARCHAR(255) NOT NULL,
    Address VARCHAR(255),
    Phone VARCHAR(20) NOT NULL UNIQUE,
    DOB DATE,
    Sex VARCHAR(10) CHECK (Sex IN ('Male', 'Female')) NOT NULL
);

CREATE TABLE BRANCH (
    BranchID INT PRIMARY KEY,
    BranchName VARCHAR(255) NOT NULL UNIQUE,
    Phone VARCHAR(20),
    Address VARCHAR(255),
    ManagerName VARCHAR(255),
    Start_Date DATE
);

CREATE TABLE EMPLOYEE (
    Employee_ID INT PRIMARY KEY,
    Full_Name VARCHAR(255) NOT NULL,
    Address VARCHAR(255),
    Phone_Number VARCHAR(20) NOT NULL UNIQUE,
    Sex VARCHAR(10) CHECK (Sex IN ('Male', 'Female')),
    Birthdate DATE,
    Salary DECIMAL(10) NOT NULL CHECK (Salary > 0),
    BranchID INT NOT NULL,
    FOREIGN KEY (BranchID) REFERENCES BRANCH(BranchID)
);

CREATE TABLE ACCOUNT (
    AccountID INT PRIMARY KEY,
    AccountType VARCHAR(100) NOT NULL,
    Balance DECIMAL(12,2) NOT NULL CHECK (Balance >= 0) DEFAULT 0,
    OpenDate DATE NOT NULL,
    Status VARCHAR(50) NOT NULL CHECK (Status IN ('Active', 'Closed', 'Frozen')),
    CustomerID INT NOT NULL,
    BranchID INT NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID),
    FOREIGN KEY (BranchID) REFERENCES BRANCH(BranchID)
);

CREATE TABLE LOAN (
    LoanID INT PRIMARY KEY,
    LoanType VARCHAR(100) NOT NULL,
    LoanAmount DECIMAL(12,2) NOT NULL CHECK (LoanAmount > 0),
    Status VARCHAR(50) NOT NULL CHECK (Status IN ('Pending', 'Approved', 'Paid Off', 'Default')),
    StartDate DATE NOT NULL,
    CustomerID INT NOT NULL,
    BranchID INT NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID),
    FOREIGN KEY (BranchID) REFERENCES BRANCH(BranchID)
);

CREATE TABLE TRANSACTIONS (
    TransactionID INT PRIMARY KEY,
    TransactionDate DATETIME NOT NULL DEFAULT GETDATE(),
    Amount DECIMAL(12,2) NOT NULL CHECK (Amount <> 0),
    BalanceAfter DECIMAL(12,2),
    AccountID INT NOT NULL,
    FOREIGN KEY (AccountID) REFERENCES ACCOUNT(AccountID)
);