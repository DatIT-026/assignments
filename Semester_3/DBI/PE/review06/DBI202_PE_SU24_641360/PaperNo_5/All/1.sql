CREATE TABLE Owner (
	ownerID int PRIMARY KEY,
	name nvarchar(50)
);

CREATE TABLE Vehicle (
	vehicleID int PRIMARY KEY,
	maker varchar(30),
	model varchar(30),
	year int,

	ownerID int
	FOREIGN KEY (ownerID) REFERENCES Owner(ownerId)
);

CREATE TABLE Car (
	NumDoors int,
	bodyStyle varchar(30),
	
	vehicleID int PRIMARY KEY,
	FOREIGN KEY (vehicleID) REFERENCES Vehicle(vehicleID)
);


CREATE TABLE Motorcycle (
	type varchar(30),
	engineSize int,

	vehicleID int PRIMARY KEY,
	FOREIGN KEY (vehicleID) REFERENCES Vehicle(vehicleID)
);