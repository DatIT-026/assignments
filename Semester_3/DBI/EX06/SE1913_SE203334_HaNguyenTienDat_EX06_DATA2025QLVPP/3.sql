-- 3. Danh sách nhân viên đã xử lý hơn 5 hóa đơn.
-- Hiển thị mã nhân viên, họ tên nhân viên, và số lượng hóa đơn đã xử lý. 
-- MaNV, HoTenNV,SLHD

SELECT nv.MANV, HOTEN, COUNT(hd.SOHD) AS SLHD FROM NHANVIEN nv
JOIN HOADON hd ON nv.MANV = hd.MANV
GROUP BY nv.MANV, HOTEN
HAVING COUNT(hd.SOHD) > 5