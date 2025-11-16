SELECT ranking_system_id, criteria_name 
FROM ranking_criteria
WHERE ranking_system_id = 1 OR ranking_system_id = 2
ORDER BY ranking_system_id, criteria_name