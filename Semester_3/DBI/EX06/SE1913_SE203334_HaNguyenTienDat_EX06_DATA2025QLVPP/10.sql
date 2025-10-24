-- 10. Danh sách các sản phẩm có giá lớn hơn giá trung bình.
-- Hiển thị mã sản phẩm, tên sản phẩm, và giá.
-- MaSP, TenSP, Gia

SELECT MASP, TENSP, GIA FROM SANPHAM
WHERE GIA > (SELECT AVG(GIA) FROM SANPHAM)