CREATE TABLE Virus 
(
    vName VARCHAR(255) PRIMARY KEY NOT NULL,
    Type VARCHAR(255),
    Danger_Degree INTEGER,
    Annotation VARCHAR(1000),
    CHECK (danger_degree > 0 AND danger_degree < 6)
);

CREATE TABLE Symptom 
(
	sName 			VARCHAR(255),
	description 	VARCHAR(1000),
	PRIMARY KEY (sName)
);

CREATE TABLE Region 
(
	rName VARCHAR(255) PRIMARY KEY NOT NULL
);

CREATE TABLE Institute 
(
	rName VARCHAR(255),
	iName VARCHAR(255),
	Address VARCHAR(255) NOT NULL,
	PRIMARY KEY (rName, iName),
	FOREIGN KEY (rName) REFERENCES Region
);

CREATE TABLE Precaution 
(
	pName varchar(255),
	PRIMARY KEY (pName)
);

CREATE TABLE Prevent
(
	pName 		VARCHAR(255),
	vName 		VARCHAR(255),
	FOREIGN KEY (vName) REFERENCES Virus,
	FOREIGN KEY (pName) REFERENCES Precaution,
	PRIMARY KEY (pName, vName)
);

CREATE TABLE Medium 
(
	mName 		VARCHAR(255),
	Contagious_Level 	INT,
		CHECK (1 <= Contagious_Level AND Contagious_Level <= 5),
	PRIMARY KEY (mName)
);

CREATE TABLE Patient 
(
	Nationality 		VARCHAR(255),
    National_ID		VARCHAR(255),
	Name			VARCHAR(255),    	
	Gender 		CHAR,
	AGE			INT,
	PRIMARY KEY (Nationality, National_ID)
);

CREATE TABLE Vaccination 
(
	rName 		VARCHAR(255),
	iName 			VARCHAR(255),
	Code 			VARCHAR(255),
	Currency 		CHAR(3),
    Price 			INT,
	PRIMARY KEY (rName, iName, Code),
	FOREIGN KEY (rName, iName) REFERENCES Institute(rName, iName)
);

CREATE TABLE Incur
(
	vName VARCHAR(255),
	sName VARCHAR(255),
	FOREIGN KEY (vName) REFERENCES Virus(vName),
	FOREIGN KEY (sName) REFERENCES Symptom(sName),
	PRIMARY KEY (vName, sName)
);

CREATE TABLE Hospital 
(
    rName VARCHAR(255),
    iName VARCHAR(255),
    Address VARCHAR(255) NOT NULL,
    PRIMARY KEY (rName, iName)
);

CREATE TABLE Research_Institute 
(
    rName VARCHAR(255),
    iName VARCHAR(255),
    Address VARCHAR(255) NOT NULL,
    PRIMARY KEY (rName, iName)
);

CREATE TABLE Infect 
(
    vName 	VARCHAR(255),
   	Nationality 	VARCHAR(255),
   	National_ID 	VARCHAR(255),
   	State 		VARCHAR(255) NOT NULL,
   	Begin_Time 	DATE NOT NULL,
  	End_Time 	DATE,
		CHECK(End_Time IS NULL OR Begin_Time <= End_Time),
	PRIMARY KEY (vName, Nationality, National_ID, Begin_Time),
    FOREIGN KEY (vName) REFERENCES Virus (vName),
    FOREIGN KEY (Nationality, National_ID) REFERENCES Patient (Nationality, National_ID)
);

CREATE TABLE Closed_Border 
(
    R1rName 	VARCHAR(255) REFERENCES Region(rName),
    R2rName 	VARCHAR(255) REFERENCES Region(rName),
    Begin_Time 	DATE NOT NULL,
    End_Time 	DATE,
		CHECK(End_Time IS NULL OR Begin_Time <= End_Time),
	PRIMARY KEY (R1rName, R2rName, Begin_Time)
);

CREATE TABLE Exhibit 
(
	sName		VARCHAR(255),
   	Nationality 	VARCHAR(255),
   	National_ID 	VARCHAR(255),
	PRIMARY KEY (sName, Nationality, National_ID),
	FOREIGN KEY (Nationality, National_ID) REFERENCES Patient(Nationality, National_ID),
	FOREIGN KEY (sName) REFERENCES Symptom(sName)
);

CREATE TABLE Apply
(
	rName 	VARCHAR(255) REFERENCES Region(rName),
	pName 	VARCHAR(255) REFERENCES Precaution(pName),
	Begin_Time	DATE NOT NULL,
	End_Time	DATE,
		CHECK(End_Time IS NULL OR Begin_Time <= End_Time),
	PRIMARY KEY (rName, pName, Begin_Time)
);

CREATE TABLE Accommodation
(
	Nationality 	VARCHAR(255),
	National_ID 	VARCHAR(255),
	iName		VARCHAR(255),
	rName		VARCHAR(255),
	Begin_Time	DATE NOT NULL,
	End_Time	DATE,
		CHECK(End_Time IS NULL OR Begin_Time <= End_Time),
	PRIMARY KEY (Nationality, National_ID, iName, rName),
	FOREIGN KEY (Nationality, National_ID) REFERENCES Patient(Nationality, National_ID),
	FOREIGN KEY (iName, rName) REFERENCES Hospital(iName, rName)
);

CREATE TABLE Spread
(
	vName VARCHAR(255),
	mName VARCHAR(255),
	PRIMARY KEY (vName, mName),
	FOREIGN KEY (mName) REFERENCES Medium(mName),
	FOREIGN KEY (vName) REFERENCES Virus(vName)
);
