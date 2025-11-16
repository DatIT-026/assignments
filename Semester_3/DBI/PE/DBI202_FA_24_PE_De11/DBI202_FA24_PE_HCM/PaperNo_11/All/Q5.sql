SELECT
    C.CustomerID,
    C.FullName,
    COUNT(CASE WHEN O.Status = 'Completed' AND YEAR(O.OrderTime) = 2025
        THEN O.OrderID END) AS NumberOfOrders,
    SUM(CASE WHEN O.Status = 'Completed' AND YEAR(O.OrderTime) = 2025
        THEN O.TotalAmount ELSE 0 END) AS TotalAmount
FROM
    Customers AS C
LEFT JOIN
    Orders AS O ON C.CustomerID = O.CustomerID
WHERE
    C.FullName LIKE N'Nguyễn%' OR C.FullName LIKE N'Bùi%'
GROUP BY
    C.CustomerID,
    C.FullName
ORDER BY
    C.CustomerID;