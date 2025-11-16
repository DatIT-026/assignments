INSERT INTO ranking_system (id, system_name)
VALUES 
    (4, 'QS World University Rankings')

INSERT INTO ranking_criteria (id, criteria_name, ranking_system_id)
VALUES
	(22, 'Academic Reputation', 4),
	(23, 'Ciations per faculty', 4)