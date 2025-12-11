SELECT p.SubCategoryID, sc.SubCategoryName, COUNT(p.ID) AS NumberOfProduct 
FROM SubCategory sc
JOIN Product p ON p.SubCategoryID = sc.ID
GROUP BY p.SubCategoryID, sc.SubCategoryName
HAVING COUNT(p.ID) > 100
ORDER BY COUNT(p.ID) DESC