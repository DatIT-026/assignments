SELECT ProductID, Name, Color, Price, SellEndDate FROM Product
WHERE Cost < 100 AND SellEndDate IS NOT NULL
ORDER BY Cost