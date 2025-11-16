SELECT locationID, COUNT(eventID) AS TotalEvents FROM Events
GROUP BY locationID