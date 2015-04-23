CREATE TABLE spectacle (
	numSpect int  primary key check(numSpect>0), 
	nomSpect varchar(30) NOT NULL, 
	nbrPlace int  check(nbrPlace>71));

CREATE TABLE dateT ( 
	jour DATE, 
	heure TIME,
	primary key(jour, heure));

CREATE TABLE salle ( 
	numSalle int primary key check(0<numSalle and numSalle<4));

CREATE TABLE utilisateur ( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	AdresseUt varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE programmeur( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	AdresseUt varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE client( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	AdresseUt varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE dossier ( 
	numDossier int primary key check(numDossier>0));
	
CREATE TABLE ticket( 
	numTicket int primary key check(numTicket>0),
        numDossier int check(numDossier>0), 
	foreign key (numDossier) references dossier(numDossier) ON DELETE CASCADE );

CREATE TABLE rang ( 
	numSalle int references salle(numSalle) ON DELETE CASCADE,
	numRang int,
	PRIMARY KEY (numSalle, numRang),
        CONSTRAINT chk_salle CHECK (0<numSalle and numSalle<4 and numRang>0 and numRang<11));

CREATE TABLE place ( 
	numSalle int check(0<numSalle and numSalle<4),
	numRang int check(0<numRang and numRang<11),
	numPlace int check(0<numPlace and numPlace<21),
	PRIMARY KEY (numSalle, numRang, numPlace),
	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE);
	
INSERT INTO utilisateur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('root', 'root',  'root', '', 'root');
INSERT INTO utilisateur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('yohann', 'hako',  'root', '', 'root');