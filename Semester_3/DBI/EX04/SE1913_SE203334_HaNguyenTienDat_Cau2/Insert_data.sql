INSERT INTO TOSX (MaTSX, TenTSX) VALUES
('TS01', N'Tổ 1'),
('TS02', N'Tổ 2');

INSERT INTO SANPHAM (MASP, TENSP, DVT, TIENCONG) VALUES
('SP001', N'Nồi đất', N'cái', 10000),
('SP002', N'Chén', N'cái', 2000),
('SP003', N'Bình gốm nhỏ', N'cái', 20000),
('SP004', N'Bình gốm lớn', N'cái', 25000);

INSERT INTO CONGNHAN (MACN, HO, TEN, PHAI, NGAYSINH, MATSX) VALUES
('CN001', N'Nguyễn Trường', N'An', N'Nam', '1981-05-12', 'TS01'),
('CN002', N'Lê Thị Hồng', N'Gấm', N'Nữ', '1980-06-04', 'TS01'),
('CN003', N'Nguyên Công', N'Thành', N'Nam', '1981-05-04', 'TS02'),
('CN004', N'Võ Hữu', N'Hạnh', N'Nam', '1980-02-15', 'TS02'),
('CN005', N'Lý Thanh', N'Hân', N'Nữ', '1981-12-03', 'TS01');

INSERT INTO SANXUAT (MACN, MASP, NGAY, SOLUONG) VALUES 
('CN001', 'SP001', '2007-02-01', 10),
('CN002', 'SP001', '2007-02-01', 5),
('CN003', 'SP002', '2007-01-10', 50),
('CN004', 'SP003', '2007-01-12', 10),
('CN005', 'SP002', '2007-01-12', 100),
('CN002', 'SP004', '2007-02-13', 10),
('CN001', 'SP003', '2007-02-14', 15),
('CN003', 'SP001', '2007-01-15', 20),
('CN003', 'SP004', '2007-02-14', 15),
('CN004', 'SP002', '2007-01-30', 100),
('CN005', 'SP003', '2007-02-01', 50),
('CN001', 'SP001', '2007-02-20', 30);