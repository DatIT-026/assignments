CREATE TABLE Hotels (
	hotelID INT PRIMARY KEY,
	name NVARCHAR(100),
	address NVARCHAR(100),
	street NVARCHAR(50),
	city NVARCHAR(50)
)

CREATE TABLE contactHotel (
	contact NVARCHAR(100),
	hotelID INT,

	PRIMARY KEY(contact, hotelID),
	FOREIGN KEY (hotelID) REFERENCES Hotels(hotelID) 
)

CREATE TABLE Guests (
	guestID INT PRIMARY KEY,
	fullname NVARCHAR(50),
	phone VARCHAR(50),
	email VARCHAR(50)
)

CREATE TABLE Rooms (
	roomNumber INT,

	floor INT, 
	building NVARCHAR(200),

	hotelID INT,
	PRIMARY KEY (roomNumber, hotelID),
	FOREIGN KEY (hotelID) REFERENCES Hotels(hotelID)
)

CREATE TABLE books (
	bookingID INT PRIMARY KEY,

	guestID INT,
	roomNumber INT,
	hotelID INT,
	FOREIGN KEY (guestID) REFERENCES  Guests(guestID),
	FOREIGN KEY (roomNumber, hotelID) REFERENCES Rooms(roomNumber, hotelID)
)
