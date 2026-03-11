USE [master]
GO

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'StudentManagement')
BEGIN
	ALTER DATABASE StudentManagement SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE StudentManagement SET ONLINE;
	DROP DATABASE StudentManagement;
END

GO

CREATE DATABASE StudentManagement
GO

USE StudentManagement
GO

/*******************************************************************************
	Drop tables if exists
*******************************************************************************/
DECLARE @sql nvarchar(MAX) 
SET @sql = N'' 

SELECT @sql = @sql + N'ALTER TABLE ' + QUOTENAME(KCU1.TABLE_SCHEMA) 
    + N'.' + QUOTENAME(KCU1.TABLE_NAME) 
    + N' DROP CONSTRAINT ' -- + QUOTENAME(rc.CONSTRAINT_SCHEMA)  + N'.'  -- not in MS-SQL
    + QUOTENAME(rc.CONSTRAINT_NAME) + N'; ' + CHAR(13) + CHAR(10) 
FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS AS RC 

INNER JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS KCU1 
    ON KCU1.CONSTRAINT_CATALOG = RC.CONSTRAINT_CATALOG  
    AND KCU1.CONSTRAINT_SCHEMA = RC.CONSTRAINT_SCHEMA 
    AND KCU1.CONSTRAINT_NAME = RC.CONSTRAINT_NAME 

EXECUTE(@sql) 

GO
DECLARE @sql2 NVARCHAR(max)=''

SELECT @sql2 += ' Drop table ' + QUOTENAME(TABLE_SCHEMA) + '.'+ QUOTENAME(TABLE_NAME) + '; '
FROM   INFORMATION_SCHEMA.TABLES
WHERE  TABLE_TYPE = 'BASE TABLE'

Exec Sp_executesql @sql2 
GO 

CREATE TABLE studentList (
    studentID INT PRIMARY KEY IDENTITY(1,1),
    fullName NVARCHAR(100) NOT NULL,
    userName VARCHAR(50) NOT NULL UNIQUE,
    [password] VARCHAR(255) NOT NULL,
    addressVN NVARCHAR(255),
    createdAt DATETIME DEFAULT GETDATE(),
	isAdmin bit NOT NULL
);

CREATE TABLE courseList (
    courseID VARCHAR(20) PRIMARY KEY,
    courseName NVARCHAR(200) NOT NULL,
    credits INT CHECK (credits > 0),
    description NVARCHAR(MAX)
);

INSERT INTO studentList (fullName, userName, [password], addressVN, isAdmin)
VALUES 
(N'Hà Nguyễn Tiến Đạt', 'dathnt', '00000', N'Hà Nội', 1),
(N'Lê Hoàng Nam', 'namlh', '01234', N'TP. Hồ Chí Minh', 1),
(N'Nguyễn Hải Đăng', 'dangnh', '123456', N'Hà Nội', 0),
(N'Trần Thị Mai', 'maitt', '55555', N'Đà Nẵng', 0)

INSERT INTO courseList (courseID, courseName, credits, description)
VALUES 
('CS101', N'Lập trình Java cơ bản', 3, N'Nhập môn lập trình hướng đối tượng'),
('DB201', N'Hệ quản trị CSDL', 3, N'Học SQL Server và thiết kế ERD'),
('FE301', N'Thiết kế Web Front-end', 2, N'HTML, CSS và JavaScript cơ bản');