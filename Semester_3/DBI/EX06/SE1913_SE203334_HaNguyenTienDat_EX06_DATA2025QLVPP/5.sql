-- 5. Sản phẩm được mua với số lượng nhiều nhất trên tất cả hóa đơn.
-- Hiển thị mã sản phẩm, tên sản phẩm, và tổng số lượng đã bán.
-- MaSP, TenSP, TongSLBan

-- cach 1
SELECT TOP 1 WITH TIES
	sp.MASP, sp.TENSP, SUM(cthd.SL) AS TongSLBan FROM SANPHAM sp
JOIN CTHD cthd ON sp.MASP = cthd.MASP
GROUP BY sp.MASP, sp.TENSP
ORDER BY TongSLBan Desc

-- cach 2: dung CTE
WITH TongSoLuong AS (
	SELECT sp.MASP, TENSP, SUM(cthd.SL) AS TongSLBan 
	FROM SANPHAM sp
	JOIN CTHD cthd ON cthd.MASP = sp.MASP
	GROUP BY sp.MASP, TENSP
)

SELECT MASP, TENSP, TongSLBan
FROM TongSoLuong
WHERE TongSLBan = (SELECT MAX(TongSLBan) FROM TongSoLuong)