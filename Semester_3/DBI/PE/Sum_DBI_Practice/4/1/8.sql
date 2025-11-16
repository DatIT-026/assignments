CREATE PROC Proc2 
	@purchaseOrderID int, @totalAmount decimal(18,2) OUTPUT
AS
BEGIN
	SELECT @totalAmount = SUM(p.UnitPrice * pol.OrderedQuantity)
	FROM PurchaseOrderLines pol
	JOIN Products p ON p.ProductID = pol.ProductID
	WHERE pol.PurchaseOrderID = @purchaseorderID
END

--
DECLARE @x decimal(18,2)
execute Proc2 1, @x output
SELECT @x as TotalOrderedQuantity