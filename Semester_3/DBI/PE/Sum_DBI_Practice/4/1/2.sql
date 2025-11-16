SELECT s.SupplierID, s.SupplierName, s.SupplierCategoryID,
	   s.DeliveryMethod, s.DeliveryCity
FROM Suppliers s
WHERE SupplierCategoryID = 2