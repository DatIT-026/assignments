CREATE TRIGGER tr_insert_car
ON Cars
FOR INSERT
AS
BEGIN
	SELECT i.carID, i.serialNumber, i.model, i.colour, i.year
	FROM inserted i
END;