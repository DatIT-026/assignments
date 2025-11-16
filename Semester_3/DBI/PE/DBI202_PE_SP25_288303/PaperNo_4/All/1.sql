CREATE TABLE Customers (
	custID int PRIMARY KEY,
	FirstName nvarchar(30) NOT NULL,
	LastName nvarchar(30) NOT NULL,
	phone nvarchar(100)
);

CREATE TABLE Accounts (
	accNo varchar(25) PRIMARY KEY,
	balance money,
	openTime datetime,
	custID int,
	FOREIGN KEY (custID) REFERENCES Customers(custID)
);

CREATE TABLE Cards (
	CardNo varchar(20) PRIMARY KEY,
	expiryDate date,
	creditLimit money,
	cardType varchar(30),
	accNo varchar(25),
	FOREIGN KEY (accNo) REFERENCES Accounts(accNo)
);