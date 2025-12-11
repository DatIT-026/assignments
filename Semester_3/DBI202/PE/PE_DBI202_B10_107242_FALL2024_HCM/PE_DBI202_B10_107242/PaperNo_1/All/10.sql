CREATE PROCEDURE proc_Staffs
    -- 1. Khai báo các tham số đầu vào
    @StaffID INT,
    @NewPhone VARCHAR(15)
AS
BEGIN
    -- 2. Thân của procedure: câu lệnh UPDATE
    UPDATE Staffs
    SET phone = @NewPhone
    WHERE StaffID = @StaffID;
END;