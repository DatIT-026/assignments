USE [master]
GO

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'PE_DBI202')
BEGIN
	ALTER DATABASE [PE_DBI202] SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE [PE_DBI202] SET ONLINE;
	DROP DATABASE [PE_DBI202];
END

GO

CREATE DATABASE [PE_DBI202]
GO

USE [PE_DBI202]
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

CREATE TABLE DEPARTMENT
( 
Dname VARCHAR(15) NOT NULL,
Dnumber INT NOT NULL,
Mgr_ssn CHAR(9) NOT NULL,
Mgr_start_date DATE,
PRIMARY KEY (Dnumber),
UNIQUE (Dname));
go
CREATE TABLE EMPLOYEE
( 
	Fname VARCHAR(15) NOT NULL,
	Minit CHAR,
	Lname VARCHAR(15) NOT NULL,
	Ssn CHAR(9) NOT NULL,
	Bdate DATE,
	Address VARCHAR(30),
	Sex CHAR,
	Salary DECIMAL(10,2),
	Super_ssn CHAR(9),
	Dno INT NOT NULL,
	PRIMARY KEY (Ssn),
	FOREIGN KEY (Super_ssn) REFERENCES EMPLOYEE(Ssn),
	FOREIGN KEY (Dno) REFERENCES DEPARTMENT(Dnumber) 
);
go
CREATE TABLE DEPT_LOCATIONS
( 
	Dnumber INT NOT NULL,
	Dlocation VARCHAR(15) NOT NULL,
	PRIMARY KEY (Dnumber, Dlocation),
	FOREIGN KEY (Dnumber) REFERENCES DEPARTMENT(Dnumber) 
);
go
CREATE TABLE PROJECT
( 
	Pname VARCHAR(15) NOT NULL,
	Pnumber INT NOT NULL,
	Plocation VARCHAR(15),
	Dnum INT NOT NULL,
	PRIMARY KEY (Pnumber),
	UNIQUE (Pname),
	FOREIGN KEY (Dnum) REFERENCES DEPARTMENT(Dnumber) 
);
go
CREATE TABLE WORKS_ON
( 
	Essn CHAR(9) NOT NULL,
	Pno INT NOT NULL,
	Hours DECIMAL(3,1),
	PRIMARY KEY (Essn, Pno),
	FOREIGN KEY (Essn) REFERENCES EMPLOYEE(Ssn),
	FOREIGN KEY (Pno) REFERENCES PROJECT(Pnumber) 
);
go
CREATE TABLE DEPENDENT
( 
	Essn CHAR(9) NOT NULL,
	Dependent_name VARCHAR(15) NOT NULL,
	Sex CHAR,
	Bdate DATE,
	Relationship VARCHAR(8),
	PRIMARY KEY (Essn, Dependent_name),
	FOREIGN KEY (Essn) REFERENCES EMPLOYEE(Ssn) 
);

go
insert into DEPARTMENT
values('Research',5,333445555,'1988-05-22')
insert into DEPARTMENT
values('Administration',4,987654321,'1995-01-01')
insert into DEPARTMENT
values('Headquaters',1,888665555,'1981-06-19')
go

insert into EMPLOYEE values('James','E','Borg',888665555,'1937-11-10','450 Stone, Houston, TX','M',55000,NULL,1)
insert into EMPLOYEE values('Franklin','T','Wong',333445555,'1955-12-08','638 Voss, Houston, TX','M',40000,888665555,5)
insert into EMPLOYEE values('Jennifer','S','Wallace',987654321,'1941-06-20','291 Berry, Bellaire, TX','F',43000,888665555,4)
insert into EMPLOYEE values('Alicia','J','Zelaya',999887777,'1968-01-19','3321 Castle, Spring, TX','F',25000,987654321,4)
insert into EMPLOYEE values('Ramesh','K','Narayan',666884444,'1962-09-15','915 Fire Oak, Humble, TX','M',38000,333445555,5)
insert into EMPLOYEE values('Joyce','A','English',453453453,'1972-07-31','5631 Rice, Houston, TX','F',25000,333445555,5)
insert into EMPLOYEE values('Ahmad','V','Jabbar',987987987,'1969-03-29','980 Dallas, Houston, TX','M',25000,987654321,4)
insert into EMPLOYEE values('John','B','Smith',123456789,'1965-01-09','731 Fondren, Houston, TX','M',30000,333445555,5)
go

insert into DEPT_LOCATIONS values(1,'Houston')
insert into DEPT_LOCATIONS values(4,'Stafford')
insert into DEPT_LOCATIONS values(5,'Bellaire')
insert into DEPT_LOCATIONS values(5,'Sugarland')
insert into DEPT_LOCATIONS values(5,'Houston')
go
insert into PROJECT values('ProductX',1,'Bellaire',5)
insert into PROJECT values('ProductY',2,'Sugarland',5)
insert into PROJECT values('ProductZ',3,'Houston',4)
insert into PROJECT values('Computerization',10,'Stafford',4)
insert into PROJECT values('Reorganization',20,'Houston',1)
insert into PROJECT values('Newbenefits',30,'Stafford',4)
go
insert into WORKS_ON values(123456789,1,32.5)
insert into WORKS_ON values(123456789,2,7.5)
insert into WORKS_ON values(666884444,3,40.0)
insert into WORKS_ON values(453453453,1,20.0)
insert into WORKS_ON values(453453453,2,20.0)
insert into WORKS_ON values(333445555,2,10.0)
insert into WORKS_ON values(333445555,3,10.0)
insert into WORKS_ON values(333445555,10,10.0)
insert into WORKS_ON values(333445555,20,10.0)
insert into WORKS_ON values(999887777,30,30.0)
insert into WORKS_ON values(999887777,10,10.0)
insert into WORKS_ON values(987987987,10,35.0)
insert into WORKS_ON values(987987987,30,5.0)
insert into WORKS_ON values(987654321,30,20.0)
insert into WORKS_ON values(987654321,20,15.0)
insert into WORKS_ON values(888665555,20,NULL)
go
insert into DEPENDENT values(333445555,'Alice','F','1986-04-05','Daughter')
insert into DEPENDENT values(333445555,'Theodore','M','1983-10-25','Son')
insert into DEPENDENT values(333445555,'Joy','F','1958-05-03','Spouse')
insert into DEPENDENT values(987654321,'Abner','M','1942-02-28','Spouse')
insert into DEPENDENT values(123456789,'Michael','M','1988-01-04','Son')
insert into DEPENDENT values(123456789,'Alica','F','1988-12-30','Daughter')
insert into DEPENDENT values(123456789,'Elizabeth','F','1967-05-05','Spouse')
GO