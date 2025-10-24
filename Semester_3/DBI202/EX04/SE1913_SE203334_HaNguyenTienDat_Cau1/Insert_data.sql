﻿INSERT INTO CHINHANH (MSCN, TENCN) VALUES ('01', N'Quận 1');
INSERT INTO CHINHANH (MSCN, TENCN) VALUES ('02', N'Quận 5');
INSERT INTO CHINHANH (MSCN, TENCN) VALUES ('03', N'Bình Thạnh');

INSERT INTO KYNANG (MSKN, TENKN) VALUES ('01', N'Word');
INSERT INTO KYNANG (MSKN, TENKN) VALUES ('02', N'Excel');
INSERT INTO KYNANG (MSKN, TENKN) VALUES ('03', N'Access');
INSERT INTO KYNANG (MSKN, TENKN) VALUES ('04', N'Power Point');
INSERT INTO KYNANG (MSKN, TENKN) VALUES ('05', N'SPSS');

INSERT INTO NHANVIEN (MSNV, HO, TEN, NGAYSINH, NGAYVAOLAM, MSCN) VALUES
('0001', N'Lê Văn', N'Minh', '1960-06-10', '1986-05-02', '01'),
('0002', N'Nguyễn Thị', N'Mai', '1970-04-20', '2001-07-04', '01'),
('0003', N'Lê Anh', N'Tuấn', '1975-06-25', '1982-09-01', '02'),
('0004', N'Vương Tuấn', N'Vũ', '1960-03-25', '1986-01-12', '02'),
('0005', N'Lý Anh', N'Hân', '1980-12-01', '2004-05-15', '02'),
('0006', N'Phan Lê', N'Tuấn', '1976-06-04', '2002-10-25', '03'),
('0007', N'Lê Tuân', N'Tú', '1975-08-15', '2000-08-15', '03');

INSERT INTO NV_KN (MSNV, MSKN, MucDo) VALUES
('0001', '01', 2),
('0001', '02', 1),
('0002', '01', 2),
('0002', '03', 2),
('0003', '02', 1),
('0003', '03', 2),
('0004', '01', 5),
('0004', '02', 4),
('0004', '03', 1),
('0005', '02', 4),
('0005', '04', 4),
('0006', '05', 4),
('0006', '02', 4),
('0006', '03', 2),
('0007', '03', 4),
('0007', '04', 3);