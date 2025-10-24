USE EX01

-- 1. Find the names of all students who are majoring in Computer Science.
SELECT Student.Name From Student WHERE Major = 'Computer Science';

-- 2. Find the CourseID and CourseNames of all courses that their credits less than or equal to 4.
SELECT Course.CourseID, Course.CourseName FROM Course WHERE Credits <= 4;

-- 3. List all course names that students have enrolled in the Fall 2024 semester.
SELECT Student.Name FROM Student

-- 4. Find the StudentIDs of students who are enrolled in the 'Database' course.
-- 5. Retrieve the names of students and the course names they are enrolled in.
-- 6. Find the course names that are taken by students majoring in 'Mathematics'.
-- 7. List all studentIDs who are not enrolled in any courses.
-- 8. List all the names of students who are enrolled in the 'Algebra' course.
