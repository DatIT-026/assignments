-- Câu 5. Tạo SP dùng để thêm một chi tiết đơn đặt hàng
CREATE PROCEDURE sp_ThemChiTietDonHang
    @SoDH VARCHAR(5),
    @MaVT VARCHAR(5),
    @SLDat INT
AS
BEGIN
    INSERT INTO CTDATHANG (SoDH, MaVT, SLDat)
    VALUES (@SoDH, @MaVT, @SLDat);
END;