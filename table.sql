CREATE TABLE spectacle (
	numSpect int  primary key check(numSpect>0), 
	nomSpect varchar(30) NOT NULL, 
	nbrPlace int  check(nbrPlace>71));

CREATE TABLE dateT ( 
	jour DATE, 
	heure int  check(13<heure and heure<22),
	primary key(jour, heure));

CREATE TABLE salle ( 
	numSalle int primary key check(0<numSalle and numSalle<4));

CREATE TABLE utilisateur ( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	@Ut varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE programmeur( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	@Ut varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE client( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	@Ut varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE dossier ( 
	numDossier int primary key check(numDossier>0));
	
CREATE TABLE ticket( 
	numTicket int primary key check(numTicket>0), 
	numDossier int references dossier ON DELETE CASCADE check(numDossier>0));

CREATE TABLE rang ( 
	numSalle int references salle ON DELETE CASCADE check(0<numSalle and numSalle<4),
	numRang int check(0<numRang and numRang<11),
	PRIMARY KEY (numSalle, numRang));
	
