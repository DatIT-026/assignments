CREATE TABLE Teachers (
	TeacherID int PRIMARY KEY,
	Name nvarchar(50),
	Address nvarchar(200),
	Gender char(1)
);

CREATE TABLE Students (
	StudentID int PRIMARY KEY,
	Name nvarchar(50),
	Address nvarchar(200),
	Gender char(1)
);

CREATE TABLE Classes (
	ClassID int PRIMARY KEY,
	GroupID char(6),
	courseID char(6),
	NoCredits int,
	Semester char(10),
	year int,

	TeacherID int,
	FOREIGN KEY (TeacherID) REFERENCES Teachers(TeacherID)
);

CREATE TABLE Attend (
	Date date,
	Slot int,
	Attend bit,

	PRIMARY KEY (StudentID, ClassID, Date, Slot),

	StudentID int,
	ClassID int,	

	FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
	FOREIGN KEY (ClassID) REFERENCES Classes(ClassID),
);