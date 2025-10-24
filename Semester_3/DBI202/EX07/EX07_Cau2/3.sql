-- Câu 3. Tạo SP cho biết tổng số lượng đặt theo số đơn hàng với số đơn hàng là tham số vào.
CREATE PROCEDURE sp_TongSoLuongDatTheoDH
    @SoDH VARCHAR(5)
AS
BEGIN
    SELECT SUM(SLDat) AS TongSoLuongDat
    FROM CTDATHANG
    WHERE SoDH = @SoDH;
END;