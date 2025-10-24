create database QLBanHang
use QLBanHang
go
create table NHACUNGCAP
(
	MaNCC varchar(5) primary key,
	TenNCC nvarchar(50),
	DiaChi nvarchar(50),
	DienThoai varchar(12)
)
create table VATTU
(
MaVT varchar(5) primary key,
TenVT Nvarchar(50),
DonViTinh nvarchar(20),
PhanTram int
)
create table DONDATHANG
(
	SoDH Varchar(5) primary key,
	NgayHD date not null default getdate(),
	MaNCC varchar(5) foreign key (MaNCC) references NhaCungCap(MaNCC)
)
create table CTDATHANG
(
	SoDH varchar(5) not null,
	MaVT varchar(5) not null,	
	SLDat int,
	primary key(SoDH,MaVT),
	foreign key (SoDH) references DONDATHANG(SoDH),
	foreign key (MaVT) references VATTU(MaVT),
)
create table PHIEUNHAP(
	SoPN varchar(5)primary key,
	NgayNhap date default getdate(),
	SoDH varchar(5) foreign key (SoDH) references DONDATHANG(SoDH)

)
create table CTPHIEUNHAP(
	SoPN varchar(5) ,
	MaVT varchar (5),
	SLNhap int ,
	DGNhap int,
	primary key(SoPN,MaVT),
	foreign key (SoPN) references PHIEUNHAP(SoPN),
	foreign key (MaVT) references VATTU(MaVT),

)
create table PHIEUXUAT
(
	SoPX varchar(5) primary key,
	NgayXuat date default getdate(),
	TenKH nvarchar(50)
)

create table CTPHIEUXUAT
(
	SoPX varchar(5),
	MaVT varchar(5),
	SoLuong int ,
	DGXuat int,
	primary key(SoPX,MaVT),
	foreign key (SoPX) references PHIEUXUAT(SoPX),
	foreign key (MaVT) references VATTU(MaVT),

)
create table TONKHO
(
	NamThang varchar(10),
	MaVT varchar(5),
	SLDau int,
	TongSLNhap int,
	TongSLXuat int,
	SLCuoi int,
	primary key(NamThang,MaVT),
	foreign key (MaVT) references VATTU(MaVT),
)


--Chen DL

insert into NHACUNGCAP values('C01',N'Lê Minh Trí',N'54 Hậu Giang Q6 HCM','8781024')
insert into NHACUNGCAP values('C02',N'Trần Minh Thạch',N'145 Hùng Vương Mỹ Tho','7698154')
insert into NHACUNGCAP values('C03',N'Hồng Phương',N'154/85 Lê Lai Q1 HCM','9600125')
insert into NHACUNGCAP values('C04',N'Nhật Thắng',N'198/40 Hương Lộ 14 QTB HCM','8757757')
insert into NHACUNGCAP values('C05',N'Lưu Nguyệt Quế',N'178 Nguyễn Văn Luông Đà Lạt','7964251')
insert into NHACUNGCAP values('C07',N'Cao Minh Trung',N'125 Lê Quang Sung Nha Trang','Chưa có')
select * from  NHACUNGCAP
go
insert into VATTU values('DD01',N'Đầu DVD Hitachi 1 đĩa',N'Bộ',40)					
insert into VATTU values('DD02',N'Đầu DVD Hitachi 3 đĩa',N'Bộ',40)					
insert into VATTU values('TL15',N'Tủ lạnh Sanyo 150 lit',N'Cái',25)					
insert into VATTU values('TL90',N'Tủ lạnh Sanyo 90 lit',N'Cái',20)					
insert into VATTU values('TV14',N'Tivi Sony 14 inches',N'Cái',15)					
insert into VATTU values('TV21',N'Tivi Sony 21 inches',N'Cái',10)					
insert into VATTU values('TV29',N'Tivi Sony 29 inches',N'Cái',10)					
insert into VATTU values('VD01',N'Đầu VCD Sony 1 đĩa',N'Bộ',30)					
insert into VATTU values('VD02',N'Đầu VCD Sony 3 đĩa',N'Bộ',30)
go
select * from  VATTU
insert into DONDATHANG values ('D001','2015-01-07','C03')			
insert into DONDATHANG values ('D002','1930-01-07','C01')			
insert into DONDATHANG values ('D003','2010-02-07','C02')			
insert into DONDATHANG values ('D004','2017-02-07','C05')			
insert into DONDATHANG values ('D005','2003-01-07','C02')			
insert into DONDATHANG values ('D006','2003-12-07','C05')	
go
select * from  DONDATHANG
insert into CTDATHANG values ('D001','DD01',10)
insert into CTDATHANG values ('D001','DD02',15)
insert into CTDATHANG values ('D002','VD02',30)
insert into CTDATHANG values ('D003','TV14',10)
insert into CTDATHANG values ('D003','TV29',20)
insert into CTDATHANG values ('D004','TL90',10)
insert into CTDATHANG values ('D005','TV14',10)
insert into CTDATHANG values ('D005','TV29',20)
insert into CTDATHANG values ('D006','TV14',10)
insert into CTDATHANG values ('D006','TV29',20)
insert into CTDATHANG values ('D006','VD01',20)
go
select * from  CTDATHANG
insert into PHIEUNHAP values('N001','2017-01-07','D001')				
insert into PHIEUNHAP values('N002','2020-01-07','D001')				
insert into PHIEUNHAP values('N003','1931-01-07','D002')				
insert into PHIEUNHAP values('N004','2015-02-07','D003')
GO
select * from PHIEUNHAP
insert into CTPHIEUNHAP values ('N001','DD01',8,2500000)		
insert into CTPHIEUNHAP values ('N001','DD02',10,3500000)		
insert into CTPHIEUNHAP values ('N002','DD01',2,2500000)		
insert into CTPHIEUNHAP values ('N002','DD02',5,3500000)		
insert into CTPHIEUNHAP values ('N003','VD02',30,2500000)		
insert into CTPHIEUNHAP values ('N004','TV14',5,2500000)		
insert into CTPHIEUNHAP values ('N004','TV29',12,3500000)
GO
select * from CTPHIEUNHAP

insert into PHIEUXUAT values('X001','2017-01-07',N'Nguyễn Ngọc Phương Nhi') 	
insert into PHIEUXUAT values('X002','2025-01-07',N'Nguyễn Hồng Phương') 	
insert into PHIEUXUAT values('X003','1931-01-07',N'Nguyễn Tuấn Tú') 	
GO
select * from PHIEUXUAT
insert into CTPHIEUXUAT values ('X001','DD01',2,3500000)				
insert into CTPHIEUXUAT values ('X002','DD01',1,3500000)				
insert into CTPHIEUXUAT values ('X002','DD02',5,4900000)				
insert into CTPHIEUXUAT values ('X003','DD01',3,3500000)				
insert into CTPHIEUXUAT values ('X003','DD02',2,4900000)				
insert into CTPHIEUXUAT values ('X003','VD02',10,3250000)	
GO
select * from CTPHIEUXUAT
insert into TONKHO values ('5/2007','DD01',100,500,300,300)					
insert into TONKHO values ('5/2007','DD02',120,470,50,540)					
insert into TONKHO values ('5/2007','TL15',140,440,20,560)					
insert into TONKHO values ('5/2007','TL90',160,410,40,530)					
insert into TONKHO values ('5/2007','TV14',180,380,70,490)					
insert into TONKHO values ('5/2007','TV21',200,350,140,410)					
insert into TONKHO values ('5/2007','TV29',220,320,250,290)	