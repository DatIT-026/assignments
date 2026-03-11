CREATE DATABASE MyStock;
GO
USE MyStock;
GO

CREATE TABLE Cars (
    CarID INT PRIMARY KEY,
    CarName VARCHAR(50) NOT NULL,
    Manufacturer VARCHAR(50) NOT NULL,
    Price MONEY NOT NULL,
    ReleasedYear INT NOT NULL
);
GO

INSERT INTO Cars (CarID, CarName, Manufacturer, Price, ReleasedYear) VALUES
(1, 'Accord', 'Honda Motor Company', 24970.0000, 2021),
(3, 'Clarity', 'Honda Motor Company', 33400.0000, 2021),
(4, 'BMW 8 Series', 'BMW', 85000.0000, 2021),
(5, 'Audi A6', 'Audi', 14000.0000, 2020);
GO