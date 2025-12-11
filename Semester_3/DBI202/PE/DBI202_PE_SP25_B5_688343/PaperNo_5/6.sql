-- Subquery
SELECT e.EmployeeID, e.FullName, e.Role, ec.OrderCount FROM Employees e
JOIN ( 
	SELECT EmployeeID, COUNT(OrderID) AS OrderCount FROM Orders
    GROUP BY EmployeeID
) AS ec ON e.EmployeeID = ec.EmployeeID
WHERE ec.OrderCount > (
     SELECT AVG(1.0 * OrderCount) -- nhan 1.0 de dam bao la so thuc
     FROM (
           SELECT COUNT(OrderID) AS OrderCount FROM Orders
           GROUP BY EmployeeID
    ) AS AllEmployeeCounts
)
ORDER BY ec.OrderCount DESC, e.FullName ASC;

-- CTE
WITH EmployeeOrderCounts AS (
    SELECT EmployeeID, COUNT(OrderID) AS OrderCount FROM Orders
    GROUP BY EmployeeID
)

SELECT e.EmployeeID, e.FullName, e.Role, eoc.OrderCount FROM Employees e
JOIN EmployeeOrderCounts eoc ON e.EmployeeID = eoc.EmployeeID
WHERE eoc.OrderCount > ( SELECT AVG(1.0 * OrderCount) FROM EmployeeOrderCounts )
ORDER BY eoc.OrderCount DESC, e.FullName ASC;