SELECT e.EmployeeID, e.FullName AS EmployeeFullName, Role, OrderID, OrderDate, c.CustomerID, c.FullName AS CustomerFullName
FROM Employees e
LEFT JOIN Orders o ON o.EmployeeID = e.EmployeeID AND MONTH(o.OrderDate) IN (3, 4) AND YEAR(o.OrderDate) = 2025
LEFT JOIN Customers c ON c.CustomerID = o.CustomerID
WHERE Role = 'Chef'
ORDER BY EmployeeFullName ASC, o.OrderID DESC