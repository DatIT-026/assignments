SELECT sc.SupplierCategoryID, sc.SupplierCategoryName,
	   COUNT(DISTINCT s.SupplierID) AS NumberOfSuppliers, 
	   COUNT(DISTINCT po.PurchaseOrderID) AS NumberOfPurchaseOrders
FROM SupplierCategories sc
LEFT JOIN Suppliers s ON s.SupplierCategoryID = sc.SupplierCategoryID
LEFT JOIN PurchaseOrders po ON po.SupplierID = s.SupplierID
GROUP BY sc.SupplierCategoryID, sc.SupplierCategoryName
ORDER BY NumberOfPurchaseOrders DESC, sc.SupplierCategoryName