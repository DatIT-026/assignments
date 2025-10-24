-- 8. Tạo view có tên vw_CTPXUAT_VT bao gồm các thông tin sau: 
-- số phiếu xuất hàng, mã vật tư, tên vật tư, số lượng xuất, đơn giá xuất.\

CREATE VIEW vw_CTPXUAT_VT AS
SELECT ct.SoPX, ct.MaVT, vt.TenVT, ct.SoLuong AS SLXuat, ct.DGXuat
FROM CTPHIEUXUAT ct
JOIN VATTU vt ON ct.MaVT = vt.MaVT;