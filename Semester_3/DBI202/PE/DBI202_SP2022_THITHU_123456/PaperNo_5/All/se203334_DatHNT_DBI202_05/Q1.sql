CREATE TABLE Departments (
	DeptID varchar(20) PRIMARY KEY,
	name nvarchar(200),
	office nvarchar(100)
);

CREATE TABLE Employees (
	EmpCode varchar(20) PRIMARY KEY,
	Name nvarchar(50),
	BirthDate date,

	DeptID varchar(20),
	FOREIGN KEY (DeptID) REFERENCES Departments(DeptID)
);

CREATE TABLE Dependants (
	EmpCode varchar(20) NOT NULL,
	Number int NOT NULL,

	Name nvarchar(50),
	BirthDate Date,
	Role nvarchar(30),

	FOREIGN KEY (EmpCode) REFERENCES Employees(EmpCode),
	PRIMARY KEY (EmpCode, Number)
);