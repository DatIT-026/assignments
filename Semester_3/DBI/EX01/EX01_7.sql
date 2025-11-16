-- cach 1: dung NOT IN
SELECT StudentID FROM Student
WHERE StudentID NOT IN (SELECT StudentID FROM Enrollment);

-- cach 2: dung JOIN LEFT
SELECT Student.StudentID FROM Student
LEFT JOIN Enrollment ON Student.StudentID = Enrollment.StudentID
WHERE Enrollment.StudentID IS NULL;
