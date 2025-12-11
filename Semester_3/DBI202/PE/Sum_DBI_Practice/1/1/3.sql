SELECT c.ID, CustomerName, Country, City, State, PostalCode, Region 
FROM Customer c
JOIN Orders o ON o.CustomerID = c.ID
WHERE CustomerName LIKE 'B%' AND MONTH(o.OrderDate) = 12 AND YEAR(o.OrderDate) = 2017