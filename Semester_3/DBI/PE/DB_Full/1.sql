CREATE TABLE tblStudents (
	stid nchar(7) PRIMARY KEY,
	name nvarchar(25),
	birthday date,
	phone nvarchar(15)
)

CREATE TABLE tblSubjects (
	subid nchar(6) PRIMARY KEY,
	subname nvarchar(50),
	unit int
)

CREATE TABLE Result (
	score01 float,
	score02 float,

	stid nchar(7),
	subid nchar(6),

	PRIMARY KEY(stid, subid),

	FOREIGN KEY (stid) REFERENCES tblStudents(stid),
	FOREIGN KEY (subid) REFERENCES tblSubjects(subid)
)