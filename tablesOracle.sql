CREATE TABLE spectacle (
	numSpect int check(numSpect>0),
	nomSpect varchar(30) NOT NULL,
        description varchar(200));--CHARACTER SET utf8 COLLATE utf8_bin; 

ALTER TABLE spectacle ADD(
	CONSTRAINT spec_pk PRIMARY KEY(numSpect));

CREATE SEQUENCE spec_seq;
--puis lancer le fichier oracle1 manuellement

--INSERT INTO dossier (nomDossier) VALUES ('voiliiiii');
--select * from spectacle;

