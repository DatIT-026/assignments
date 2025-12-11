-- subquery
SELECT e.eventID, e.name, e.StartTime, e.EndTime, e.locationID
FROM Events e
WHERE e.eventID IN (
	SELECT w.eventID FROM workFor w
	GROUP BY w.eventID
	HAVING COUNT(w.staffID) > 1
)

-- CTE
WITH moreThan1Staff AS (
	SELECT w.eventID, COUNT(staffID) AS numStaff FROM workFor w
	GROUP BY w.eventID
	HAVING COUNT(w.staffID) > 1
)

SELECT e.eventID, e.name, e.StartTime, e.EndTime, e.locationID
FROM Events e
JOIN moreThan1Staff m ON e.eventID = m.eventID