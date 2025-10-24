-- 4. Tạo view có tên vw_CTPNHAP_VT_PN_DH bao gồm các thông tin sau:
-- số phiếu nhập hàng, ngày nhập hàng, số đơn đặt hàng, mã nhà cung cấp, mã
-- vật tư, tên vật tư, số lượng nhập, đơn giá nhập, thành tiền nhập.

CREATE VIEW vw_CTPNHAP_VT_PN_DH AS
SELECT ct.SoPN, pn.NgayNhap, pn.SoDH, dh.MaNCC, ct.MaVT, vt.TenVT, ct.SLNhap, ct.DGNhap,
    (ct.SLNhap * ct.DGNhap) AS ThanhTienNhap
FROM CTPHIEUNHAP ct
JOIN VATTU vt ON ct.MaVT = vt.MaVT
JOIN PHIEUNHAP pn ON ct.SoPN = pn.SoPN
JOIN DONDATHANG dh ON pn.SoDH = dh.SoDH;