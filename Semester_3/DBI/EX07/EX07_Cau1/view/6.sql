-- 6. Tạo view có tên vw_CTPNHAP_VT_loc bao gồm các thông tin sau: số
-- phiếu nhập hàng, mã vật tư, tên vật tư, số lượng nhập, đơn giá nhập, thành
-- tiền nhập. Và chỉ liệt kê các chi tiết nhập vật tư có đơn vị tính là Bộ.

CREATE VIEW vw_CTPNHAP_VT_loc AS
SELECT ct.SoPN, ct.MaVT, vt.TenVT, ct.SLNhap, ct.DGNhap, 
    (ct.SLNhap * ct.DGNhap) AS ThanhTienNhap
FROM CTPHIEUNHAP ct
JOIN VATTU vt ON ct.MaVT = vt.MaVT
WHERE vt.DonViTinh = N'Bộ';