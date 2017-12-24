USE master
GO

IF EXISTS(SELECT name FROM sys.databases WHERE name = 'SHOE_SHOP')
	DROP DATABASE SHOE_SHOP
GO

CREATE DATABASE SHOE_SHOP
GO

USE SHOE_SHOP
GO

CREATE TABLE tbl_role (
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_name VARCHAR(50) NOT NULL,
	_status VARCHAR(20) NOT NULL DEFAULT 'active',
)
GO

INSERT INTO tbl_role(_code,_name) 
	VALUES	('adm','admin'),
			('ctm','customer')
GO

CREATE TABLE tbl_account(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_role_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_role(_code),
	_user VARCHAR(50) NOT NULL UNIQUE,
	_pass VARCHAR(50) NOT NULL,
	_name VARCHAR(50) NOT NULL DEFAULT 'edit me',
	_address VARCHAR(255),
	_phone VARCHAR(15) NOT NULL UNIQUE,
	_email VARCHAR(100) NOT NULL DEFAULT 'edit-me@email.com',
	_status VARCHAR(20) NOT NULL DEFAULT 'active'
)
GO


INSERT INTO tbl_account(_code, _role_code, _user, _pass, _address, _phone, _email, _status) 
	VALUES	('adm01','adm','admin','admin','123 adm01 No 1 in the Mars','0989786765', 'adm01@email.com', 'active'),
			('ctm01','ctm','cus01','cus01','123 ctm01 No 1 in the Mars','0987675654', 'cus01@email.com', 'active')
GO

CREATE TABLE tbl_brand(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_name VARCHAR(50) NOT NULL,
	_status VARCHAR(20) NOT NULL DEFAULT 'active',
	_logo VARCHAR(255) NOT NULL DEFAULT 'https://dummyimage.com/600x400/000/fff',
)
GO

INSERT INTO tbl_brand (_code, _name)
	VALUES	('brn01','branch01'),
			('brn02','branch02')
GO

CREATE TABLE tbl_category(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_name VARCHAR(50) NOT NULL,
	_status VARCHAR(20) NOT NULL DEFAULT 'active',
	_logo VARCHAR(255) NOT NULL DEFAULT 'https://dummyimage.com/600x400/000/fff',
)
GO

INSERT INTO tbl_category (_code, _name)
	VALUES	('cate01','category01'),
			('cate02','category02')
GO

CREATE TABLE tbl_product(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_name VARCHAR(50) NOT NULL,
	_brand_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_brand(_code),
	_category_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_category(_code),
	_description VARCHAR(255) NOT NULL DEFAULT 'description',
	_price FLOAT NOT NULL DEFAULT 0,
	_is_hot BIT NOT NULL DEFAULT 0,
	_quantity INT NOT NULL DEFAULT 0,
	_status VARCHAR(20) NOT NULL DEFAULT 'active'
)
GO

INSERT INTO tbl_product (_code, _name, _brand_code, _category_code)
	VALUES	('prd01','product 01', 'brn01', 'cate01'),
			('prd02','product 02', 'brn02', 'cate02')
GO

CREATE TABLE tbl_product_image(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_product_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_product(_code),
	_status VARCHAR(20) NOT NULL DEFAULT 'active',
	_image VARCHAR(255) NOT NULL DEFAULT 'https://dummyimage.com/600x400/000/fff',
)
GO

INSERT INTO tbl_product_image (_code, _product_code)
	VALUES	('ptr01','prd01'),
			('ptr02','prd01'),
			('ptr03','prd01'),
			('ptr04','prd02'),
			('ptr05','prd02'),
			('ptr06','prd02')
GO

CREATE TABLE tbl_order(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_account_code VARCHAR(20),
	_date BIGINT NOT NULL DEFAULT 1513036800000,
	_name VARCHAR(50) NOT NULL DEFAULT 'edit me',
	_email VARCHAR(100) NOT NULL DEFAULT 'edit-me@email.com',
	_phone VARCHAR (20) NOT NULL DEFAULT '84000000000',
	_ship_address VARCHAR(255) NOT NULL DEFAULT '123 adm01 No 1 in the Mars',
	_ship_date BIGINT NOT NULL DEFAULT 1513036800000,
	_status VARCHAR(20) NOT NULL DEFAULT 'wait',
)

INSERT INTO tbl_order (_code, _account_code)
	VALUES	('ord01','ctm01'),
			('ord02','ctm01')
GO

CREATE TABLE tbl_order_detail(
	_ord INT IDENTITY,
	_order_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_order(_code),
	_product_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_product(_code),
	_quantity INT NOT NULL DEFAULT 1,
	CONSTRAINT tbl_order_detail_pri PRIMARY KEY (_order_code,_product_code)
)
GO

INSERT INTO tbl_order_detail(_order_code,_product_code)
	VALUES	('ord01','prd01'),
			('ord01','prd02'),
			('ord02','prd01'),
			('ord02','prd02')
GO

CREATE TABLE tbl_rate(
	_ord INT IDENTITY,
	_product_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_product(_code),
	_account_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_account(_code),
	_rate INT NOT NULL DEFAULT 3,
	CONSTRAINT tbl_rate_pri PRIMARY KEY (_product_code,_account_code)
)
GO

INSERT INTO tbl_rate (_product_code, _account_code)
	VALUES	('prd01','ctm01'),
			('prd02','ctm01')
GO