SELECT
    C.CustomerID,
    C.FullName,
    ROUND(AVG(CASE WHEN YEAR(O.OrderTime) = 2024 
		THEN O.TotalAmount END), 2) AS AvgTotalOrderValue2024,
    ROUND(AVG(CASE WHEN YEAR(O.OrderTime) = 2025 
		THEN O.TotalAmount END), 2) AS AvgTotalOrderValue2025,
    CAST(ROUND((
                (AVG(CASE WHEN YEAR(O.OrderTime) = 2025 THEN O.TotalAmount END) -
                 AVG(CASE WHEN YEAR(O.OrderTime) = 2024 THEN O.TotalAmount END)) /
                 AVG(CASE WHEN YEAR(O.OrderTime) = 2024 THEN O.TotalAmount END)
			) * 100,2) 
    AS VARCHAR(50)) + '%' AS PercentageChange
FROM
    Customers AS C
JOIN
    Orders AS O ON C.CustomerID = O.CustomerID
WHERE
    O.Status = 'Completed'
    AND YEAR(O.OrderTime) IN (2024, 2025)
GROUP BY
    C.CustomerID,
    C.FullName
HAVING
    COUNT(DISTINCT YEAR(O.OrderTime)) = 2
ORDER BY
    C.CustomerID;