CREATE DATABASE TechStoreDB;
GO
USE TechStoreDB;
GO

-- 2. Professional Product Management
CREATE TABLE Products (
    ProductID INT PRIMARY KEY IDENTITY(1,1),
    ProductName NVARCHAR(200) NOT NULL,
    Price DECIMAL(18, 2) NOT NULL,
    StockQuantity INT DEFAULT 0, -- Used for Checkout logic
    Category VARCHAR(255),
    Status BIT DEFAULT 1 -- 1: Active, 0: Hidden (Soft Delete)
);

-- 3. Handles Authentication & Authorization
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(50) NOT NULL,
    Role VARCHAR(20) DEFAULT 'CUSTOMER' -- 'ADMIN' or 'CUSTOMER'
);

INSERT INTO Users VALUES 
('admin', 'admin123', 'ADMIN'),
('miniks', 'user123', 'CUSTOMER');

INSERT INTO Products (ProductName, Price, StockQuantity, Category) VALUES 
(N'MacBook M3 Pro', 2200, 15, N'Laptops'),
(N'Dell XPS 13', 1400, 10, N'Laptops'),
(N'ThinkPad X1 Carbon', 1600, 8, N'Laptops'),
(N'iPhone 15 Pro', 1100, 25, N'Smartphones'),
(N'Samsung S24 Ultra', 1200, 20, N'Smartphones'),
(N'Google Pixel 8', 800, 12, N'Smartphones'),
(N'Keychron K2 V2', 90, 50, N'Peripherals'),
(N'Logitech MX Master 3S', 100, 40, N'Peripherals'),
(N'Sony WH-1000XM5', 350, 15, N'Peripherals'),
(N'LG UltraFine 4K', 700, 5, N'Peripherals');
