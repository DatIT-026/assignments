-- 4. Thông tin chi tiết các hóa đơn có giá trị cao nhất.
-- Hiển thị số hóa đơn, mã khách hàng, mã nhân viên, ngày hóa đơn, và trị giá của hóa đơn 
-- SoHD, MaKH, MaNV, NgayHD, TriGia

SELECT SOHD, MAKH, MANV, NGHD, TRIGIA FROM HOADON
WHERE TRIGIA = (SELECT MAX(TRIGIA) FROM HOADON)