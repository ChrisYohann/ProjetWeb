/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.*;
import static dao.DAOUtil.closeAll;
import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author chris
 *
 */
public class PayerDaoImpl implements PayerDao {
    
    private static String SQL_ADD_DOSSIER = "INSERT INTO dossier(numDossier) VALUES(?)";
    private static String SQL_ADD_TICKET = "INSERT INTO ticket (numTicket,numDossier) VALUES(?,?)";
    private static String SQL_ADD_ACHAT = "INSERT INTO achat (login, numDossier, numTicket, numSpect,jour, heure, numSalle, numRang, numPlace) VALUES (?,?,?,?,?,?,?,?,?) ";
    private static String SQL_ADD_RESERVATION = "INSERT INTO reservation (login, numDossier, numTicket, numSpect,jour, heure, numSalle, numRang, numPlace) VALUES (?,?,?,?,?,?,?,?,?)  ";
    private static String SQL_ALL_SPECTACLES = "SELECT * FROM spectacle ";
    private static String SQL_DATE_SPECTACLE = "SELECT prez.numSpect,prez.nbrPlace,prez.jour,prez.heure,prez.numSalle from representation prez,spectacle s where s.numSpect = ? and prez.numSpect = s.numSpect ";

    private static String UPDATE_PREZ_ORCHESTRE = "UPDATE representation SET dernierPO = ?,dernierRO = ?,nbrPlace = ? where jour = ? and heure = ? and numSalle = ?";
    private static String UPDATE_PREZ_POULAILLER = "UPDATE representation SET dernierPP = ?,dernierRP = ?,nbrPlace = ? where jour = ? and heure = ? and numSalle = ?";
    private static String UPDATE_PREZ_BALCON = "UPDATE representation SET dernierPB = ?,dernierRB = ?,nbrPlace = ? where jour = ? and heure = ? and numSalle = ?";

    private static String FOREIGN_SALLE = "INSERT INTO salle(numSalle) VALUES (?)";
    private static String FOREIGN_CATEGORIE = "INSERT INTO categorie(catTarif,tarif) VALUES(?,?)";
    private static String FOREIGN_RANG = "INSERT INTO rang(numSalle,numRang,catTarif) VALUES(?,?,?)";
    private static String FOREIGN_PLACE = "INSERT INTO place(numSalle,numRang,numPlace,jour,heure) VALUES (?,?,?,?,?)";
    
    private static String FIND_RESERVATION = "SELECT * FROM reservation where login = ? and jour = ? and heure = ? and numSalle = ?";
    
    private static String SQL_CHECK_PLACE = "SELECT p.numSalle,p.numRang,p.numPlace FROM place p where p.numSalle = ? and p.numRang = ? and p.numPlace = ?";
    private static String COMMIT = " commit" ;
    
    private DAOManager manager;

    public PayerDaoImpl(DAOManager gerant) {
        this.manager = gerant;
    }

    public String date_en_chaine(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    //dans le cas ou l'utilisateur souhaite payer pour les representation en parametre
    @Override
    public void creer(ArrayList<PreReservation> preRes, String login) throws DAOException {
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RepresentationDao represdao = new RepresentationDaoImpl(this.manager);
        

        try {int l;
            connexion = manager.getConnection();
            for (l=0; l < preRes.size(); l++) {
                
           int numDossier = (int) (new Date().getTime() / 10000)-25*l;
                Date date = preRes.get(l).getDate();
                String dateE = date_en_chaine(date);
                int heure = preRes.get(l).getHeure();
                int salle = preRes.get(l).getSalle();
                Representation representation = represdao.trouver(dateE, heure, salle);
                if (representation != null) {
                    int nbrPlace = preRes.get(l).getNbPlace();
                    String cat = preRes.get(l).getCat();
                    switch (cat) {
                        case "orchestre":
                            preparedStatement = initRequete(connexion, SQL_CHECK_PLACE, false, salle, 5, 11 - nbrPlace);
                            resultSet = preparedStatement.executeQuery();
                            if (!resultSet.next()) {
                                //Initialisation d'un numéro de dossier :
                                preparedStatement = initRequete(connexion,SQL_ADD_DOSSIER,true,numDossier);
                                int transpole = preparedStatement.executeUpdate();
                                //1er cas : Tous cote a cote
                                if (11 - representation.getDernierPO() > nbrPlace) {
                                    int i ;
                                    for ( i = 1; i <= nbrPlace; i++) {
                                        //preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, 1, 1 + i);
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRO(), representation.getDernierPO() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi.");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRO(), representation.getDernierPO() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible de créer l'achat");
                                            }
                                        }
                                    }
                                    //ON MET LA BDD A JOUR
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_ORCHESTRE, true, representation.getDernierPO() + i - 1, representation.getDernierRO(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();

                                } else { int i ;
                                    int j = nbrPlace + representation.getDernierPO() - 10;
                                    int rangee_dessous = nbrPlace - j;
                                    for (i = 1; i <= rangee_dessous; i++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRO(), representation.getDernierPO() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRO(), representation.getDernierPO() + i);
                                            statut = preparedStatement.executeUpdate();
                                            
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'effectuer l'achat");
                                            }

                                        }
                                        
                                    }   representation.setDernierPO(0);
                                        representation.setDernierRO(representation.getDernierRO()+1);   
                                        preparedStatement = initRequete(connexion, UPDATE_PREZ_ORCHESTRE, true, 0, representation.getDernierRO(),representation.getNbrPlace(), dateE, heure, salle);
                                        preparedStatement.executeUpdate();


                                    for (int k = 1; k <= j; k++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRO(), k, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i+k-1,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i+k-1, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRO(),k);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'initialiser l'achat");
                                            }

                                        }
                                    }
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_ORCHESTRE, true, j, representation.getDernierRO(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();
                                }

                            }

                            //Si le resultSet est vide, la place est libre
                            break;

                       case "poulailler":
                            preparedStatement = initRequete(connexion, SQL_CHECK_PLACE, false, salle, 5, 11 - nbrPlace);
                            resultSet = preparedStatement.executeQuery();
                            if (!resultSet.next()) {
                                //Initialisation d'un numéro de dossier :
                                preparedStatement = initRequete(connexion,SQL_ADD_DOSSIER,true,numDossier);
                                int transpole = preparedStatement.executeUpdate();
                                //1er cas : Tous cote a cote
                                if (11 - representation.getDernierPP() > nbrPlace) {
                                    int i ;
                                    for ( i = 1; i <= nbrPlace; i++) {
                                        //preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, 1, 1 + i);
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRP(), representation.getDernierPP() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi.");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRP(), representation.getDernierPP() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible de créer l'achat");
                                            }
                                        }
                                    }
                                    //ON MET LA BDD A JOUR
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_POULAILLER, true, representation.getDernierPP() + i - 1, representation.getDernierRP(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();

                                } else { int i ;
                                    int j = nbrPlace + representation.getDernierPP() - 10;
                                    int rangee_dessous = nbrPlace - j;
                                    for (i = 1; i <= rangee_dessous; i++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRP(), representation.getDernierPP() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRP(), representation.getDernierPP() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'effectuer l'achat");
                                            }

                                        }
                                        
                                    }   representation.setDernierPP(0);
                                        representation.setDernierRP(representation.getDernierRP()+1);   
                                        preparedStatement = initRequete(connexion, UPDATE_PREZ_POULAILLER, true, 0, representation.getDernierRP(),representation.getNbrPlace(), dateE, heure, salle);
                                        preparedStatement.executeUpdate();


                                    for (int k = 1; k <= j; k++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRP(), k, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i+k-1,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i+k-1, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRP(),k);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'initialiser l'achat");
                                            }

                                        }
                                    }
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_POULAILLER, true, j, representation.getDernierRP(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();
                                }

                            }

                            //Si le resultSet est vide, la place est libre
                            break;
                           
                    case "balcon":
                            preparedStatement = initRequete(connexion, SQL_CHECK_PLACE, false, salle, 5, 11 - nbrPlace);
                            resultSet = preparedStatement.executeQuery();
                            if (!resultSet.next()) {
                                //Initialisation d'un numéro de dossier :
                                preparedStatement = initRequete(connexion,SQL_ADD_DOSSIER,true,numDossier);
                                int transpole = preparedStatement.executeUpdate();
                                //1er cas : Tous cote a cote
                                if (11 - representation.getDernierPB() > nbrPlace) {
                                    int i ;
                                    for ( i = 1; i <= nbrPlace; i++) {
                                        //preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, 1, 1 + i);
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRB(), representation.getDernierPB() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi.");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRB(), representation.getDernierPB() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible de créer l'achat");
                                            }
                                        }
                                    }
                                    //ON MET LA BDD A JOUR
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_BALCON, true, representation.getDernierPB() + i - 1, representation.getDernierRB(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();

                                } else { int i ;
                                    int j = nbrPlace + representation.getDernierPB() - 10;
                                    int rangee_dessous = nbrPlace - j;
                                    for (i = 1; i <= rangee_dessous; i++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRB(), representation.getDernierPB() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRB(), representation.getDernierPB() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'effectuer l'achat");
                                            }

                                        }
                                        
                                    }   representation.setDernierPB(0);
                                        representation.setDernierRB(representation.getDernierRB()+1);   
                                        preparedStatement = initRequete(connexion, UPDATE_PREZ_BALCON, true, 0, representation.getDernierRB(),representation.getNbrPlace(), dateE, heure, salle);
                                        preparedStatement.executeUpdate();


                                    for (int k = 1; k <= j; k++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRB(), k, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i+k-1,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, true, login, numDossier, i+k-1, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRB(),k);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'initialiser l'achat");
                                            }

                                        }
                                    }
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_BALCON, true, j, representation.getDernierRB(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();
                                }

                            }

                            //Si le resultSet est vide, la place est libre
                            break;
                        default:
                            
                    }

                //repres.add(representation);
                    //if(rep.get(i-1) == null)
                    // throw new DAOException(Integer.toString(rep.size())) ;
                    // preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, false, login, 16, i, representation.getSpect().getNumero(),
                    //          representation.date_en_chaine(representation.getJour()), representation.getHeure(), representation.getNumSalle(), 15, 9);//TODO numrang place et dossier
                            //int sucess = preparedStatement.executeUpdate();//On recherche le login dans la table 
                    //if (sucess == 0) {
                    //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                    //  throw new DAOException("Echec : La reservation correspondant n'a pas été chargée");
                    //}
                }
                else{
                    throw new DAOException("La représentation est nulle"+l);
                }
            }
            //new_user.setInscrit(true);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }

public void reserver(ArrayList<PreReservation> preRes, String login) throws DAOException {
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RepresentationDao represdao = new RepresentationDaoImpl(this.manager);
        

        try {int l;
            connexion = manager.getConnection();
            for (l=0; l < preRes.size(); l++) {
                
           int numDossier = (int) (new Date().getTime() / 10000)-25*l;
                Date date = preRes.get(l).getDate();
                String dateE = date_en_chaine(date);
                int heure = preRes.get(l).getHeure();
                int salle = preRes.get(l).getSalle();
                Representation representation = represdao.trouver(dateE, heure, salle);
                if (representation != null) {
                    int nbrPlace = preRes.get(l).getNbPlace();
                    String cat = preRes.get(l).getCat();
                    switch (cat) {
                        case "orchestre":
                            preparedStatement = initRequete(connexion, SQL_CHECK_PLACE, false, salle, 5, 11 - nbrPlace);
                            resultSet = preparedStatement.executeQuery();
                            if (!resultSet.next()) {
                                //Initialisation d'un numéro de dossier :
                                preparedStatement = initRequete(connexion,SQL_ADD_DOSSIER,true,numDossier);
                                int transpole = preparedStatement.executeUpdate();
                                //1er cas : Tous cote a cote
                                if (11 - representation.getDernierPO() > nbrPlace) {
                                    int i ;
                                    for ( i = 1; i <= nbrPlace; i++) {
                                        //preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, 1, 1 + i);
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRO(), representation.getDernierPO() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi.");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRO(), representation.getDernierPO() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible de créer l'achat");
                                            }
                                        }
                                    }
                                    //ON MET LA BDD A JOUR
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_ORCHESTRE, true, representation.getDernierPO() + i - 1, representation.getDernierRO(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();

                                } else { int i ;
                                    int j = nbrPlace + representation.getDernierPO() - 10;
                                    int rangee_dessous = nbrPlace - j;
                                    for (i = 1; i <= rangee_dessous; i++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRO(), representation.getDernierPO() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRO(), representation.getDernierPO() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'effectuer l'achat");
                                            }

                                        }
                                        
                                    }   representation.setDernierPO(0);
                                        representation.setDernierRO(representation.getDernierRO()+1);   
                                        preparedStatement = initRequete(connexion, UPDATE_PREZ_ORCHESTRE, true, 0, representation.getDernierRO(),representation.getNbrPlace(), dateE, heure, salle);
                                        preparedStatement.executeUpdate();


                                    for (int k = 1; k <= j; k++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRO(), k, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i+k-1,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i+k-1, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRO(),k);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'initialiser l'achat");
                                            }

                                        }
                                    }
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_ORCHESTRE, true, j, representation.getDernierRO(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();
                                }

                            }

                            //Si le resultSet est vide, la place est libre
                            break;

                       case "poulailler":
                            preparedStatement = initRequete(connexion, SQL_CHECK_PLACE, false, salle, 5, 11 - nbrPlace);
                            resultSet = preparedStatement.executeQuery();
                            if (!resultSet.next()) {
                                //Initialisation d'un numéro de dossier :
                                preparedStatement = initRequete(connexion,SQL_ADD_DOSSIER,true,numDossier);
                                int transpole = preparedStatement.executeUpdate();
                                //1er cas : Tous cote a cote
                                if (11 - representation.getDernierPP() > nbrPlace) {
                                    int i ;
                                    for ( i = 1; i <= nbrPlace; i++) {
                                        //preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, 1, 1 + i);
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRP(), representation.getDernierPP() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi.");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRP(), representation.getDernierPP() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible de créer l'achat");
                                            }
                                        }
                                    }
                                    //ON MET LA BDD A JOUR
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_POULAILLER, true, representation.getDernierPP() + i - 1, representation.getDernierRP(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();

                                } else { int i ;
                                    int j = nbrPlace + representation.getDernierPP() - 10;
                                    int rangee_dessous = nbrPlace - j;
                                    for (i = 1; i <= rangee_dessous; i++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRP(), representation.getDernierPP() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRP(), representation.getDernierPP() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'effectuer l'achat");
                                            }

                                        }
                                        
                                    }   representation.setDernierPP(0);
                                        representation.setDernierRP(representation.getDernierRP()+1);   
                                        preparedStatement = initRequete(connexion, UPDATE_PREZ_POULAILLER, true, 0, representation.getDernierRP(),representation.getNbrPlace(), dateE, heure, salle);
                                        preparedStatement.executeUpdate();


                                    for (int k = 1; k <= j; k++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRP(), k, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i+k-1,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i+k-1, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRP(),k);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'initialiser l'achat");
                                            }

                                        }
                                    }
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_POULAILLER, true, j, representation.getDernierRP(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();
                                }

                            }

                            //Si le resultSet est vide, la place est libre
                            break;
                           
                    case "balcon":
                            preparedStatement = initRequete(connexion, SQL_CHECK_PLACE, false, salle, 5, 11 - nbrPlace);
                            resultSet = preparedStatement.executeQuery();
                            if (!resultSet.next()) {
                                //Initialisation d'un numéro de dossier :
                                preparedStatement = initRequete(connexion,SQL_ADD_DOSSIER,true,numDossier);
                                int transpole = preparedStatement.executeUpdate();
                                //1er cas : Tous cote a cote
                                if (11 - representation.getDernierPB() > nbrPlace) {
                                    int i ;
                                    for ( i = 1; i <= nbrPlace; i++) {
                                        //preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, 1, 1 + i);
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRB(), representation.getDernierPB() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi.");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRB(), representation.getDernierPB() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible de créer l'achat");
                                            }
                                        }
                                    }
                                    //ON MET LA BDD A JOUR
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_BALCON, true, representation.getDernierPB() + i - 1, representation.getDernierRB(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();

                                } else { int i ;
                                    int j = nbrPlace + representation.getDernierPB() - 10;
                                    int rangee_dessous = nbrPlace - j;
                                    for (i = 1; i <= rangee_dessous; i++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRB(), representation.getDernierPB() + i, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRB(), representation.getDernierPB() + i);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'effectuer l'achat");
                                            }

                                        }
                                        
                                    }   representation.setDernierPB(0);
                                        representation.setDernierRB(representation.getDernierRB()+1);   
                                        preparedStatement = initRequete(connexion, UPDATE_PREZ_BALCON, true, 0, representation.getDernierRB(),representation.getNbrPlace(), dateE, heure, salle);
                                        preparedStatement.executeUpdate();


                                    for (int k = 1; k <= j; k++) {
                                        preparedStatement = initRequete(connexion, FOREIGN_PLACE, true, salle, representation.getDernierRB(), k, dateE, heure);
                                        int statut = preparedStatement.executeUpdate();
                                        if (statut == 0) {
                                            throw new DAOException("On peut plus rien faire pour toi encore une fois");
                                        } else {
                                            preparedStatement = initRequete(connexion,SQL_ADD_TICKET,true,i+k-1,numDossier);
                                            statut = preparedStatement.executeUpdate();
                                            preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, true, login, numDossier, i+k-1, representation.getSpect().getNumero(), dateE, heure, salle, representation.getDernierRB(),k);
                                            statut = preparedStatement.executeUpdate();
                                            if (statut == 0) {
                                                throw new DAOException("Impossible d'initialiser l'achat");
                                            }

                                        }
                                    }
                                    preparedStatement = initRequete(connexion, UPDATE_PREZ_BALCON, true, j, representation.getDernierRB(),representation.getNbrPlace()-nbrPlace, dateE, heure, salle);
                                    int statut = preparedStatement.executeUpdate();
                                }

                            }

                            //Si le resultSet est vide, la place est libre
                            break;
                        default:
                            
                    }

                //repres.add(representation);
                    //if(rep.get(i-1) == null)
                    // throw new DAOException(Integer.toString(rep.size())) ;
                    // preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, false, login, 16, i, representation.getSpect().getNumero(),
                    //          representation.date_en_chaine(representation.getJour()), representation.getHeure(), representation.getNumSalle(), 15, 9);//TODO numrang place et dossier
                            //int sucess = preparedStatement.executeUpdate();//On recherche le login dans la table 
                    //if (sucess == 0) {
                    //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                    //  throw new DAOException("Echec : La reservation correspondant n'a pas été chargée");
                    //}
                }
                else{
                    throw new DAOException("La représentation est nulle"+l);
                }
            }
            //new_user.setInscrit(true);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }   
    
    /*public List<Reservation> afficher_reservations(String login,Representation prez) throws DAOException{
     Connection connexion = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     Spectacle festival = null;

     try {
     connexion = manager.getConnection();
     preparedStatement = initRequete(connexion, FIND_RESERVATION, false,login,prez.date_en_chaine(prez.getJour()),prez.getHeure(),prez.getNumSalle());
     resultSet = preparedStatement.executeQuery();
     while (resultSet.next()) {
     festival = this.link(resultSet);
     festival.setRepresentation(this.associer_representations(festival.getNumero()));
     spectacles_list.add(festival);
     }

     } catch (SQLException e) {
     throw new DAOException(e);
     } finally {
     closeAll(resultSet, preparedStatement, connexion);
     }
     return spectacles_list;
     }
    }
   /*
     public Panier link(ResultSet resultSet) throws SQLException {

     Panier preRes = new Panier();
     preRes.setNumero(resultSet.getInt("numSpect"));
     preRes.setName(resultSet.getString("nomSpect"));
     preRes.setDescription(resultSet.getString("description"));

     return preRes;
     }

     */
}
