CREATE TRIGGER tr_insert_university_ranking
ON university_ranking_year
AFTER INSERT
AS
BEGIN
	SELECT 
		i.university_id, 
		u.university_name, 
		i.ranking_criteria_id,
		rc.criteria_name,
		i.year,
		i.score
	FROM inserted i
	JOIN university u ON u.id = i.university_id
	JOIN ranking_criteria rc ON rc.id = i.ranking_criteria_id
END;

-- TEST
INSERT INTO university_ranking_year(university_id, ranking_criteria_id, year, score)
VALUES 
	(1, 1, 2020, 99),
	(12, 2, 2020, 67)