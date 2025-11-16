CREATE FUNCTION f_tk(@mechanicID decimal(18,0))
RETURNS TABLE
AS
RETURN
(
	SELECT me.mechanicID, me.mechanicName, SUM(sm.hours) AS sumHours
	FROM Mechanic me
	JOIN ServiceMehanic sm ON sm.mechanicID = me.mechanicID
	WHERE @mechanicID = me.mechanicID
	GROUP BY me.mechanicID, me.mechanicName
)