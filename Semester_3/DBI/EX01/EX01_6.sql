USE EX01

SELECT DISTINCT Course.CourseName FROM Student
JOIN Enrollment ON Student.StudentID = Enrollment.StudentID
JOIN Course ON Enrollment.CourseID = Course.CourseID
WHERE Student.Major = 'Mathematics';