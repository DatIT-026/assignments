SELECT p.ProductID, p.Name AS ProductName, p.Price,
	   pm.Name AS ModelName, ps.Name AS SubCategoryName,
	   ps.Category
FROM Product p
LEFT JOIN ProductModel pm ON p.ModelID = pm.ModelID 
LEFT JOIN ProductSubcategory ps ON ps.SubcategoryID = p.SubcategoryID
WHERE Price < 100 AND Color = 'Black'