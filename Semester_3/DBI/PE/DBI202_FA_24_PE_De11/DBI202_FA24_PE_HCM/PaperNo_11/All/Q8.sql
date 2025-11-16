CREATE PROCEDURE MonthlySalesSummary
    @year_in INT
AS
BEGIN
    WITH AllMonths (MonthNum) AS (
        SELECT 1
        UNION ALL
        SELECT MonthNum + 1
        FROM AllMonths
        WHERE MonthNum < 12
    ),
    MonthlySales AS (
        SELECT
            MONTH(O.OrderTime) AS MonthNum,
            COUNT(DISTINCT O.OrderID) AS NumberOfOrders,
            SUM(OD.Quantity * OD.Price) AS TotalRevenue
        FROM Orders AS O
        JOIN OrderDetails AS OD ON O.OrderID = OD.OrderID
        WHERE
            YEAR(O.OrderTime) = @year_in
            AND O.Status = 'Completed'
        GROUP BY MONTH(O.OrderTime)
    )

    SELECT
        CAST(M.MonthNum AS VARCHAR(2)) + '/' + CAST(@year_in AS VARCHAR(4)) AS Month,
        COALESCE(S.NumberOfOrders, 0) AS NumberOfOrders,
        COALESCE(S.TotalRevenue, 0) AS TotalRevenue
    FROM AllMonths AS M
    LEFT JOIN MonthlySales AS S ON M.MonthNum = S.MonthNum
    ORDER BY M.MonthNum;
END; 


EXECUTE MonthlySalesSummary 2023