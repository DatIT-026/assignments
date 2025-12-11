SELECT p.ID AS ProductID, p.ProductName, od.Quantity
FROM Product p
JOIN OrderDetails od ON p.ID = od.ProductID
WHERE od.Quantity = (SELECT MAX(Quantity) FROM OrderDetails);