WITH ItemSales2023 AS (
    SELECT
        mi.ItemID,
        mi.ItemName,
        mi.Category,
        COALESCE(SUM(s.Quantity * s.Price), 0) AS TotalAmount
    FROM
        MenuItems AS mi
    LEFT JOIN
        (
            SELECT
                o.OrderID,
                od.ItemID,
                od.Quantity,
                od.Price
            FROM
                Orders AS o
            JOIN
                OrderDetails AS od ON o.OrderID = od.OrderID
            WHERE
                o.Status = 'Completed'
                AND YEAR(o.OrderTime) = 2023
        ) AS s ON mi.ItemID = s.ItemID
    GROUP BY
        mi.ItemID,
        mi.ItemName,
        mi.Category
)

SELECT
    ItemID,
    ItemName,
    Category,
    TotalAmount
FROM
    ItemSales2023
WHERE
    TotalAmount < (SELECT AVG(TotalAmount) FROM ItemSales2023)
ORDER BY TotalAmount ASC, ItemID ASC