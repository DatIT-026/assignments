WITH Top5HighestPrice AS (
    SELECT TOP 5 p.ID, p.UnitPrice
    FROM Product p
    ORDER BY p.UnitPrice DESC
),
Top5LowestPrice AS (
    SELECT TOP 5 p.ID, p.UnitPrice
    FROM Product p
    ORDER BY p.UnitPrice ASC
)

SELECT p.ID, p.ProductName, p.UnitPrice, p.SubCategoryID
FROM Product p
JOIN Top5HighestPrice tp ON tp.ID = p.ID
WHERE p.UnitPrice = tp.UnitPrice

UNION ALL

SELECT p.ID, p.ProductName, p.UnitPrice, p.SubCategoryID
FROM Product p
JOIN Top5LowestPrice tp ON tp.ID = p.ID
WHERE p.UnitPrice = tp.UnitPrice

ORDER BY p.UnitPrice DESC;
