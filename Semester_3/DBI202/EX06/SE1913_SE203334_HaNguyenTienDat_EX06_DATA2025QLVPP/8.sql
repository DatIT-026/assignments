-- 8. Tìm khách hàng có hóa đơn đầu tiên được ghi nhận.
-- Hiển thị mã khách hàng, họ tên khách hàng, và ngày đăng ký hóa đơn đầu tiên.
-- MaKH, HoTen, NgayDangKy

-- dung subquery
SELECT DISTINCT kh.MAKH, kh.HOTEN, kh.NGAYDK FROM KHACHHANG kh
JOIN HOADON hd ON hd.MAKH = kh.MAKH
WHERE hd.NGHD = (SELECT MIN(NGHD) FROM HOADON)

-- dung SELECT TOP n WITH TIES
SELECT TOP 1 WITH TIES
    kh.MAKH, kh.HOTEN, kh.NGAYDK
FROM KHACHHANG kh
JOIN HOADON hd ON kh.MAKH = hd.MAKH
ORDER BY hd.NGHD ASC; -- ASC là mặc định (tăng dần, tức là cũ nhất)