-- Caller Volume
create table CallerVolume (
	cid int not null auto_increment primary key,
	numberofcalls int,
	State varchar(255),
	CallsDate date
	);


-- Damaged Packages
create table DamagedPackages (
	date Date not null primary key,
	NumberDetected int,
	NumberReported int
	);

-- Refuel Costs
create table RefuelCosts (
	date Date NOT NULL PRIMARY KEY,
	CostOfGasPerGGE decimal (18, 3),
	CostOfElectricityPerGGE decimal (18, 3),
	CostOfNaturalGasPerGGE decimal (18, 3)
	);

-- Returns After Delivery
create table Returns (
	ReturnDate date not null primary key,
	NumberOfReturns int
	);

-- Time Saved on Returns
create table TimeSaved (
	ExpectedDeliveryDate date,
	DateDamagedPackageDetected date,
	DifferenceBetweenDates int
	);
	
-- View for Caller Volume
CREATE VIEW v_CallerVolume AS
	SELECT numberofcalls, State, CallsDate 
	FROM CallerVolume;
	
-- Procedure to query quarter
DELIMITER $$
CREATE PROCEDURE queryquarter (IN start DATE, IN end DATE)
BEGIN
	SELECT CallerVolume.State, SUM(CallerVolume.numberofcalls) 
	FROM CallerVolume 
	WHERE CallsDate BETWEEN start AND end
	GROUP BY CallerVolume.State;
END$$
DELIMITER ;

