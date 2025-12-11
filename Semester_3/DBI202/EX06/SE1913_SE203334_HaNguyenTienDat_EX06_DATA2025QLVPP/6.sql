-- 6. Danh sách khách hàng sinh trước năm 2001.
-- Hiển thị mã khách hàng, họ tên, và ngày sinh của khách hàng
-- MaKH, HoTen, NgaySinhKH

SELECT kh.MAKH, kh.HOTEN, kh.NGSINH FROM KHACHHANG kh
WHERE YEAR(kh.NGSINH) < 2001