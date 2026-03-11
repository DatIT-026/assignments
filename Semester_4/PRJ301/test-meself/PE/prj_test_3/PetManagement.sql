-- Create Database
CREATE DATABASE PetManagement;
GO
USE PetManagement;
GO

-- 1. Users Table (Authentication and Authorization)
CREATE TABLE Users (
    userID VARCHAR(20) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    fullName NVARCHAR(100),
    roleID CHAR(2), -- 'ST' for Staff (Admin-like), 'US' for regular User
    status INT      -- 1 for Active, 0 for Inactive
);

-- 2. Pets Table (Business Data)
CREATE TABLE Pets (
    petID INT PRIMARY KEY IDENTITY(1,1),
    petName NVARCHAR(100),
    breed NVARCHAR(50),
    age INT,
    price FLOAT,
    description NVARCHAR(255)
);

-- Insert Sample Data
INSERT INTO Users (userID, password, fullName, roleID, status) VALUES 
('staff01', '123', 'John Wick', 'ST', 1),
('client01', '123', 'Tony Stark', 'US', 1),
('bad_user', '123', 'Loki Odinson', 'US', 0);

INSERT INTO Pets (petName, breed, age, price, description) VALUES 
('Buddy', 'Golden Retriever', 3, 800.0, 'Very friendly and active'),
('Luna', 'Persian Cat', 2, 450.0, 'White fur, quiet temperament'),
('Max', 'Beagle', 5, 600.0, 'Great tracking instincts');
