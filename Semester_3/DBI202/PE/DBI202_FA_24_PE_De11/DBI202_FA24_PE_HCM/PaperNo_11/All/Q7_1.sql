WITH Sales2024 AS (
    SELECT
        CustomerID,
        AVG(TotalAmount) AS Avg2024
    FROM
        Orders
    WHERE
        Status = 'Completed'
        AND YEAR(OrderTime) = 2024
    GROUP BY
        CustomerID
),

Sales2025 AS (
    SELECT
        CustomerID,
        AVG(TotalAmount) AS Avg2025
    FROM
        Orders
    WHERE
        Status = 'Completed'
        AND YEAR(OrderTime) = 2025
    GROUP BY
        CustomerID
),

Calculation AS (
    SELECT
        S2024.CustomerID,
        S2024.Avg2024,
        S2025.Avg2025,
        ((S2025.Avg2025 - S2024.Avg2024) / S2024.Avg2024) * 100 AS PctChange
    FROM
        Sales2024 AS S2024
    INNER JOIN
        Sales2025 AS S2025 ON S2024.CustomerID = S2025.CustomerID
)

SELECT
    C.CustomerID,
    C.FullName,
    ROUND(CALC.Avg2024, 2) AS AvgTotalOrderValue2024,
    ROUND(CALC.Avg2025, 2) AS AvgTotalOrderValue2025,
    CAST(ROUND(CALC.PctChange, 2) AS VARCHAR(50)) + '%' AS PercentageChange
FROM
    Calculation AS CALC
JOIN
    Customers AS C ON C.CustomerID = CALC.CustomerID
ORDER BY
    C.CustomerID;