USE EX01

SELECT DISTINCT Student.Name FROM Student
JOIN Enrollment ON Student.StudentID = Enrollment.StudentID
JOIN Course ON Course.CourseID = Enrollment.CourseID
WHERE Course.CourseName = 'Algebra';