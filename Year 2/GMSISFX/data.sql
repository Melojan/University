BEGIN TRANSACTION;
CREATE TABLE customerVehicle ( 
	id                   integer NOT NULL  ,
	customerID           integer   ,
	vehicleID            integer   ,
	CONSTRAINT Pk_customerVehicle PRIMARY KEY ( id ),
	FOREIGN KEY ( customerID ) REFERENCES customer( id ) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY ( vehicleID ) REFERENCES Vehicle( id ) ON DELETE CASCADE ON UPDATE CASCADE
 );
INSERT INTO `customerVehicle` VALUES (1,1,1);
INSERT INTO `customerVehicle` VALUES (2,2,2);
INSERT INTO `customerVehicle` VALUES (3,3,5);
INSERT INTO `customerVehicle` VALUES (4,4,10);
INSERT INTO `customerVehicle` VALUES (5,5,6);
INSERT INTO `customerVehicle` VALUES (6,6,7);
INSERT INTO `customerVehicle` VALUES (7,7,9);
INSERT INTO `customerVehicle` VALUES (8,8,8);
INSERT INTO `customerVehicle` VALUES (9,9,3);
INSERT INTO `customerVehicle` VALUES (10,10,4);
INSERT INTO `customerVehicle` VALUES (11,11,12);
INSERT INTO `customerVehicle` VALUES (12,12,11);
INSERT INTO `customerVehicle` VALUES (13,13,15);
INSERT INTO `customerVehicle` VALUES (14,14,14);
INSERT INTO `customerVehicle` VALUES (15,15,13);
CREATE TABLE customerType ( 
	id                   integer NOT NULL  ,
	customerType         text   ,
	CONSTRAINT Pk_customerType PRIMARY KEY ( id )
 );
INSERT INTO `customerType` VALUES (1,'Indi');
INSERT INTO `customerType` VALUES (2,'business');
CREATE TABLE "customer" (
	`id`	integer NOT NULL,
	`firstName`	text,
	`lastName`	TEXT,
	`customerType`	integer,
	`address`	TEXT,
	`phoneNumber`	text,
	`email`	text,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`customerType`) REFERENCES `customerType`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `customer` VALUES (1,'John','Smith',2,'15 Mile End Rd','02082547812','john_smith@gmail.com');
INSERT INTO `customer` VALUES (2,'Ming','Xiao',2,'101 Haymill Close','02082307673','waleedehsan@gmail.com');
INSERT INTO `customer` VALUES (3,'Sarah','Walker',1,'69 North Lane','02082471259','sarah_walker@gmail.com');
INSERT INTO `customer` VALUES (4,'Lu','Xiaojun',1,'32 Westminser Road','02082987563','lu_xiaojun@gmail.com');
INSERT INTO `customer` VALUES (5,'Michael','Makepeace',2,'50 Bow Rd','02082133254','michael_makepeace@gmail.com');
INSERT INTO `customer` VALUES (6,'Madison','Reid',2,'44 Argyl Rd','02082821654','madison_reid@gmail.com');
INSERT INTO `customer` VALUES (7,'Ahmed','Hussain',1,'99 Sunny Meadow','02082012145','ahmed_hussain@gmail.com');
INSERT INTO `customer` VALUES (8,'Terry','Butler',1,'78 Heath Rd','02082648915','terry_butler@gmail.com');
INSERT INTO `customer` VALUES (9,'Elizabeth','Green',2,'259 Lisa Street','02082369871','elizabeth_green@gmail.com');
INSERT INTO `customer` VALUES (10,'Dan','Welbeck',1,'85 Stratford Rd','02084751569','dan_welbeck@gmail.com');
INSERT INTO `customer` VALUES (11,'Iqbal','Chodhary',1,'9 Swindon Close','02085671188','i.iqbal@hotmail.com');
INSERT INTO `customer` VALUES (12,'James','Gordon',2,'69 Chingwell Rd','07353210089','james_g@gmail.com');
INSERT INTO `customer` VALUES (13,'Tony','Montana',1,'1 North Rd','02073217801','tony.montana@hotmail.co.uk');
INSERT INTO `customer` VALUES (14,'Sunny','Sing',2,'103 Hollywood Rd','02073638701','sunny_sing1@hotmail.co.uk');
INSERT INTO `customer` VALUES (15,'Jeff','Meng',1,'134 Coopes Lane','0764542311','jeffmeng123@gmail.com');
CREATE TABLE Warranty ( 
	id                   integer NOT NULL  ,
	name                 text   , "address" TEXT, "dateOfExpiry" DATETIME,
	CONSTRAINT Pk_Warranty PRIMARY KEY ( id )
 );
INSERT INTO `Warranty` VALUES (1,'Romford Merc PLC','212 Chadwell Heath Rd RM8 8BW','13/10/18');
INSERT INTO `Warranty` VALUES (2,'BMW  Bluewater','unit 2 Water Bay St WA3 9LP','01/09/17');
INSERT INTO `Warranty` VALUES (3,'Tesla Showroom Mayfair','7 Bond Street UX8 9AB','22/04/19');
INSERT INTO `Warranty` VALUES (4,'Honda Hackney Auto','101 Hoe StreetNW9 0EW','02/01/18');
INSERT INTO `Warranty` VALUES (5,'Honda Romford ','10 Romford Rd RM7 9MK','01/09/2017');
INSERT INTO `Warranty` VALUES (6,'VolksWagon Forest Gate','1 Mannor Park High St E17 9AK','11/11/2017');
INSERT INTO `Warranty` VALUES (7,'Toyota Thurdock','Unit E Riverside Avenue TR9 8YG','01/03/2018');
INSERT INTO `Warranty` VALUES (8,'Skoda TLC','07 East Ham EH1 2FB','12/11/17');
CREATE TABLE "VehicleTemplate" ("Model" TEXT,"Make" TEXT,"EngineSize" DOUBLE,"FuelType" TEXT);
INSERT INTO `VehicleTemplate` VALUES ('Civic','Honda',1.6,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Focus','Ford',1.2,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Fiesta','Ford',1.2,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('Jazz','Honda',1.4,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('3-series','BMW',2.0,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('5-series','BMW',2.2,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('C-class','Mercedes Benz',2.2,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('A-class','Mercedes Benz',1.8,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('Polo','VW',1.2,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Golf','VW',2.0,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('up','VW',1.0,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Transporter ','VW',1.8,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('Yaris','Toyota',1.2,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Prius','Toyota',1.4,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Prius plus','Toyota',1.8,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Astra','Vauxhall',1.6,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('Corsa','Vauxhall',1.2,'Petrol');
INSERT INTO `VehicleTemplate` VALUES ('A3','Audi',2.0,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('X5','BMW',2.2,'Diesel');
INSERT INTO `VehicleTemplate` VALUES ('Q6','Audi',2.0,'Diesel');
CREATE TABLE "Vehicle" (
	`id`	integer NOT NULL,
	`regNumber`	text,
	`warrantyId`	integer,
	`model`	TEXT,
	`make`	TEXT,
	`engineSize`	INTEGER,
	`fuelType`	TEXT,
	`colour`	TEXT,
	`MotRenewalDate`	DATETIME,
	`lastServiceDate`	DATETIME,
	`currentMileage`	INTEGER,
	`vehicleType`	INTEGER,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`warrantyId`) REFERENCES `Warranty`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `Vehicle` VALUES (1,'EJ09 VKM',1,'S-Class','Mercedes',2,'Diesel','Silver','01/03/2017','15/05/2017',2000,'Car');
INSERT INTO `Vehicle` VALUES (2,'W241 NAY','','Itallia','Ferarri',2.4,'Diesel','Red','21/01/2017','06/06/2017',6000,'Car');
INSERT INTO `Vehicle` VALUES (3,'MN09 FWQ',2,'1-Series','BMW',2,'Diesel','Grey','02/02/2017','05/05/2017',10000,'Van');
INSERT INTO `Vehicle` VALUES (4,'WE08 ASD',NULL,'Megane','Renault',1.8,'Petrol','Navy','14/09/2016','20/11/2016',23000,'Car');
INSERT INTO `Vehicle` VALUES (5,'SD02 SDW',NULL,'Golf','VW',1.6,'Petrol','Black','11/04/2016','02/09/2016',100000,'Van');
INSERT INTO `Vehicle` VALUES (6,'MG17 IGE',3,'X','Tesla',6,'Diesel','White','18/06/2017','08/08/2017',5500,'Car');
INSERT INTO `Vehicle` VALUES (7,'FW06 DFW','','S60','Volvo',1.8,'Petrol','Green','30/01/2016','22/05/2017',67500,'Truck');
INSERT INTO `Vehicle` VALUES (8,'VC02 IES','','Focus','Ford',1.6,'Diesel','Black','19/02/2015','06/09/2015',78000,'Car');
INSERT INTO `Vehicle` VALUES (9,'SD01 WER',4,'Jazz','Honda',1,'Petrol','Silver','12/02/2017','25/12/2017',135000,'Truck');
INSERT INTO `Vehicle` VALUES (10,'CA12 SFJ',5,'Civic','Honda',1.8,'Petrol','Red','01/01/2016','03/03/2016',13650,'Car');
INSERT INTO `Vehicle` VALUES (11,'QW03 OKL',NULL,'I20','Hyundai',1.4,'Petrol','Blue','24/03/2016','20/07/2016',62200,'Van');
INSERT INTO `Vehicle` VALUES (12,'ZX05 ASC',NULL,'206','Pegout',1.1,'Diesel','Yellow','03/06/2016','23/09/2016',20000,'Car');
INSERT INTO `Vehicle` VALUES (13,'AS01 LOP',6,'Transporter','VW',1.6,'Diesel','White','22/09/2016','03/05/2016',110000,'Van');
INSERT INTO `Vehicle` VALUES (14,'EB11 XAS',7,'Prius','Toyota',1.4,'Disel','Grey','16/01/2016','18/08/2016',98000,'Car');
INSERT INTO `Vehicle` VALUES (15,'DC12 NLL',8,'Yeti','Skoda',2,'Petrol','White','29/03/2015','27/12/2015',92000,'Truck');
CREATE TABLE `SPC` (
	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`NAME`	TEXT,
	`ADDRESS`	TEXT,
	`PHONE`	TEXT,
	`EMAIL`	TEXT
);
INSERT INTO `SPC` VALUES (1,'SPC-1','101 Mile End Rd','02082307673','SPC-1@gmail.com');
INSERT INTO `SPC` VALUES (2,'SPC-2','12 West Way','02085632498','SPC-2@gmail.com');
INSERT INTO `SPC` VALUES (3,'SPC-3','15 Auriol Drive','02082647932','SPC-3@gmail.com');
INSERT INTO `SPC` VALUES (4,'SPC-4','60 Gainsford St','02089933647','SPC-4@gmail.com');
INSERT INTO `SPC` VALUES (5,'SPC-5','69 Clissold Rd','02084132288','SPC-5@gmail.com');
INSERT INTO `SPC` VALUES (6,'SPC-6','110 Walden Way','02082698746','SPC-6@gmail.com');
INSERT INTO `SPC` VALUES (7,'SPC-7','28 Ravington Rd','02031644411','SPC-7@gmail.com');
INSERT INTO `SPC` VALUES (8,'SPC-8','20 Bilton Rd','02082966447','SPC-8@gmail.com');
INSERT INTO `SPC` VALUES (9,'SPC-9','50 Ashford Lane','02082337982','SPC-9@gmail.com');
INSERT INTO `SPC` VALUES (10,'SPC-10','222 North Lane','02082163258','SPC-10@gmail.com');
CREATE TABLE "Repair" (
	`RepairID`	INTEGER NOT NULL,
	`InstallDate`	TEXT,
	`WarrantyDate`	TEXT,
	`CustomerID`	INTEGER,
	`BookingID`	INTEGER,
	PRIMARY KEY(`RepairID`),
	FOREIGN KEY(`CustomerID`) REFERENCES `customer`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(`BookingID`) REFERENCES `Booking`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `Repair` VALUES (1,'28/09/2013','28/09/2014',1,1);
INSERT INTO `Repair` VALUES (2,'29/07/2013','29/07/2014',2,2);
INSERT INTO `Repair` VALUES (3,'25/06/2013','25/06/2014',3,3);
INSERT INTO `Repair` VALUES (4,'27/03/2017','27/03/2018',4,4);
INSERT INTO `Repair` VALUES (5,'26/05/2017','26/05/2018',5,5);
INSERT INTO `Repair` VALUES (6,'24/05/2014','24/05/2015',6,6);
INSERT INTO `Repair` VALUES (7,'17/03/2017','14/03/2018',7,7);
INSERT INTO `Repair` VALUES (8,'16/03/2016','16/03/2017',8,8);
INSERT INTO `Repair` VALUES (9,'18/03/2017','18/03/2018',9,9);
INSERT INTO `Repair` VALUES (10,'18/05/2017','16/05/2018',10,10);
CREATE TABLE "Parts" (
	`id`	integer NOT NULL,
	`name`	text,
	`description`	TEXT, "PartCost" INTEGER, "StockLevel" INTEGER,
	PRIMARY KEY(`id`)
);
INSERT INTO `Parts` VALUES (1,'Clutch','description of clutch',100,20);
INSERT INTO `Parts` VALUES (2,'Brake','description of brake',100,20);
INSERT INTO `Parts` VALUES (3,'Wheel','description of wheel',100,20);
INSERT INTO `Parts` VALUES (4,'Bumper','description of bumper',100,20);
INSERT INTO `Parts` VALUES (5,'Engine','description of engine',100,20);
INSERT INTO `Parts` VALUES (6,'Exhaust','description of exhaust',100,20);
INSERT INTO `Parts` VALUES (7,'Shocks','desciption of shocks',100,20);
INSERT INTO `Parts` VALUES (8,'Gear Box','description of gear box',100,20);
INSERT INTO `Parts` VALUES (9,'Radiator','description of radiator',100,20);
INSERT INTO `Parts` VALUES (10,'Windscreen','description of windscreen',100,19);
CREATE TABLE Paid ( 
	id                   integer NOT NULL  ,
	name                 varchar(100)   ,
	CONSTRAINT Pk_Paid PRIMARY KEY ( id )
 );
CREATE TABLE "Inventory" ("PartsID" INTEGER, "RepairID" INTEGER);
INSERT INTO `Inventory` VALUES (1,1);
INSERT INTO `Inventory` VALUES (2,2);
INSERT INTO `Inventory` VALUES (3,3);
INSERT INTO `Inventory` VALUES (4,4);
INSERT INTO `Inventory` VALUES (5,5);
INSERT INTO `Inventory` VALUES (6,6);
INSERT INTO `Inventory` VALUES (7,7);
INSERT INTO `Inventory` VALUES (8,8);
INSERT INTO `Inventory` VALUES (9,9);
INSERT INTO `Inventory` VALUES (10,10);
CREATE TABLE BookingType ( 
	id                   integer NOT NULL  ,
	name                 text   ,
	CONSTRAINT Pk_BookingType PRIMARY KEY ( id )
 );
INSERT INTO `BookingType` VALUES (1,'maintennace');
INSERT INTO `BookingType` VALUES (2,'repair');
CREATE TABLE "Booking" (
	`id`	integer NOT NULL,
	`bookingType`	integer,
	`paid`	integer,
	`date`	TEXT,
	`balance`	real,
	`customerVehicle`	integer,
	`partsID`	integer,
	`spcID`	integer,
	`sentOut`	integer,
	`returnstatus`	integer,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`bookingType`) REFERENCES `BookingType`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(`paid`) REFERENCES `Paid`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(`customerVehicle`) REFERENCES `customerVehicle`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(`partsID`) REFERENCES `Parts`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(`spcID`) REFERENCES `SPC`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `Booking` VALUES (1,2,0,'2017-03-19',87.0,1,1,2,'',1);
INSERT INTO `Booking` VALUES (2,2,1,'2017-03-17',35.0,2,2,5,NULL,1);
INSERT INTO `Booking` VALUES (3,2,1,'2017-01-12',90.0,3,3,7,'',1);
INSERT INTO `Booking` VALUES (4,2,1,'2017-02-05',68.0,4,5,6,NULL,1);
INSERT INTO `Booking` VALUES (5,2,1,'2016-01-09',90.0,6,7,9,NULL,1);
INSERT INTO `Booking` VALUES (6,2,1,'
2017-02-12',65.0,7,9,10,'',1);
INSERT INTO `Booking` VALUES (7,2,1,'2017-01-22',100.0,8,8,8,NULL,1);
INSERT INTO `Booking` VALUES (8,2,1,'2017-01-01',250.0,5,4,1,'',1);
INSERT INTO `Booking` VALUES (9,2,1,'2017-01-18',125.0,9,6,3,'',1);
INSERT INTO `Booking` VALUES (10,2,1,'2017-03-03',50.0,10,10,4,'',1);
INSERT INTO `Booking` VALUES (11,2,0,'2017-09-25',165.0,1,6,1,NULL,0);
INSERT INTO `Booking` VALUES (12,2,0,'2017-09-16',95.0,2,8,2,NULL,0);
INSERT INTO `Booking` VALUES (13,2,0,'2017-07-23',59.99,3,4,3,NULL,0);
INSERT INTO `Booking` VALUES (14,2,0,'2017-06-05',89.99,4,9,4,NULL,0);
INSERT INTO `Booking` VALUES (15,2,0,'2017-11-13',35.0,5,3,5,NULL,0);
INSERT INTO `Booking` VALUES (16,2,0,'2017-12-19',69.99,6,2,6,NULL,0);
INSERT INTO `Booking` VALUES (17,2,0,'2017-08-21',150.0,7,1,7,NULL,0);
INSERT INTO `Booking` VALUES (18,2,0,'2017-08-20',225.0,8,6,8,NULL,0);
INSERT INTO `Booking` VALUES (19,2,0,'2017-07-07',140.0,9,10,9,NULL,0);
INSERT INTO `Booking` VALUES (20,2,0,'2017-12-04',79.99,10,5,10,NULL,0);
CREATE TABLE "Admin" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`firstName`	TEXT,
	`lastName`	TEXT,
	`pass`	TEXT,
	`Type`	INTEGER,
	`lastLoginDate`	TEXT
);
INSERT INTO `Admin` VALUES (12345,'Jonathan','Smith','admin',1,'Sunday 19th March 2017  2:34:01 PM');
INSERT INTO `Admin` VALUES (54321,'Joe','Black','user',2,'Friday 17th March 2017  9:10:45 PM');
CREATE INDEX idx_customerVehicle_0 ON customerVehicle ( vehicleID );
CREATE INDEX idx_customerVehicle ON customerVehicle ( customerID );
CREATE INDEX idx_Vehicle ON Vehicle ( warrantyId )


;
COMMIT;
