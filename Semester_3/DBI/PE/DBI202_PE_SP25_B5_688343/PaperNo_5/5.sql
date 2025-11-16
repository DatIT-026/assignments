-- Subquery
SELECT mi.MenuItemID, mi.Name, mi.Category,
	SUM(ood.Quantity) AS TotalQuantity,
    SUM(ood.Quantity * ood.Price) AS TotalAmount,
    COUNT(DISTINCT ood.CustomerID) AS NumberOfCustomers
FROM MenuItems mi
LEFT JOIN (
	SELECT od.MenuItemID, od.Quantity, od.Price, o.CustomerID FROM OrderDetails od
    JOIN Orders o ON od.OrderID = o.OrderID
    WHERE o.OrderDate >= '2024-10-01' AND o.OrderDate < '2024-11-01'
) AS ood ON ood.MenuItemID = mi.MenuItemID
WHERE mi.Category = 'Main Course'
GROUP BY mi.MenuItemID, mi.Name, mi.Category
ORDER BY mi.MenuItemID

-- CTE
WITH OctoberOrderDetails AS (
    SELECT od.MenuItemID, od.Quantity, od.Price, o.CustomerID FROM OrderDetails od
    JOIN Orders o ON od.OrderID = o.OrderID
    WHERE o.OrderDate >= '2024-10-01' AND o.OrderDate < '2024-11-01'
)
SELECT mi.MenuItemID, mi.Name, mi.Category,
    SUM(ood.Quantity) AS TotalQuantity,
    SUM(ood.Quantity * ood.Price) AS TotalAmount,
    COUNT(DISTINCT ood.CustomerID) AS NumberOfCustomers
FROM MenuItems mi
LEFT JOIN OctoberOrderDetails ood ON ood.MenuItemID = mi.MenuItemID
WHERE mi.Category = 'Main Course'
GROUP BY mi.MenuItemID, mi.Name, mi.Category
ORDER BY mi.MenuItemID