-- 1. Danh sách khách hàng có tổng giá trị hóa đơn lớn hơn 50 triệu, gồm:
-- MaKH, HoTenKH,TongGTHD.

SELECT kh.MAKH, HOTEN, SUM(TRIGIA) AS TongGTHD FROM KHACHHANG kh
JOIN HOADON hd ON kh.MAKH = hd.MAKH
GROUP BY kh.MAKH, HOTEN
HAVING SUM(TRIGIA) > 50000000
