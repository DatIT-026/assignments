-- 3. Tạo view có tên vw_CTPNHAP_VT_PN bao gồm các thông tin sau: 
-- số phiếu nhập hàng, ngày nhập hàng, số đơn đặt hàng, mã vật tư, tên vật tư, số
-- lượng nhập, đơn giá nhập, thành tiền nhập.

CREATE VIEW vw_CTPNHAP_VT_PN AS
SELECT ct.SoPN, pn.NgayNhap, pn.SoDH, ct.MaVT, vt.TenVT, ct.SLNhap, ct.DGNhap,
    (ct.SLNhap * ct.DGNhap) AS ThanhTienNhap
FROM CTPHIEUNHAP ct
JOIN VATTU vt ON ct.MaVT = vt.MaVT
JOIN PHIEUNHAP pn ON ct.SoPN = pn.SoPN;