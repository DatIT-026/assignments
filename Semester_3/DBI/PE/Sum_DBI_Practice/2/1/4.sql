SELECT p.ProductID, p.Name AS ProductName, p.Color, p.SubcategoryID, 
	   ps.Name AS SubCategoryName, ps.Category,
	   pch.StartDate, pch.EndDate, pch.Cost AS HistoryCost
FROM Product p
LEFT JOIN ProductCostHistory pch ON pch.ProductID = p.ProductID
LEFT JOIN ProductSubcategory ps ON ps.SubcategoryID = p.SubcategoryID
WHERE p.Color = 'Black' AND p.Name LIKE 'HL%'