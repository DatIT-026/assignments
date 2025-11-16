-- 1. TRUY VẤN LỰA CHỌN TRÊN NHIỀU BẢNG

-- a) Hiển thị MSNV, HoTen (Ho + Ten as HoTen), số năm làm việc (SoNamLamViec).
-- Tính số năm làm việc dựa trên ngày hiện tại (GETDATE()) và ngày vào làm (NGAYVAOLAM).
SELECT MSNV, HO + ' ' + TEN AS HoTen, DATEDIFF(YEAR, NGAYVAOLAM, GETDATE()) AS SoNamLamViec FROM NHANVIEN;

-- b) Liệt kê các thông tin về nhân viên: HoTen, NgaySinh, NgayVaoLam, TenCN (sắp xếp theo tên chi nhánh).
-- Nối bảng NHANVIEN và CHINHANH.
SELECT NV.HO + ' ' + NV.TEN AS HoTen, NV.NgaySinh, NV.NgayVaoLam, CN.TenCN FROM NHANVIEN NV
JOIN CHINHANH CN ON NV.MSCN = CN.MSCN ORDER BY CN.TenCN;

-- c) Liệt kê các nhân viên (HoTen, TenKN, MucDo) của những nhân viên biết sử dụng 'Word'.
-- Nối 3 bảng: NHANVIEN, NV_KN (kỹ năng của nhân viên), và KYNANG.
SELECT NV.HO + ' ' + NV.TEN AS HoTen, KN.TenKN, NV_KN.MucDo FROM NHANVIEN NV
JOIN NV_KN ON NV.MSNV = NV_KN.MSNV
JOIN KYNANG KN ON NV_KN.MSKN = KN.MSKN
WHERE KN.TENKN = N'Word';

-- d) Liệt kê các kỹ năng (TenKN, MucDo) mà nhân viên Lê Anh Tuân biết sử dụng.
-- Nối 3 bảng và lọc theo tên nhân viên cụ thể.
SELECT KN.TENKN, NV_KN.MucDo FROM NHANVIEN NV
JOIN NV_KN ON NV.MSNV = NV_KN.MSNV
JOIN KYNANG KN ON NV_KN.MSKN = KN.MSKN
WHERE NV.HO = N'Lê Anh' AND NV.TEN = N'Tuấn';

-- 2. TRUY VẤN LỒNG (SUBQUERY)

-- a) Liệt kê MANV, HoTen, MSCN, TenCN của các nhân viên có mức độ thành thạo về 'Excel' cao nhất trong công ty.
SELECT NV.MSNV, NV.HO + ' ' + NV.TEN AS HoTen, CN.MSCN, CN.TENCN FROM NHANVIEN NV
JOIN CHINHANH CN ON NV.MSCN = CN.MSCN
JOIN NV_KN ON NV.MSNV = NV_KN.MSNV
JOIN KYNANG KN ON NV_KN.MSKN = KN.MSKN
WHERE KN.TENKN = N'Excel' AND NV_KN.MucDo = (
        -- Truy vấn lồng: Tìm mức độ (MucDo) cao nhất của kỹ năng 'Excel'
        SELECT MAX(NVKN_Sub.MucDo) FROM NV_KN NVKN_Sub
        JOIN KYNANG KN_Sub ON NVKN_Sub.MSKN = KN_Sub.MSKN
        WHERE KN_Sub.TENKN = N'Excel'
    );

-- b) Liệt kê MANV, HoTen, TenCN của các nhân viên vừa biết Word vừa biết Excel (dùng truy vấn lồng).
SELECT NV.MSNV, NV.HO + ' ' + NV.TEN AS HoTen, CN.TENCN FROM NHANVIEN NV
JOIN CHINHANH CN ON NV.MSCN = CN.MSCN
WHERE NV.MSNV IN (
        -- Lấy ra MSNV của những người biết 'Word'
        SELECT MSNV FROM NV_KN
        WHERE MSKN = (SELECT MSKN FROM KYNANG WHERE TENKN = N'Word')
	) AND NV.MSNV IN (
        -- Lấy ra MSNV của những người biết 'Excel'
        SELECT MSNV FROM NV_KN
        WHERE MSKN = (SELECT MSKN FROM KYNANG WHERE TENKN = N'Excel')
    );

-- c) Với từng kỹ năng, hãy liệt kê các thông tin (...) của những nhân viên thành thạo kỹ năng đó nhất.
SELECT NV.MSNV, NV.HO + ' ' + NV.TEN AS HoTen, CN.TENCN, KN.TENKN, NV_KN.MucDo FROM NHANVIEN NV
JOIN CHINHANH CN ON NV.MSCN = CN.MSCN
JOIN NV_KN ON NV.MSNV = NV_KN.MSNV
JOIN KYNANG KN ON NV_KN.MSKN = KN.MSKN
WHERE NV_KN.MucDo = (
    SELECT MAX(NVKN_Sub.MucDo) FROM NV_KN NVKN_Sub WHERE NVKN_Sub.MSKN = KN.MSKN
);

-- d) Liệt kê các chi nhánh (MSCN, TenCN) mà mọi nhân viên trong chi nhánh đó đều biết 'Word'.
SELECT CN.MSCN, CN.TENCN FROM CHINHANH CN	
WHERE CN.MSCN NOT IN ( -- Loại bỏ các chi nhánh có NV KHÔNG biết Word
        SELECT NV.MSCN FROM NHANVIEN NV
        WHERE NV.MSNV NOT IN ( -- Tìm NV không biết 'Word'
            SELECT MSNV FROM NV_KN WHERE MSKN = (SELECT MSKN FROM KYNANG WHERE TENKN = N'Word')
        )
);

-- 3. TRUY VẤN GOM NHÓM DỮ LIỆU (GROUP BY)

-- a) Với mỗi chi nhánh, hãy cho biết TenCN, SoNV (số nhân viên của chi nhánh đó).
SELECT CN.TenCN, COUNT(NV.MSNV) AS SoNV FROM CHINHANH CN
JOIN NHANVIEN NV ON CN.MSCN = NV.MSCN
GROUP BY CN.TenCN;

-- b) Với mỗi kỹ năng, hãy cho biết TenKN, SoNguoiDung (số nhân viên biết sử dụng kỹ năng đó).
SELECT KN.TENKN, COUNT(NVKN.MSNV) AS SoNguoiDung FROM KYNANG KN
JOIN NV_KN NVKN ON KN.MSKN = NVKN.MSKN
GROUP BY KN.TENKN;

-- c) Cho biết TenKN có từ 3 nhân viên trong công ty sử dụng trở lên.
-- Dùng HAVING để lọc kết quả sau khi đã GROUP BY.
SELECT KN.TENKN FROM KYNANG KN
JOIN NV_KN NVKN ON KN.MSKN = NVKN.MSKN GROUP BY KN.TENKN
HAVING COUNT(NVKN.MSNV) >= 3;

-- d) Cho biết TenCN có nhiều nhân viên nhất.
-- Dùng TOP 1 WITH TIES để lấy chi nhánh có COUNT lớn nhất (kể cả trường hợp có nhiều chi nhánh bằng nhau).
SELECT TOP 1 WITH TIES CN.TENCN FROM CHINHANH CN
JOIN NHANVIEN NV ON CN.MSCN = NV.MSCN GROUP BY CN.TENCN
ORDER BY COUNT(NV.MSNV) DESC; -- Sắp xếp giảm dần để lấy giá trị lớn nhất

-- e) Cho biết TenCN có ít nhân viên nhất.
SELECT TOP 1 WITH TIES CN.TENCN FROM CHINHANH CN
JOIN NHANVIEN NV ON CN.MSCN = NV.MSCN GROUP BY CN.TENCN
ORDER BY COUNT(NV.MSNV) ASC; -- Sắp xếp tăng dần để lấy giá trị nhỏ nhất

-- f) Với mỗi nhân viên, hãy cho biết số kỹ năng tin học mà nhân viên đó sử dụng được.
SELECT NV.HO + ' ' + NV.TEN AS HoTen, COUNT(NVKN.MSKN) AS SoKyNang FROM NHANVIEN NV
LEFT JOIN -- Dùng LEFT JOIN để bao gồm cả nhân viên không có kỹ năng nào
    NV_KN NVKN ON NV.MSNV = NVKN.MSNV
GROUP BY NV.MSNV, NV.HO, NV.TEN
ORDER BY SoKyNang DESC;

-- g) Cho biết HoTen, TenCN của nhân viên biết sử dụng nhiều kỹ năng nhất.
SELECT TOP 1 WITH TIES NV.HO + ' ' + NV.TEN AS HoTen, CN.TENCN
    -- Không cần hiển thị COUNT, nhưng cần ORDER BY theo nó
FROM NHANVIEN NV
JOIN CHINHANH CN ON NV.MSCN = CN.MSCN
LEFT JOIN NV_KN NVKN ON NV.MSNV = NVKN.MSNV
GROUP BY NV.MSNV, NV.HO, NV.TEN, CN.TENCN
ORDER BY COUNT(NVKN.MSKN) DESC;