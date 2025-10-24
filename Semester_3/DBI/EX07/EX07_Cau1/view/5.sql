-- 5. Tạo view có tên vw_CTPNHAP_loc bao gồm các thông tin sau: 
-- số phiếu nhập hàng, mã vật tư, số lượng nhập, đơn giá nhập, thành tiền nhập.
-- Và chỉ liệt kê các chi tiết nhập có số lượng nhập > 5.

CREATE VIEW vw_CTPNHAP_loc AS
SELECT SoPN, MaVT, SLNhap, DGNhap,
    (SLNhap * DGNhap) AS ThanhTienNhap
FROM CTPHIEUNHAP
WHERE SLNhap > 5;