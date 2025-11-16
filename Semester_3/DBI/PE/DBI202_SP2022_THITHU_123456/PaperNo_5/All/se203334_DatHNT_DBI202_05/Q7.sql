SELECT ps.Category, ps.Name AS SubcategoryName,
	   COUNT(DISTINCT p.ProductID) AS NumberOfProducts
FROM ProductSubcategory ps
JOIN Product p ON p.SubcategoryID = ps.SubcategoryID
GROUP BY ps.Category, ps.Name
HAVING COUNT(DISTINCT p.ProductID) >= ALL (
	SELECT COUNT(DISTINCT p1.ProductID) FROM Product p1
	JOIN ProductSubcategory ps1 ON ps1.SubcategoryID = p1.SubcategoryID
	WHERE ps1.Category = ps.Category
	GROUP BY ps1.Name
)
ORDER BY NumberOfProducts DESC