-- Câu 1. Tạo Stored procedure (SP) cho biết tổng số lượng cuối của vật tư với mã vật tư là tham số vào.
CREATE PROCEDURE sp_TongSoLuongCuoi
    @MaVT VARCHAR(5)
AS
BEGIN
    SELECT SUM(SLCuoi) AS TongSoLuongCuoi
    FROM TONKHO
    WHERE MaVT = @MaVT;
END;