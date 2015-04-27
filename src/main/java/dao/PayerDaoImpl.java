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
    
    private static String SQL_ADD_ACHAT = "INSERT INTO achat (login, numDossier, numTicket, numSpect,jour, heure, numSalle, numRang, numPlace) VALUES (?,?,?,?,?,?,?,?,?)";
    private static String SQL_ADD_RESERVATION = "INSERT INTO reservation (login, numSpect,jour, heure, numSalle, numRang, numPlace) VALUES (?,?,?,?,?,?,?)";
    private static String SQL_ALL_SPECTACLES = "SELECT * FROM spectacle ";
    private static String SQL_DATE_SPECTACLE = "SELECT prez.numSpect,prez.nbrPlace,prez.jour,prez.heure,prez.numSalle from representation prez,spectacle s where s.numSpect = ? and prez.numSpect = s.numSpect ";

    private static String FOREIGN_SALLE = "INSERT INTO salle(numSalle) VALUES (?)";
    private static String FOREIGN_CATEGORIE = "INSERT INTO categorie(catTarif,tarif) VALUES(?,?)";
    private static String FOREIGN_RANG = "INSERT INTO rang(numSalle,numRang,catTarif) VALUES(?,?)";
    private static String FOREIGN_PLACE = "INSERT INTO place(numSalle,numRang,numPlace) VALUES (?,?,?)";

    private static String SQL_CHECK_PLACE = "SELECT p.numSalle,p.numRang,p.numPlace FROM place p where p.numSalle = ? and p.numRang = ? and p.numPlace = ?";
    
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

        try {
            connexion = manager.getConnection();
            for (int i = 0; i < preRes.size(); i++) {

                Date date = preRes.get(i).getDate();
                String dateE = date_en_chaine(date);
                int heure = preRes.get(i).getHeure();
                int salle = preRes.get(i).getSalle();
                Representation representation = represdao.trouver(dateE, heure, salle);
                if (representation != null) {
                    int nbrPlace = preRes.get(i).getNbPlace();
                    String cat = preRes.get(i).getCat();
                   switch(cat){
                       case "orchestre" :
                           preparedStatement = initRequete(connexion,SQL_CHECK_PLACE,false,salle,5,11-nbrPlace);
                           break ;
                       case "poulailler" :
                           preparedStatement = initRequete(connexion,SQL_CHECK_PLACE,false,salle,12,11-nbrPlace);
                           break ;    
                       case "balcon" :
                           preparedStatement = initRequete(connexion,SQL_CHECK_PLACE,false,salle,15,11-nbrPlace);
                           break ;      
                       default :
                           preparedStatement = initRequete(connexion,SQL_CHECK_PLACE,false,salle,15,11-nbrPlace);
                   }
                   resultSet = preparedStatement.executeQuery() ;
                        if(!resultSet.next()){
                            //Si le resultSet est vide, la place est libre
                            
                            
                            
                            
                            
                        }
                
                   
                //repres.add(representation);
                /*if(rep.get(i) == null)
                     throw new DAOException(Integer.toString(rep.size())) ;*/
                    preparedStatement = initRequete(connexion, SQL_ADD_ACHAT, false, login, 16, i, representation.getSpect().getNumero(),
                            representation.date_en_chaine(representation.getJour()), representation.getHeure(), representation.getNumSalle(), 15, 9);//TODO numrang place et dossier

                    int sucess = preparedStatement.executeUpdate();//On recherche le login dans la table 
                    if (sucess == 0) {
                        //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                        throw new DAOException("Echec : La reservation correspondant n'a pas été chargée");
                    }
                }
            }
            //new_user.setInscrit(true);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }

    @Override
    public void reserver(ArrayList<PreReservation> preRes, String login) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RepresentationDao represdao = new RepresentationDaoImpl(this.manager);

        try {
            connexion = manager.getConnection();
            for (int i = 0; i < preRes.size(); i++) {

                Date date = preRes.get(i).getDate();
                String dateE = date_en_chaine(date);
                int heure = preRes.get(i).getHeure();
                int salle = preRes.get(i).getSalle();
                Representation representation = represdao.trouver(dateE, heure, salle);
                int nbrPlace = preRes.get(i).getNbPlace();
                String cat = preRes.get(i).getCat();

                preparedStatement = initRequete(connexion, SQL_ADD_RESERVATION, false, login, representation.getSpect().getNumero(),
                        representation.getJour(), representation.getHeure(), representation.getNumSalle(), 15, 9);//TODO
                int sucess = preparedStatement.executeUpdate();//On recherche le login dans la table 
                if (sucess == 0) {
                    //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                    throw new DAOException("Echec : La reservation correspondant n'a pas été chargée");
                }
            }

            //new_user.setInscrit(true);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }

    }

    /*
     @Override
     public Panier afficher_preRes() throws DAOException {
     List<Spectacle> spectacles_list = new ArrayList();
     Connection connexion = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     Spectacle festival = null;

     try {
     connexion = manager.getConnection();
     preparedStatement = initRequete(connexion, SQL_ALL_SPECTACLES, false);
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

     public Panier link(ResultSet resultSet) throws SQLException {

     Panier preRes = new Panier();
     preRes.setNumero(resultSet.getInt("numSpect"));
     preRes.setName(resultSet.getString("nomSpect"));
     preRes.setDescription(resultSet.getString("description"));

     return preRes;
     }

     */
}
