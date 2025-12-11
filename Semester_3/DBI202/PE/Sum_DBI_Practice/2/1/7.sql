WITH RelevantModels AS (
    SELECT ModelID, Name AS ModelName FROM ProductModel
    WHERE Name LIKE 'HL Mountain%'
),
ProductLocationCounts AS (
    SELECT ProductID, COUNT(DISTINCT LocationID) AS NumberOfLocations
    FROM ProductInventory
    GROUP BY ProductID
),
ModelProductLocationsRanked AS (
    SELECT rm.ModelID, rm.ModelName, p.ProductID, p.Name AS ProductName,
        ISNULL(plc.NumberOfLocations, 0) AS NumberOfLocations,
        RANK() OVER (PARTITION BY rm.ModelID ORDER BY ISNULL(plc.NumberOfLocations, 0) DESC) AS LocationRank
    FROM RelevantModels AS rm
    LEFT JOIN Product AS p ON rm.ModelID = p.ModelID
    LEFT JOIN ProductLocationCounts AS plc ON p.ProductID = plc.ProductID
)

SELECT ModelID, ModelName, ProductID, ProductName, NumberOfLocations
FROM ModelProductLocationsRanked
WHERE LocationRank = 1
ORDER BY ModelID;