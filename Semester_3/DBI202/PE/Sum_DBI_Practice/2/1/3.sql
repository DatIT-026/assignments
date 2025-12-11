SELECT ProductID, Price, StartDate, EndDate
FROM ProductPriceHistory
WHERE YEAR(EndDate) = 2003 AND Price < 100
ORDER BY Price DESC