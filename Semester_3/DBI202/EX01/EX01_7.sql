USE EX01

-- case 1: use NOT IN
SELECT StudentID FROM Student
WHERE StudentID NOT IN (SELECT StudentID FROM Enrollment);

-- case 2: use JOIN LEFT
SELECT Student.StudentID FROM Student -- get studentID from the Student table
LEFT JOIN Enrollment ON Student.StudentID = Enrollment.StudentID -- match with enrollment, keep unmatched as NULL
WHERE Enrollment.StudentID IS NULL; -- filter students without any enrollment
