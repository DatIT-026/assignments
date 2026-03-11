USE master;
GO

IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'UserAuthDb')
BEGIN
	CREATE DATABASE UserAuthDb;
END
GO

USE UserAuthDb;
GO

IF OBJECT_ID('dbo.Users', 'u') IS NOT NULL
BEGIN
	DROP TABLE dbo.Users;
END
GO

CREATE TABLE Users (
	user_id INT IDENTITY(1,1) PRIMARY KEY,
	username NVARCHAR(50) NOT NULL UNIQUE,
	password NVARCHAR(255) NULL,
	email NVARCHAR(100) NOT NULL UNIQUE,
	full_name NVARCHAR(100) NOT NULL,
	is_admin BIT DEFAULT 0,
	is_verified BIT DEFAULT 0,
	verification_code NVARCHAR(10) NULL,
	reset_code NVARCHAR(10) NULL,
	code_expiry DATETIME NULL,
	created_date DATETIME DEFAULT GETDATE(),
	last_login DATETIME NULL,
	is_active BIT DEFAULT 1,
	social_id NVARCHAR(255) NULL,
	profile_picture NVARCHAR(500) NULL
);
GO

CREATE INDEX idx_username ON Users(username);
CREATE INDEX idx_email ON Users(email);
CREATE INDEX idx_verification_code ON Users(verification_code);
CREATE INDEX idx_reset_code ON Users(reset_code);
CREATE UNIQUE INDEX idx_social_id ON Users(social_id) WHERE social_id IS NOT NULL;
GO

-- admin
INSERT INTO Users (username, password, email, full_name, is_admin, is_verified, is_active)
VALUES ('admin', 'Admin@12345', 'admin@gmail.com', 'System Administrator', 1, 1, 1);

-- user
INSERT INTO Users (username, password, email, full_name, is_admin, is_verified, is_active)
VALUES ('user', 'User@12345', 'user@gmail.com', 'Tester', 0, 1, 1);
GO

SELECT * FROM Users;
GO