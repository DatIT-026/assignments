CREATE TABLE Teams (
	TeamCode varchar(15) PRIMARY KEY,
	Name nvarchar(50),
	Address nvarchar(200)
);

CREATE TABLE Players (
	SSN varchar(15) PRIMARY KEY,
	Name nvarchar(100),

	TeamCode varchar(15),
	FOREIGN KEY (TeamCode) REFERENCES Teams(TeamCode)
);

CREATE TABLE Games (
	GameCode varchar(15) PRIMARY KEY,
	Time time,
	Date date
);

CREATE TABLE Participate (
	TeamCode varchar(15) NOT NULL,
	GameCode varchar(15) NOT NULL,
	Result int,

	PRIMARY KEY (TeamCode, GameCode),

	FOREIGN KEY (TeamCode) REFERENCES Teams(TeamCode),
	FOREIGN KEY (GameCode) REFERENCES Games(GameCode)
);