-- 7. Tạo view có tên vw_CTPXUAT bao gồm các thông tin sau: 
-- số phiếu xuất hàng, mã vật tư, số lượng xuất, đơn giá xuất, thành tiền xuất.

CREATE VIEW vw_CTPXUAT AS
SELECT SoPX, MaVT, SoLuong AS SLXuat, DGXuat, 
    (SoLuong * DGXuat) AS ThanhTienXuat
FROM CTPHIEUXUAT;