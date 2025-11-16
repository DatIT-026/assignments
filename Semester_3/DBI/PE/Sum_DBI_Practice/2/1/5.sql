SELECT pi.ProductID, p.Name, SUM(Quantity) AS TotalQuantity FROM ProductInventory [pi]
JOIN Product p ON p.ProductID = pi.ProductID
GROUP BY pi.ProductID, p.Name
HAVING SUM(Quantity) > 1700
ORDER BY TotalQuantity DESC, p.Name