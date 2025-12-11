CREATE TABLE Product (
	ProductID INT PRIMARY KEY,
	Productname NVARCHAR(150),
	Price DECIMAL(12,2)
)

CREATE TABLE Supplier (
	SupplierID VARCHAR(30) PRIMARY KEY,
	SupplierName NVARCHAR(100),
	Level INT
)

CREATE TABLE Category (
	CategoryID INT,
	Name NVARCHAR(100),
	Description NVARCHAR(200),
	
	ProductID INT FOREIGN KEY REFERENCES Product,
	
	PRIMARY KEY (CategoryID, ProductID)
)

CREATE TABLE hasSupplier (
	ProductID INT FOREIGN KEY REFERENCES Product,
	SupplierID VARCHAR(30) FOREIGN KEY REFERENCES Supplier,
	
	PRIMARY KEY (ProductID, SupplierID)
)