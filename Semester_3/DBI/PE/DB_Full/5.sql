SELECT rs.id, rs.system_name, COUNT(rc.id) AS numberOfCriteria FROM ranking_system rs
JOIN ranking_criteria rc ON rc.ranking_system_id = rs.id
GROUP BY rs.id, rs.system_name
ORDER BY numberOfCriteria DESC