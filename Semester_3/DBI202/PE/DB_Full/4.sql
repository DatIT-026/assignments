SELECT uy.university_id,
	   u.university_name,
	   uy.year, 
	   uy.num_students,
	   uy.pct_international_students,
	   u.country_id
FROM university_year uy
JOIN university u ON u.id = uy.university_id
WHERE year = 2016 AND pct_international_students > 30
ORDER BY university_name