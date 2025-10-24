CREATE TABLE Banks (
	SWIFTCode varchar(15) PRIMARY KEY,
	Name nvarchar(200)
);

CREATE TABLE Branches (
	BranchNo varchar(20) PRIMARY KEY,
	City nvarchar(50),
	Country nvarchar(50),
	Address nvarchar(200),
	SWIFTCode varchar(15),
	FOREIGN KEY (SWIFTCode) REFERENCES Banks
);

CREATE TABLE Accounts (
	AccountNo varchar(30) PRIMARY KEY,
	Type nvarchar(100),
	Balance float,
	BranchNo varchar(20),
	FOREIGN KEY (BranchNo) REFERENCES Branches
);
