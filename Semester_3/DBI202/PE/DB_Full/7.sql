SELECT ury.university_id, u.university_name,
	   rc.ranking_system_id, rc.criteria_name, ury.year, ury.score
FROM university_ranking_year ury
JOIN university u ON u.id = ury.university_id
JOIN ranking_criteria rc ON rc.id = ury.ranking_criteria_id
WHERE ury.year = 2016 AND rc.criteria_name = 'Teaching' AND ury.score IN (
	SELECT ury2.score
	FROM university_ranking_year ury2
	JOIN ranking_criteria rc2 ON rc2.id = ury2.ranking_criteria_id
	WHERE ury2.year = 2016 AND rc2.criteria_name = 'Teaching'
	GROUP BY ury2.score
	HAVING COUNT(*) > 1

)
ORDER BY score DESC