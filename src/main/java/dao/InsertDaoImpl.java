/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import beans.Representation;
import beans.Spectacle;
import beans.UtilisateurInscrBean;
import static dao.DAOUtil.closeAll;
import static dao.DAOUtil.initRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author igierm
 */
public class InsertDaoImpl implements InsertDao{
    
    private static String FOREIGN_SALLE = "INSERT INTO salle(numSalle) VALUES (?)";
    private static String FOREIGN_CATEGORIE = "INSERT INTO categorie(catTarif,tarif) VALUES(?,?)";
    private static String FOREIGN_RANG = "INSERT INTO rang(numSalle,numRang,catTarif) VALUES(?,?,?)";
    private static String CHECK_SALLE="SELECT * FROM salle";
    private static String CHECK_CATEGORIE="SELECT * FROM categorie";
    private static String CHECK_RANG="SELECT * FROM rang";
    
    private DAOManager manager;

    public InsertDaoImpl(DAOManager gerant) {
        this.manager = gerant;
    }
    
    @Override
    public void creer(){
        
       Connection connexion = null ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try { connexion = manager.getConnection() ;
            preparedStatement = initRequete(connexion, CHECK_SALLE, false);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {//TODO: sinon renvoyer une erreur ?
                for (int i = 1; i < 4; i++) {
                    preparedStatement = initRequete(connexion, FOREIGN_SALLE, true, i);
                    int sucess = preparedStatement.executeUpdate();
                    if (sucess == 0) {
                        //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                        throw new DAOException("Echec : Les salle n'ont pas été chargées");
                    }
                }
            }
            preparedStatement = initRequete(connexion, CHECK_CATEGORIE, false);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {//TODO: sinon renvoyer une erreur ?
                    preparedStatement = initRequete(connexion, FOREIGN_CATEGORIE, true, "orchestre",40);
                    int sucess1 = preparedStatement.executeUpdate();
                    preparedStatement = initRequete(connexion, FOREIGN_CATEGORIE, true, "poulailler",20);
                    int sucess2 = preparedStatement.executeUpdate();
                    preparedStatement = initRequete(connexion, FOREIGN_CATEGORIE, true, "balcon",10);
                    int sucess3 = preparedStatement.executeUpdate();
                    if (sucess1 == 0||sucess2 == 0||sucess3 == 0) {
                        //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                        throw new DAOException("Echec : Les categorie n'ont pas été chargées");
                    }
                
            }
            
            preparedStatement = initRequete(connexion, CHECK_RANG, false);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {//TODO: sinon renvoyer une erreur ?
                for (int i=1;i<4;i++) {//dans les trois salles
                    for (int j=1; j<6; j++) {//les rangs qui sont de categorie orchestre
                    preparedStatement = initRequete(connexion, FOREIGN_RANG, true, i,j,"orchestre");
                    int sucess1 = preparedStatement.executeUpdate();
                    if (sucess1 == 0) {
                        //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                        throw new DAOException("Echec : Les categorie n'ont pas été chargées");
                    }
                    }
                    for(int j=6;j<13;j++){
                    preparedStatement = initRequete(connexion, FOREIGN_RANG, true, i,j,"poulailler");
                    int sucess2 = preparedStatement.executeUpdate();
                    if (sucess2 == 0) {
                        //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                        throw new DAOException("Echec : Les categorie n'ont pas été chargées");
                    }
                    }
                    for(int j=13;j<16;j++){
                    preparedStatement = initRequete(connexion, FOREIGN_RANG, true,i,j, "balcon");
                    int sucess3 = preparedStatement.executeUpdate();
                    if (sucess3 == 0) {
                        //spectacle.setErreur("<FONT COLOR=\"red\" >Le spectacle existe déjà.</FONT>");
                        throw new DAOException("Echec : Les categorie n'ont pas été chargées");
                    }
                    }
                }
                
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeAll(resultSet, preparedStatement, connexion);
        }
        
    }
}
