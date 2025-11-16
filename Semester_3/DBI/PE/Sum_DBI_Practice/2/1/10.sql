UPDATE ProductInventory SET Quantity = 2000 FROM ProductInventory pinv
JOIN Product p ON p.ProductID = pinv.ProductID
WHERE p.ModelID = 33;