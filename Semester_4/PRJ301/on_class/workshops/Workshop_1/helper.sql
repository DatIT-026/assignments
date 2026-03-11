CREATE DATABASE MyShop;
GO

USE MyShop;
GO

CREATE TABLE Mobiles(
    mobileId VARCHAR(10) PRIMARY KEY,
    description VARCHAR(250) NOT NULL,
    price FLOAT,
    mobileName VARCHAR(20) NOT NULL,
    yearOfProduction INT,
    quantity INT,
    notSale BIT
);
GO

CREATE TABLE Users(
    userId VARCHAR(20) PRIMARY KEY,
    password INT NOT NULL,
    fullName VARCHAR(50) NOT NULL,
    role INT
);
GO

INSERT INTO Mobiles (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) 
VALUES
('M001', 'High-end smartphone with OLED screen', 999.99, 'Galaxy S23', 2023, 50, 0),
('M002', 'Budget friendly phone with large battery', 250.00, 'Moto G', 2022, 120, 0),
('M003', 'Discontinued older model', 150.00, 'OldPhone X', 2018, 0, 1);
GO

INSERT INTO Users (userId, password, fullName, role) 
VALUES
('user_john', 123456, 'John Doe', 0),
('staff_jane', 987654, 'Jane Smith', 1);
GO