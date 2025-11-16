SELECT ps.SubcategoryID, ps.Name AS SubCategoryName,
	   ps.Category, COUNT(p.ProductID) AS NumberOFProducts
FROM ProductSubcategory ps
JOIN Product p ON p.SubcategoryID = ps.SubcategoryID
GROUP BY ps.SubcategoryID, ps.Name, ps.Category
ORDER BY ps.Category, NumberOFProducts DESC