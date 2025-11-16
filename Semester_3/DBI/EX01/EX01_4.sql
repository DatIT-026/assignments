SELECT DISTINCT Enrollment.StudentID FROM Enrollment
JOIN Course ON Course.CourseID = Enrollment.CourseID
WHERE Course.CourseName = 'Database';