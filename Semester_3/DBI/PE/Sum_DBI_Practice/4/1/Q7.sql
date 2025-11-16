SELECT p.ProductID, p.ProductName, p.UnitPrice, p.Color, 
	   pqm.TotalOrderedQuantity, pqm.NumberOfPurchaserOrders
FROM Products p
LEFT JOIN (
		SELECT pol.ProductID, SUM(pol.OrderedQuantity) AS TotalOrderedQuantity, 
		   COUNT(DISTINCT pol.PurchaseOrderID) AS NumberOfPurchaserOrders
		FROM PurchaseOrderLines pol
		JOIN PurchaseOrders po ON pol.PurchaseOrderID = po.PurchaseOrderID
		WHERE MONTH(po.OrderDate) = 5 AND YEAR(po.OrderDate) = 2013
		GROUP BY ProductID
	) AS pqm ON p.ProductID = pqm.ProductID
WHERE p.UnitPrice = 18 AND p.Color IN ('black', 'white')
ORDER BY pqm.TotalOrderedQuantity DESC, p.ProductName
