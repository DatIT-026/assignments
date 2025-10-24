-- 2. Tạo view có tên vw_CTPNHAP_VT bao gồm các thông tin sau: 
-- số phiếu nhập hàng, mã vật tư, tên vật tư, số lượng nhập, đơn giá nhập, thành tiền nhập.

CREATE VIEW vw_CTPNHAP_VT AS
SELECT ct.SoPN, ct.MaVT, vt.TenVT, ct.SLNhap, ct.DGNhap,
    (ct.SLNhap * ct.DGNhap) AS ThanhTienNhap
FROM CTPHIEUNHAP ct
JOIN VATTU vt ON ct.MaVT = vt.MaVT;