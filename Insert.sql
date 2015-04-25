INSERT INTO utilisateur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('root', 'root',  'root', '', 'root');
INSERT INTO utilisateur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('yohann', 'hako',  'root', '', 'root');
INSERT INTO programmeur (login, nomUt, prenomUt,AdresseUt,mdpUt) VALUES ('root', 'root',  'root', '', 'root');
INSERT INTO spectacle(numSpect,nomSpect,description) VALUES (1,'Spectacle 1','Description Spectacle 1');
INSERT INTO spectacle(numSpect,nomSpect,description) VALUES (2,'Spectacle 2','Description Spectacle 2');
INSERT INTO spectacle(numSpect,nomSpect,description) VALUES (3,'Spectacle 3','Description Spectacle 3');
 insert into representation (numSpect,nbrPlace,jour,heure,numSalle) VALUES(1,90,'2015-04-24',17,1);
 insert into representation (numSpect,nbrPlace,jour,heure,numSalle) VALUES(2,90,'2015-04-24',19,1);
 insert into representation (numSpect,nbrPlace,jour,heure,numSalle) VALUES(2,90,'2016-04-24',19,1);
insert into representation (numSpect,nbrPlace,jour,heure,numSalle) VALUES(1,90,'2015-01-05',17,3); 
--insert into representation (numSpect,nbrPlace,jour,heure,numSalle) VALUES(1,90,to_date('2015-04-24','yyyy-mm-dd'),17,1);
--insert into representation (numSpect,nbrPlace,jour,heure,numSalle) VALUES(1,90,to_date('2015-24-04','yyyy-dd-mm'),17,1);
--INSERT INTO dateT(jour,heure) VALUES(to_date('2016-24-04', 'yyyy-dd-mm'),17);
--insert into representation(numSpect, nbrPlace,jour,heure,numSalle) VALUES(1,90,'24-APR-15',17,1)
