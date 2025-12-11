CREATE PROCEDURE proc_product_model
    @modelID INT, 
    @numberOfProducts INT OUTPUT
AS
BEGIN
    SELECT @numberOfProducts = COUNT(DISTINCT ProductID)
    FROM Product
    WHERE ModelID = @modelID;
END;