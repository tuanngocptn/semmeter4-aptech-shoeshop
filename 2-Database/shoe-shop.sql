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
	VALUES	('nike','Nike'),
			('adidas','Adidas'),
			('converse','Converse'),
			('vans','Vans'),
			('puma','Puma')
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
	VALUES	('joggers','Joggers'),
			('cricket','Cricket'),
			('tennis','Tennis'),
			('casual','Casual'),
			('sports','Sports')
GO

CREATE TABLE tbl_product(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_name VARCHAR(50) NOT NULL,
	_brand_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_brand(_code),
	_category_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_category(_code),
	_description VARCHAR(500) NOT NULL DEFAULT 'description',
	_price FLOAT NOT NULL DEFAULT 0,
	_is_hot BIT NOT NULL DEFAULT 0,
	_quantity INT NOT NULL DEFAULT 0,
	_status VARCHAR(20) NOT NULL DEFAULT 'active'
)
GO

INSERT INTO tbl_product (_code, _name, _brand_code, _category_code,_description,_price,_is_hot,_quantity)
	VALUES	('prd01','Nike SF-AF1', 'nike', 'casual', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 103, 1, 12),
			('prd02','Nike Air Huarache', 'nike', 'joggers', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 123, 0, 20),
			('prd03','Nike Air Max', 'nike', 'joggers', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 121, 0, 30),
			('prd04','The Nike Air Jordan', 'nike', 'cricket', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 289, 0, 32),
			('prd05','Adidas Ultra Boots', 'adidas', 'cricket', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 150, 1, 41),
			('prd06','Adidas EQT 2017', 'adidas', 'sports', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 110, 0, 12),
			('prd07','Adidas MND', 'adidas', 'tennis', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 100, 0, 15),
			('prd08','Adidas Yeezy Boost', 'adidas', 'sports', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 250, 1, 22),
			('prd09','Vans Late Night Pack', 'vans', 'sports', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 60, 0, 11),
			('prd10','Vans Digi Hula', 'vans', 'cricket', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 120, 0, 14),
			('prd11','Vans x Baron Von Fancy', 'vans', 'tennis', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 123, 0, 31),
			('prd12','Vans Footwear', 'vans', 'tennis', 'Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua. Ut Enim Ad Minim Veniam, Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat. Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu', 135, 0, 10)
GO

CREATE TABLE tbl_product_image(
	_ord INT IDENTITY,
	_code VARCHAR(20) PRIMARY KEY,
	_product_code VARCHAR(20) FOREIGN KEY REFERENCES tbl_product(_code),
	_status VARCHAR(20) NOT NULL DEFAULT 'active',
	_image VARCHAR(255) NOT NULL DEFAULT 'https://dummyimage.com/600x400/000/fff',
)
GO

INSERT INTO tbl_product_image (_code, _product_code,_image)
	VALUES	('ptr01','prd01','images/shoe1.jpg'),
			('ptr02','prd01','images/grid3.jpg'),
			('ptr03','prd01','images/grid4.jpg'),
			('ptr04','prd01','images/show.jpg'),

			('ptr05','prd02','images/shoe1.jpg'),
			('ptr06','prd02','images/grid4.jpg'),
			('ptr07','prd02','images/grid3.jpg'),
			('ptr08','prd02','images/show1.jpg'),

			('ptr09','prd03','images/shoe3.jpg'),
			('ptr10','prd03','images/grid5.jpg'),
			('ptr11','prd03','images/grid6.jpg'),
			('ptr12','prd03','images/show2.jpg'),

			('ptr13','prd04','images/shoe1.jpg'),
			('ptr14','prd04','images/grid6.jpg'),
			('ptr15','prd04','images/grid5.jpg'),
			('ptr16','prd04','images/show3.jpg'),

			('ptr17','prd05','images/shoe2.jpg'),
			('ptr18','prd05','images/grid7.jpg'),
			('ptr19','prd05','images/grid8.jpg'),
			('ptr20','prd05','images/show.jpg'),

			('ptr21','prd06','images/shoe1.jpg'),
			('ptr22','prd06','images/grid8.jpg'),
			('ptr23','prd06','images/grid7.jpg'),
			('ptr24','prd06','images/show1.jpg'),

			('ptr25','prd07','images/shoe3.jpg'),
			('ptr26','prd07','images/grid3.jpg'),
			('ptr27','prd07','images/grid9.jpg'),
			('ptr28','prd07','images/show2.jpg'),

			('ptr29','prd08','images/shoe3.jpg'),
			('ptr30','prd08','images/grid4.jpg'),
			('ptr31','prd08','images/grid10.jpg'),
			('ptr32','prd08','images/show3.jpg'),

			('ptr33','prd09','images/shoe2.jpg'),
			('ptr34','prd09','images/grid5.jpg'),
			('ptr35','prd09','images/grid11.jpg'),
			('ptr36','prd09','images/show.jpg'),

			('ptr37','prd10','images/shoe2.jpg'),
			('ptr38','prd10','images/grid6.jpg'),
			('ptr39','prd10','images/grid12.jpg'),
			('ptr40','prd10','images/show1.jpg'),

			('ptr41','prd11','images/shoe2.jpg'),
			('ptr42','prd11','images/grid7.jpg'),
			('ptr43','prd11','images/grid13.jpg'),
			('ptr44','prd11','images/show2.jpg'),

			('ptr45','prd12','images/shoe3.jpg'),
			('ptr46','prd12','images/grid8.jpg'),
			('ptr47','prd12','images/grid12.jpg'),
			('ptr48','prd12','images/show3.jpg')
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

CREATE VIEW VIEW_REPORT AS
SELECT tbl_product._price, tbl_order_detail._quantity, tbl_order._code, tbl_order._date FROM tbl_order_detail
LEFT JOIN tbl_order ON tbl_order_detail._order_code = tbl_order._code
LEFT JOIN tbl_product ON tbl_order_detail._product_code = tbl_product._code;


