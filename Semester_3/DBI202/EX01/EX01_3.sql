SELECT DISTINCT Course.CourseName FROM Course
JOIN Enrollment ON Enrollment.CourseID = Course.CourseID
WHERE Enrollment.Semester = 'Fall 2024';