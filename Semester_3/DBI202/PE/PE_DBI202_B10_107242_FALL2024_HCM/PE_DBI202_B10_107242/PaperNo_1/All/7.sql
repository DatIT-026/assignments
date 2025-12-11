-- Subquery (tham khao)
SELECT ce.Name 
FROM (
	SELECT l.Name, COUNT(e.locationID) AS countLocation 
	FROM Events e
	JOIN Locations l ON l.locationID = e.locationID
	GROUP BY l.Name
) AS ce
WHERE ce.countLocation = (
	SELECT MAX(ce1.countLocation) 
	FROM (
		SELECT l.Name, COUNT(e.locationID) AS countLocation 
		FROM Events e
		JOIN Locations l ON l.locationID = e.locationID
		GROUP BY l.Name
	) AS ce1
);

-- CTE
WITH countEvents AS (
	SELECT l.Name, COUNT(e.locationID) AS countLocation FROM Events e
	JOIN Locations l ON l.locationID = e.locationID
	GROUP BY l.Name
)

SELECT ce.Name FROM countEvents ce
WHERE ce.countLocation = (
	SELECT MAX(ce1.countLocation) FROM countEvents ce1
)