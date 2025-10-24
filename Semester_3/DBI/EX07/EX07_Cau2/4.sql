-- Câu 4. Tạo SP dùng để thêm một đơn đặt hàng.
CREATE PROCEDURE sp_ThemDonDatHang
    @SoDH VARCHAR(5),
    @NgayHD DATE,
    @MaNCC VARCHAR(5)
AS
BEGIN
    INSERT INTO DONDATHANG (SoDH, NgayHD, MaNCC)
    VALUES (@SoDH, @NgayHD, @MaNCC);
END;