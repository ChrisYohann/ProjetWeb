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
	AdresseUt varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE programmeur( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	AdresseUt varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE dossier ( 
	numDossier int primary key check(numDossier>0));

CREATE TABLE categorie (
	catTarif varchar(20) primary key check(catTarif in('orchestre','balcon','poulailler')),
	tarif int check(tarif in('40','20','10')));
	
CREATE TABLE ticket( 
	numTicket int check(numTicket>0),
        numDossier int check(numDossier>0), 
        PRIMARY KEY (numTicket, numDossier),
	foreign key (numDossier) references dossier(numDossier) ON DELETE CASCADE );

CREATE TABLE rang ( 
	numSalle int references salle(numSalle) ON DELETE CASCADE,
	numRang int,
	catTarif varchar(20) check(catTarif in('orchestre','balcon','poulailler')),
	PRIMARY KEY (numSalle, numRang),
	FOREIGN KEY (catTarif) references categorie(catTarif) ON DELETE CASCADE,
        CONSTRAINT chk_salle CHECK (0<numSalle and numSalle<4 and numRang>0 and numRang<11));
	
CREATE TABLE place ( 
	numSalle int check(0<numSalle and numSalle<4),
	numRang int check(0<numRang and numRang<11),
	numPlace int check(0<numPlace and numPlace<21),
	PRIMARY KEY (numSalle, numRang, numPlace),
	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE);
	
CREATE TABLE achat(
	login varchar(30) REFERENCES utilisateur(login) ON DELETE CASCADE,
	numDossier int, 
	numTicket int,
	FOREIGN KEY(numTicket, numDossier) REFERENCES ticket(numTicket, numDossier) ON DELETE CASCADE,
	numSpect int REFERENCES spectacle(numSpect) ON DELETE CASCADE,
	jour DATE,
	heure int,
	FOREIGN KEY(jour, heure) references dateT(jour, heure) ON DELETE CASCADE,
	numSalle int,
	numRang int,
	numPlace int,
	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE,
<<<<<<< HEAD
	Foreign Key(numSalle, numRang, numPlace) references place(numSalle, numRang, numPlace) ON DELETE CASCADE,
	CONSTRAINT chk_achat CHECK (numTicket >0 and numDossier>0 and 0<numSalle and numSalle<4 and 13<heure and heure<22 and numRang>0 and numRang<11 and 0<numPlace and numPlace<21));
=======
	Foreign Key(numSalle, numRang, numPlace) references place(numSalle, numRang, numPlace) ON DELETE 		CASCADE,
	CONSTRAINT chk_achat CHECK (numTicket>0 and numDossier>0 and 0<numSalle and numSalle<4 and 13<heure and heure<22 and 		numRang>0 and numRang<11 and 0<numPlace and numPlace<21));
>>>>>>> b72fbb09ea248cabd03dbbfc831e9036057a26ab
	
CREATE TABLE representation(
	numSpect int REFERENCES spectacle(numSpect) ON DELETE CASCADE,
	jour DATE,
	heure int,
	FOREIGN KEY(jour, heure) references dateT(jour, heure) ON DELETE CASCADE,
	numSalle int references salle(numSalle),
	CONSTRAINT chk_repres CHECK (0<numSalle and numSalle<4 and 13<heure and heure<22));
	
CREATE TABLE reservation(
	login varchar(30) REFERENCES utilisateur(login) ON DELETE CASCADE,
	numSpect int REFERENCES spectacle(numSpect) ON DELETE CASCADE,
	jour DATE,
	heure int  check(13<heure and heure<22),
	FOREIGN KEY(jour, heure) references dateT(jour, heure) ON DELETE CASCADE,
	numSalle int check(0<numSalle and numSalle<4),
	numRang int check(0<numRang and numRang<11),
	numPlace int check(0<numPlace and numPlace<21),
	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE,
	Foreign Key(numSalle, numRang, numPlace) references place(numSalle, numRang, numPlace) ON DELETE CASCADE);
