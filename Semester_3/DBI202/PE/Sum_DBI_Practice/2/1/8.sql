CREATE PROCEDURE proc_product_location
    @productId int,
    @numberOfLocation int OUTPUT
AS
BEGIN
    SELECT @numberOfLocation = COUNT(DISTINCT LocationID)
    FROM ProductInventory
    WHERE ProductID = @productId;
END;

-- test case
DECLARE @x int;
EXEC proc_product_location 1, @x output
SELECT @x AS SoLuongDiaDiem;