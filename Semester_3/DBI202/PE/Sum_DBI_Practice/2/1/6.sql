WITH countProduct AS (
	SELECT psc.SubcategoryID, COUNT(p.ProductID) AS NumberOfProducts FROM ProductSubcategory psc
	JOIN Product p ON p.SubcategoryID = psc.SubcategoryID
	GROUP BY psc.SubcategoryID
)

SELECT psc.SubcategoryID, psc.Name, psc.Category, cp.NumberOfProducts FROM ProductSubcategory psc
JOIN countProduct cp ON cp.SubcategoryID = psc.SubcategoryID
WHERE cp.NumberOfProducts IN (
	SELECT MIN(cp1.NumberOfProducts) FROM countProduct cp1
)