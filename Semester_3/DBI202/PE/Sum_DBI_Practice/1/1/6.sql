WITH countOrders AS (
	SELECT o.CustomerID, COUNT(o.ID) AS numberOfOrders FROM Orders o
	GROUP BY o.CustomerID
),

maxCount AS (
	SELECT co.CustomerID, MAX(numberOfOrders) AS maxOrder
	FROM countOrders co
	GROUP BY co.CustomerID
)

SELECT c.ID, c.CustomerName, mc.maxOrder FROM Customer c
JOIN maxCount mc ON mc.CustomerID = c.ID
WHERE mc.maxOrder IN (
	SELECT MAX(mc.maxOrder) FROM maxCount mc
)