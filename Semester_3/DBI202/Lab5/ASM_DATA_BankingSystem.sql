INSERT INTO CUSTOMER (CustomerID, FullName, Address, Phone, DOB, Sex)
VALUES
(1, 'Nguyen Van An', '123 Le Loi, Ha Noi', '0905123456', '1988-03-15', 'Male'),
(2, 'Tran Thi Bich', '45 Nguyen Trai, Da Nang', '0905789123', '1992-07-09', 'Female'),
(3, 'Le Hoang Nam', '78 Hai Ba Trung, HCM', '0912345678', '1985-12-22', 'Male'),
(4, 'Pham Minh Chau', '23 Nguyen Van Cu, Hue', '0978123987', '1998-04-01', 'Female'),
(5, 'Do Quang Huy', '89 Phan Dinh Phung, Ha Noi', '0904556677', '1990-09-30', 'Male');

INSERT INTO BRANCH (BranchID, BranchName, Phone, Address, ManagerName, Start_Date)
VALUES
(1, 'Ha Noi Branch', '0241234567', '12 Ly Thuong Kiet, Ha Noi', 'Nguyen Thi Lan', '2015-06-01'),
(2, 'Da Nang Branch', '0236123456', '56 Tran Hung Dao, Da Nang', 'Tran Quoc Hung', '2017-09-10'),
(3, 'HCM Branch', '0289123456', '101 Nguyen Hue, HCM', 'Le Van Minh', '2018-01-15');

INSERT INTO EMPLOYEE (Employee_ID, Full_Name, Address, Phone_Number, Sex, Birthdate, Salary, BranchID)
VALUES
(1, 'Pham Quang Tuan', 'Ha Noi', '0906789001', 'Male', '1990-11-02', 18000000, 1),
(2, 'Nguyen Thi Hoa', 'Da Nang', '0906123455', 'Female', '1995-02-20', 15000000, 2),
(3, 'Tran Van Khoa', 'HCM', '0912789001', 'Male', '1987-08-08', 22000000, 3),
(4, 'Le Thi My Linh', 'HCM', '0908123459', 'Female', '1992-03-10', 16000000, 3),
(5, 'Do Duc Hieu', 'Ha Noi', '0905789990', 'Male', '1994-12-25', 17000000, 1);

INSERT INTO ACCOUNT (AccountID, AccountType, Balance, OpenDate, Status, CustomerID, BranchID)
VALUES
(1, 'Savings', 5000000, '2022-01-10', 'Active', 1, 1),
(2, 'Checking', 12000000, '2021-05-22', 'Active', 2, 2),
(3, 'Savings', 3500000, '2023-03-15', 'Frozen', 3, 3),
(4, 'Checking', 7000000, '2020-11-05', 'Closed', 4, 1),
(5, 'Savings', 25000000, '2024-02-12', 'Active', 5, 2);

INSERT INTO LOAN (LoanID, LoanType, LoanAmount, Status, StartDate, CustomerID, BranchID)
VALUES
(1, 'Home Loan', 500000000, 'Approved', '2021-08-10', 1, 1),
(2, 'Car Loan', 300000000, 'Paid Off', '2020-05-15', 2, 2),
(3, 'Education Loan', 150000000, 'Pending', '2023-06-20', 3, 3),
(4, 'Business Loan', 800000000, 'Approved', '2022-09-01', 4, 1),
(5, 'Personal Loan', 100000000, 'Default', '2021-02-18', 5, 2);

INSERT INTO TRANSACTIONS (TransactionID, TransactionDate, Amount, BalanceAfter, AccountID)
VALUES
(1, '2024-10-01 10:00:00', 2000000, 7000000, 1),
(2, '2024-10-05 14:30:00', -1500000, 10500000, 2),
(3, '2024-10-10 09:45:00', 500000, 4000000, 3),
(4, '2024-10-12 16:00:00', -700000, 6300000, 4),
(5, '2024-10-15 11:20:00', 3000000, 28000000, 5);


SELECT * FROM CUSTOMER;
SELECT * FROM BRANCH;
SELECT * FROM EMPLOYEE;
SELECT * FROM ACCOUNT;
SELECT * FROM LOAN;
SELECT * FROM TRANSACTIONS;