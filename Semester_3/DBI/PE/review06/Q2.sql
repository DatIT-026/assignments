SELECT m.*
FROM Members m
WHERE m.Sex = 'Male' AND YEAR(m.BirthDate) > 1990