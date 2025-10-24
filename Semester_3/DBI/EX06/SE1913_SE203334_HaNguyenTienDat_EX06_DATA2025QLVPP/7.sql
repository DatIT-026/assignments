-- 7. Danh sách sản phẩm không xuất hiện trong bất kỳ hóa đơn nào.
-- Hiển thị mã sản phẩm và tên sản phẩm: MaSP, TenSP

-- cach 1: dung LEFT JOIN
SELECT sp.MASP, sp.TENSP FROM SANPHAM sp
LEFT JOIN CTHD cthd ON cthd.MASP = sp.MASP
WHERE cthd.MASP IS NULL
-- WHERE cthd.SOHD IS NULL cung duoc

-- cach 2: dung NOT IN
SELECT sp.MASP, sp.TENSP FROM SANPHAM sp
WHERE sp.MASP NOT IN (SELECT cthd.MASP FROM CTHD cthd);