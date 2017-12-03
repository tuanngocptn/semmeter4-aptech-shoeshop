USE master
GO

IF EXISTS(SELECT name FROM sys.databases WHERE name = 'shoeshop')
	DROP DATABASE shoeshop
GO

CREATE DATABASE shoeshop
GO

USE shoeshop
GO

CREATE TABLE tblRole (
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_name VARCHAR(50) NOT NULL
)
GO

INSERT INTO tblRole(_code,_name) VALUES ('adm','admin'),
										('ctm','customer')
GO

CREATE TABLE tblAccount(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_roleCode VARCHAR(20) FOREIGN KEY REFERENCES tblRole(_code),
	_user VARCHAR(50) NOT NULL UNIQUE,
	_pass VARCHAR(50) NOT NULL,
	_name VARCHAR(50),
	_birthday DATE,
	_address VARCHAR(255) UNIQUE,
	_phone VARCHAR(15) NOT NULL UNIQUE,
	_email VARCHAR(100),
	_status VARCHAR(20) NOT NULL DEFAULT 'active',
)
GO

INSERT INTO tblAccount(_code, _roleCode, _user, _pass, _birthday, _address, _phone, _email, _status) 
			   VALUES ('adm01','adm','admin','admin','1999-12-12 00:00:00','123 adm01 No 1 in the Mars','0989786765', 'adm01@email.com', 'active'),
					  ('ctm01','ctm','cus01','cus01','1998-12-12 00:00:00','123 ctm01 No 1 in the Mars','0987675654', 'cus01@email.com', 'active')