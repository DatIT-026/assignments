SELECT OrderID, OrderDate, FullName AS CustomerFullName, PhoneNumber, TableNumber, Capacity FROM Orders o
JOIN RestaurantTables rt ON rt.TableID = o.TableID
JOIN Employees e ON e.EmployeeID = o.EmployeeID
WHERE OrderDate >= '2024-11-01' AND OrderDate <= '2024-12-31' AND Capacity = 6