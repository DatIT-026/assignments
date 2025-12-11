USE PE_DBI202

CREATE TABLE Customers (
	CustID int PRIMARY KEY,
	City varchar(100),
	Cname varchar(100)
);

CREATE TABLE Orders (
	OrderID int PRIMARY KEY,
	Odate date,
	CustID int,
	FOREIGN KEY (CustID) REFERENCES Customers(CustID)
);

CREATE TABLE Items (
	ItemID int PRIMARY KEY,
	Unit_Price decimal(10)
);

-- bang phu noi giua Orders va Items (quan he n-m)
CREATE TABLE Order_Item (
	Qty int,
	OrderID int,
	ItemID int,
	PRIMARY KEY (OrderID, ItemID),
	FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
	FOREIGN KEY (ItemID) REFERENCES Items(ItemID)
);