CREATE TABLE Customers (
	custID varchar(50) PRIMARY KEY,
	custName nvarchar(100),
	custAddress nvarchar(200)
)

CREATE TABLE Rooms (
	roomID varchar(50) PRIMARY KEY,
	roomPrice money
)

CREATE TABLE Services (
	serviceID varchar(50) PRIMARY KEY,
	serviceName nvarchar(100),
	servicePrice money
)

CREATE TABLE Contracts (
	custID varchar(50),
	roomID varchar(50),
	serviceID varchar(50),

	contractID varchar(50),
	quantity int,

	PRIMARY KEY (custID, roomID, serviceID, contractID),

	FOREIGN KEY (custID) REFERENCES Customers(custID),
	FOREIGN KEY (roomID) REFERENCES Rooms(roomID),
	FOREIGN KEY (serviceID) REFERENCES Services(serviceID)
)