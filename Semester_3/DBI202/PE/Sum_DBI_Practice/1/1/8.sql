CREATE PROCEDURE TotalAmount
	@OrderID nvarchar(255),
	@TotalAmount float OUTPUT
AS
BEGIN
	SET NOCOUNT ON; -- khong anh huong den code goc, chi la tat thong bao
	SELECT @TotalAmount = SUM(o.SalePrice * o.Quantity * (1 - o.Discount)) 
	FROM OrderDetails o
	WHERE OrderID = @OrderID
END;

-- test case
DECLARE @t float
exec TotalAmount 'CA-2014-100006', @t output
print @t