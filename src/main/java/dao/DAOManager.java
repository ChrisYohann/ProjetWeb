/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author chris
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;





public class DAOManager {
    
    //Classe qui va gérer la connexion à la BD via le JDBC
    private static final String FICHIER_CONFIG       = "dao/dao.properties";
    private static final String PROPERTY_URL         = "url";
    private static final String PROPERTY_DRIVER      = "driver";
    private static final String PROPERTY_LOGIN       = "login";
    private static final String PROPERTY_PASSWORD    = "password";
    
    private String url ;
    private String login ;
    private String password ;
    
    public DAOManager(String url,String login,String password){
        this.url = url ;
        this.login = login ;
        this.password = password ;
    }
    
    public static DAOManager getInstance() throws DAOConfigurationException{
        
        
        Properties properties = new Properties();
        String url ;
        String driver ;
        String identifiant ;
        String motdepasse ;
        
        ClassLoader chargeur = Thread.currentThread().getContextClassLoader();
        InputStream fichier_config = chargeur.getResourceAsStream(FICHIER_CONFIG);
        
        
        if(fichier_config == null){
            throw new DAOConfigurationException("Le fichier properties "+FICHIER_CONFIG+" est introuvable");
        }
        
        
        try{ //A tester avec un fichier mal écrit
            properties.load(fichier_config);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            identifiant = properties.getProperty(PROPERTY_LOGIN);
            motdepasse = properties.getProperty(PROPERTY_PASSWORD);
        }
        catch(IOException e){
            throw new DAOConfigurationException("Le fichier de configuration est impossible à charger. Erreur de syntaxe");
        }

        /*
        try{ 
            Class.forName("oracle.jdbc.OracleDriver");
           
        }
        catch ( ClassNotFoundException e ) {
     //Gérer les éventuelles erreurs ici. 
        }
        String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
        String utilisateur = "celestij" ;
        String password = "celestij";
        */
        
        
       // Chargement du Driver
       
        try{
           Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            throw new DAOConfigurationException("Le driver est introuvable");
        }
        
        DAOManager manager = new DAOManager(url,identifiant,motdepasse);
        return manager ;
    }
    //On se connecte à la BDD
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,login,password);
    }
    
    public UtilisateurCoDao getUtilisateurCoDao(){
        return new UtilisateurCoDaoImpl(this);
    }
    
    public UtilisateurInscrDao getUtilisateurInscrDao(){
        return new UtilisateurInscrDaoImpl(this);
    }
    
    public SetRoleDao getSetRoleDao(){
        return new SetRoleDaoImpl(this);
    }
    
    public SpectacleDao getSpectacleDao(){
        return new SpectacleDaoImpl(this);
    }
    
    public ManagementRepresDao getManagementRepresDao() {
        return new ManagementRepresDaoImpl(this);
    }
    
    public PayerDao getPayerDao(){
        return new PayerDaoImpl(this);
    }
    
    public RepresentationDao getRepresentationDao(){
        return new RepresentationDaoImpl(this) ;
    }
    
    public InsertDao getInsertDao(){
        return new InsertDaoImpl(this);
    }
    
    public CompteDAO getCompteDao(){
        return new CompteDAOImpl(this);
    }
} 
