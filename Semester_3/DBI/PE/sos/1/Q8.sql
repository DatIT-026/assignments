CREATE PROCEDURE proc_SumQuantityProduct (
	@Store_id INT,
	@SumQuantity DECIMAL(10,2) OUTPUT
)
AS
BEGIN

SELECT @SumQuantity = SUM(stock.quantity) FROM stores store
JOIN stocks stock ON stock.store_id = store.store_id
WHERE store.store_id = @Store_id

END