-- 9. Danh sách hóa đơn với tổng giá trị cao nhất theo từng khách hàng
-- Hiển thị số hóa đơn, họ tên khách hàng, và trị giá cao nhất của hóa đơn.
-- SoHD, HoTenKH, TriGiaCaoNhat

-- cach 1: dung subquery
SELECT hd.SOHD, kh.HOTEN, hd.TRIGIA AS TriGiaCaoNhat FROM HOADON hd
JOIN KHACHHANG kh ON kh.MAKH = hd.MAKH
WHERE hd.TRIGIA = (
	SELECT MAX(hd1.TRIGIA) FROM HOADON hd1
	WHERE hd1.MAKH = hd.MAKH
)

-- cach 2: dung CTE
-- tri gia lon nhat cua hoa don tren tung khach hang
WITH MaxTriGiaPerKhach AS (
	SELECT MAKH, MAX(TRIGIA) AS TriGiaCaoNhat FROM HOADON hd
	GROUP BY MAKH
)

SELECT hd.SOHD, kh.HOTEN, hd.TRIGIA AS TriGiaCaoNhat FROM HOADON hd
JOIN KHACHHANG kh ON kh.MAKH = hd.MAKH
-- gop bang phai cung ma khach hang VA phai bang gia tri cao nhat
JOIN MaxTriGiaPerKhach cte ON hd.MAKH = cte.MAKH AND hd.TRIGIA = cte.TriGiaCaoNhat