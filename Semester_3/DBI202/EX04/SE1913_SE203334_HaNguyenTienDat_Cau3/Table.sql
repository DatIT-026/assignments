CREATE TABLE Customer
(
    CustomerID VARCHAR(10) PRIMARY KEY,
    FullName NVARCHAR(50),
    CusAddress NVARCHAR(100),
    PhoneNumber VARCHAR(15),
    DateOfBirth DATE,
    Revenue DECIMAL(15,0),
    RegistrationDate DATE
);

CREATE TABLE Staff
(
    StaffID VARCHAR(10) PRIMARY KEY,
    FullName NVARCHAR(50),
    PhoneNumber VARCHAR(15),
    StartDate DATE
);

CREATE TABLE Products
(
    ProductID VARCHAR(10) PRIMARY KEY,
    ProductName NVARCHAR(50),
    UnitOfMeasure NVARCHAR(20),
    CountryOfOrigin NVARCHAR(20),
    Price DECIMAL(15,0)
);

CREATE TABLE Invoice
(
    InvoiceID VARCHAR(10) PRIMARY KEY,
    InvoiceDate DATE DEFAULT GETDATE(),
    CustomerID VARCHAR(10) REFERENCES CUSTOMER(CustomerID),
    StaffID VARCHAR(10) REFERENCES Staff(StaffID),
    InvoiceValue DECIMAL(15,0)
);

CREATE TABLE InvoiceDetails
(
    InvoiceID VARCHAR(10),
    ProductID VARCHAR(10),
    Quantity SMALLINT,
    FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    PRIMARY KEY (InvoiceID, ProductID)
);