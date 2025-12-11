CREATE TRIGGER tr_delete_productInventory_location
ON ProductInventory FOR DELETE
AS
BEGIN
	SELECT pivn.ProductID, l.LocationID, l.Name AS LocationName,  
		   pivn.Shelf, pivn.Bin, pivn.Quantity
	FROM ProductInventory pivn
	JOIN Location l ON l.LocationID = pivn.LocationID
END;

-- test case
DELETE FROM ProductInventory
WHERE ProductID = 1 AND LocationID = 1