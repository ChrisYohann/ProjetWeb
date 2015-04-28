INSERT INTO spectacle (nomSpect,description) VALUES ('voiliiiii','youhouuuuu');
select * from spectacle;

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
	mdpUt varchar(20) NOT NULL);

CREATE TABLE programmeur( 
	login varchar(30) primary key, 
	nomUt varchar(30) NOT NULL, 
	prenomUt varchar(30) NOT NULL, 
	AdresseUt varchar(50), 
	mdpUt varchar(20) NOT NULL);

CREATE TABLE dossier ( 
	numDossier int check(numDossier>0),
	nomDossier varchar(20));

ALTER TABLE dossier ADD(
	CONSTRAINT doss_pk PRIMARY KEY(numDossier));

CREATE SEQUENCE doss_seq;

--puis faire la 2e manip manuellment