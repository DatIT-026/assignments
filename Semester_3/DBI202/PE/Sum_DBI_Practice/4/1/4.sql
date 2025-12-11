SELECT p.ProductID, p.ProductName, p.SupplierID,
	   s.SupplierName, p.TaxRate, p.UnitPrice, p.TypicalWeightPerUnit
FROM Products p
JOIN Suppliers s ON s.SupplierID = p.SupplierID
WHERE p.TaxRate = 15 AND p.UnitPrice <= 10 
	  AND p.TypicalWeightPerUnit < 0.5
ORDER BY P.SupplierID, p.ProductName