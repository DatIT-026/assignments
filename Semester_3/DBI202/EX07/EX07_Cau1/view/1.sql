-- 1. Tạo view có tên vw_CTPNHAP bao gồm các thông tin sau: số phiếu
-- nhập hàng, mã vật tư, số lượng nhập, đơn giá nhập, thành tiền nhập.

CREATE VIEW vw_CTPNHAP AS
SELECT SoPN, MaVT, SLNhap, DGNhap, (SLNhap * DGNhap) AS ThanhTienNhap
FROM CTPHIEUNHAP;