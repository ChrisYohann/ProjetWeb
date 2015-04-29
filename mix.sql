DROP TABLE panier;

DROP TABLE reservation;

DROP TABLE achat;

DROP TABLE place;

DROP TABLE representation;

DROP TABLE rang;

DROP TABLE ticket;

DROP TABLE categorie;

DROP TABLE dossier;

DROP SEQUENCE doss_seq;

DROP TABLE programmeur;

DROP TABLE utilisateur;

DROP TABLE salle;

DROP TABLE affiche;

DROP TABLE spectacle;

--DROP SEQUENCE spec_seq;


CREATE TABLE spectacle (
	numSpect int AUTO_INCREMENT primary key check(numSpect>0),
	nomSpect varchar(30) NOT NULL,
        description varchar(200))CHARACTER SET utf8 COLLATE utf8_bin; 

CREATE TABLE affiche(
        numSpect int primary key references spectacle(numSpect) on DELETE CASCADE,
        image varchar(60),
        check(numSpect >0));

CREATE TABLE salle ( 
	numSalle int primary key check(0<numSalle and numSalle<4));

CREATE TABLE utilisateur ( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	AdresseUt varchar(50), 
	mdpUt varchar(100) NOT NULL);

CREATE TABLE programmeur( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	AdresseUt varchar(50), 
	mdpUt varchar(100) NOT NULL);

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
        CONSTRAINT chk_salle CHECK (0<numSalle and numSalle<4 and numRang>0 and numRang<16));

CREATE TABLE representation(
	numSpect int REFERENCES spectacle(numSpect) ON DELETE CASCADE,
        nbrPlace int  check(nbrPlace>71),
	jour DATE,
	heure int,
	numSalle int references salle(numSalle),
        dernierPO int check(dernierPO<11),
        dernierPP int check(dernierPO<11),
        dernierPB int check(dernierPO<11),
        dernierRO int check(dernierPO<6),
        dernierRP int check(dernierPO<13),
        dernierRB int check(dernierPO<16),
        primary key (jour,heure,numSalle),
	CONSTRAINT chk_repres CHECK (0<numSalle and numSalle<4 and 13<heure and heure<22));
	
CREATE TABLE place ( 
	numSalle int check(0<numSalle and numSalle<4),
	numRang int check(0<numRang and numRang<16),
	numPlace int check(0<numPlace and numPlace<11),
        jour DATE,
        heure int check(13<heure and heure<22),
	PRIMARY KEY (numSalle, numRang, numPlace,jour,heure),
        Foreign KEY(jour,heure,numSalle) references representation(jour,heure,numSalle) ON DELETE CASCADE,
	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE);
	
CREATE TABLE achat(
	login varchar(30) REFERENCES utilisateur(login) ON DELETE CASCADE,
	numDossier int, 
	numTicket int,
	FOREIGN KEY(numTicket, numDossier) REFERENCES ticket(numTicket, numDossier) ON DELETE CASCADE,
	numSpect int REFERENCES spectacle(numSpect) ON DELETE CASCADE,
	jour DATE,
	heure int,
	numSalle int,
	numRang int,
	numPlace int,
        Primary Key(login, numDossier, numTicket),
	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE,
	Foreign Key(numSalle, numRang, numPlace,jour,heure) references place(numSalle, numRang, numPlace,jour,heure) ON DELETE 		CASCADE,
	CONSTRAINT chk_achat CHECK (numTicket>0 and numDossier>0 and 0<numSalle and numSalle<4 and 13<heure and heure<22 and numRang>0 and numRang<11 and 0<numPlace and numPlace<11));



--"ALTER TABLE spectacle AUTO_INCREMENT=1;
	
CREATE TABLE reservation(
	login varchar(30) REFERENCES utilisateur(login) ON DELETE CASCADE,
        numDossier int, 
	numTicket int,
	FOREIGN KEY(numTicket, numDossier) REFERENCES ticket(numTicket, numDossier) ON DELETE CASCADE,
	numSpect int REFERENCES spectacle(numSpect) ON DELETE CASCADE,
	jour DATE,
	heure int  check(13<heure and heure<22),
	numSalle int check(0<numSalle and numSalle<4),
	numRang int check(0<numRang and numRang<11),
	numPlace int check(0<numPlace and numPlace<11),
        Primary Key(login, numDossier, numTicket),
	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE,
	Foreign Key(numSalle, numRang, numPlace,jour,heure) references place(numSalle, numRang, numPlace,jour,heure) ON DELETE CASCADE);

--CREATE TABLE panier(
  --      idPanier int primary key,
--	login varchar(30) REFERENCES utilisateur(login) ON DELETE CASCADE,
--	numSpect int REFERENCES spectacle(numSpect) ON DELETE CASCADE,
--	jour DATE,
--	heure int  check(13<heure and heure<22),
--	numSalle int check(0<numSalle and numSalle<4),
--	numRang int check(0<numRang and numRang<11),
--	numPlace int check(0<numPlace and numPlace<21),
--	Foreign KEY(numSalle, numRang) references rang(numSalle, numRang) ON DELETE CASCADE,
--	Foreign Key(numSalle, numRang, numPlace) references place(numSalle, numRang, numPlace) ON DELETE CASCADE);
INSERT INTO utilisateur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('root', '', '', '', '133daaa226d243e140d16fc1fd59189c7eb605a6b780552ea45ddbfcc6c1b1ba');
INSERT INTO utilisateur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('yohann', 'hako',  'root', '', '133daaa226d243e140d16fc1fd59189c7eb605a6b780552ea45ddbfcc6c1b1ba');
INSERT INTO programmeur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('root', '', '', '', '133daaa226d243e140d16fc1fd59189c7eb605a6b780552ea45ddbfcc6c1b1ba');

























