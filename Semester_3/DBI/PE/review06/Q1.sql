CREATE TABLE Owner (
    ownerID INT PRIMARY KEY,
    name NVARCHAR(50)
);

CREATE TABLE Vehicle (
    vehicleID INT PRIMARY KEY,
    maker VARCHAR(30),
    model VARCHAR(30),
    year INT,
	ownerID INT,
    FOREIGN KEY (ownerID) REFERENCES Owner(ownerID)
);

CREATE TABLE Car (
    vehicleID INT PRIMARY KEY,
    NumDoors INT,
    bodyStyle VARCHAR(30),
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(vehicleID)
);

CREATE TABLE Motorcycle (
    vehicleID INT PRIMARY KEY,
    engineSize INT,
	type VARCHAR(30),
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(vehicleID)
);