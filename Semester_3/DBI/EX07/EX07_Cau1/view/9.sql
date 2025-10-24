-- 9. Tạo view có tên vw_CTPXUAT_VT_PX bao gồm các thông tin sau: 
-- số phiếu xuất hàng, tên khách hàng, mã vật tư, tên vật tư, số lượng xuất, đơn giá xuất.

CREATE VIEW vw_CTPXUAT_VT_PX AS
SELECT ct.SoPX, px.TenKH, ct.MaVT, vt.TenVT, ct.SoLuong AS SLXuat, ct.DGXuat
FROM CTPHIEUXUAT ct
JOIN VATTU vt ON ct.MaVT = vt.MaVT
JOIN PHIEUXUAT px ON ct.SoPX = px.SoPX;