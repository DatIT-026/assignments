CREATE TRIGGER trg_UpdateTotalAmount
ON OrderDetails
AFTER INSERT, UPDATE, DELETE
AS
BEGIN

    UPDATE O
    SET
        O.TotalAmount = (
            SELECT COALESCE(SUM(OD.Quantity * OD.Price), 0)
            FROM OrderDetails AS OD
            WHERE OD.OrderID = O.OrderID
        )
    FROM
        Orders AS O
    WHERE
        O.OrderID IN (
            SELECT OrderID FROM inserted
            UNION
            SELECT OrderID FROM deleted
        );
END;

