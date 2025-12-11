-- cach 1
SELECT p.ProductID, p.ProductName, COUNT(pol.PurchaseOrderID) AS NumberOfPurchaseOrders 
FROM Products p
JOIN PurchaseOrderLines pol ON pol.ProductID = p.ProductID
GROUP BY p.ProductID, p.ProductName
HAVING COUNT(pol.PurchaseOrderID) >= ALL (
	SELECT COUNT(pol2.PurchaseOrderID) FROM Products p2
	JOIN PurchaseOrderLines pol2 ON pol2.ProductID = p2.ProductID
	GROUP BY p2.ProductID
)

-- cach 2
SELECT p.ProductID, p.ProductName, COUNT(pol.PurchaseOrderID) AS NumberOfPurchaseOrders
FROM Products p
JOIN PurchaseOrderLines pol ON pol.ProductID = p.ProductID
GROUP BY p.ProductID, p.ProductName
HAVING COUNT(pol.PurchaseOrderID) = (
	SELECT MAX(OrderCounts.Total)
	FROM (
		SELECT COUNT(pol2.PurchaseOrderID) AS Total
		FROM PurchaseOrderLines pol2
		GROUP BY pol2.ProductID
	) AS OrderCounts
)