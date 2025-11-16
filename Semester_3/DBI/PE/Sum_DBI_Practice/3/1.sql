CREATE TABLE Account (
    Email varchar(50) PRIMARY KEY,
    Status int,
    Password varchar(50)
);

CREATE TABLE Customer (
    Email varchar(50) PRIMARY KEY,
    createdAt datetime,
    FOREIGN KEY (Email) REFERENCES Account(Email)
);

CREATE TABLE Cosplayer (
    Email varchar(50) PRIMARY KEY,
    description varchar(50),
    FOREIGN KEY (Email) REFERENCES Account(Email)
);

CREATE TABLE Feedback (
    ID int identity(1,1) PRIMARY KEY,
    Content varchar(50),

    CustomerEmail varchar(50), 
    FOREIGN KEY (CustomerEmail) REFERENCES Customer(Email)
);