SELECT l.LocationID, l.Name AS LocationName,
    COUNT(DISTINCT pi.ProductID) AS NumberOfProducts
FROM Location l
JOIN ProductInventory pi ON l.LocationID = pi.LocationID
GROUP BY l.LocationID, l.Name
HAVING COUNT(DISTINCT pi.ProductID) <= ALL (
        SELECT COUNT(DISTINCT pI2.ProductID)
        FROM ProductInventory pI2
        GROUP BY pI2.LocationID
    );