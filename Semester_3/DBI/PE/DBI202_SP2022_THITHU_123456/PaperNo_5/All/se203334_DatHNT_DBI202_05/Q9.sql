CREATE TRIGGER tr_insert_Product
ON Product
FOR INSERT
AS
BEGIN
    SELECT i.ProductID, i.Name AS ProductName, i.ModelID,
		   pm.Name AS ModelName
    FROM inserted i
    JOIN ProductModel pm ON i.ModelID = pm.ModelID;
END;