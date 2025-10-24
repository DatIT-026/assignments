-- 2. Tìm hóa đơn có chứa sản phẩm được sản xuất tại Việt Nam.
-- Hiển thị số hóa đơn, mã khách hàng, và tổng trị giá của hóa đơn.
-- SoHD, MaKH, TongGTHD

-- cach 1: dung DISTINCT
SELECT DISTINCT hd.SOHD, MaKH, TRIGIA as TongGTHD FROM HOADON hd
JOIN CTHD cthd ON cthd.SOHD = hd.SOHD
JOIN SANPHAM sp ON sp.MASP = cthd.MASP
WHERE sp.NUOCSX = 'VN'

-- cach 2: dung IN
SELECT hd.SOHD, hd.MAKH, hd.TRIGIA AS TongGTHD FROM HOADON hd
WHERE hd.SOHD IN (
        SELECT cthd.SOHD FROM CTHD cthd
        JOIN SANPHAM sp ON cthd.MASP = sp.MASP
        WHERE sp.NUOCSX = 'VN'
	);