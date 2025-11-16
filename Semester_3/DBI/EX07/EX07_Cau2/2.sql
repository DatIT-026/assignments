-- Câu 2. Tạo SP cho biết tổng tiền xuất của vật tư với mã vật tư là tham số vào.
CREATE PROCEDURE sp_TongTienXuatVatTu
    @MaVT VARCHAR(5)
AS
BEGIN
	-- Su dung CAST de tranh loi tran so khi nhan
    SELECT SUM(CAST(SoLuong AS BIGINT) * DGXuat) AS TongTienXuat
    FROM CTPHIEUXUAT
    WHERE MaVT = @MaVT;
END;