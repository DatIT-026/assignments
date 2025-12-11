SELECT studentId, name, surname, gender, class, point FROM students
WHERE gender = 'F' AND class LIKE '12_' AND point >= 500 
ORDER BY class, studentId