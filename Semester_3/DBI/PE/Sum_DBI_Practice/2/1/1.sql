CREATE TABLE Employees(
	EmpID int PRIMARY KEY,
	name nvarchar(50),
	salary money
);

CREATE TABLE Managers(
	bonus money,
	
	EmpID int PRIMARY KEY,
	FOREIGN KEY (EmpID) REFERENCES Employees(EmpID)
);

CREATE TABLE Projects(
	ProjectID int PRIMARY KEY,
	name nvarchar(200),

	EmpID int,
	FOREIGN KEY (EmpID) REFERENCES Managers(EmpID)
);

CREATE TABLE Works(
	hours int,
	ProjectID int,
	EmpID int,

	FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID),
	FOREIGN KEY (EmpID) REFERENCES Employees(EmpID),

	PRIMARY KEY (ProjectID, EmpID)
);
