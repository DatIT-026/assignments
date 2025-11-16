SELECT u.id AS university_id,
	   u.university_name, uy.year,
	   uy.student_staff_ratio
FROM university u
JOIN university_year uy ON uy.university_id = u.id 
WHERE uy.year = 2015 AND uy.student_staff_ratio <= ALL (
		SELECT MIN(student_staff_ratio)
		FROM university u2
		JOIN university_year uy2 ON uy2.university_id = u2.id
		GROUP BY u2.id, u2.university_name, uy2.year
	)
