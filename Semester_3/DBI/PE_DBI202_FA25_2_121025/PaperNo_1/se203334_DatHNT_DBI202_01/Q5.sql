SELECT me.mechanicName, (sm.hours) FROM Mechanic me
JOIN ServiceMehanic sm ON sm.mechanicID = me.mechanicID
WHERE sm.hours = (
	SELECT MAX(sm.hours) FROM Mechanic me
	JOIN ServiceMehanic sm ON sm.mechanicID = me.mechanicID
)