create table Product (
	ProductID int primary key,
	Price decimal(12,2),
	Productname nvarchar(150)
)

create table Category(
	CategoryID int primary key,
	Name nvarchar(100),
	Description nvarchar(200),
	ProductID int,
	foreign key (ProductID) references Product(ProductID)
)

create table Supplier(
	SupplierID varchar(30) primary key,
	SupplierName nvarchar(100),
	Level int
)

create table hasSupplier (
	SupplierID varchar(30),
	ProductID int,
	primary key (ProductID, SupplierID),
	foreign key (ProductID) references Product(ProductID),
	foreign key (SupplierID) references Supplier(SupplierID)
)