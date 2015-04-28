/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 *
 * @author jm
 */
public class Crypteur {
    
    private static final String FICHIER_CONFIG       = "sha/sha.properties";
    private static final String PREFIXE         = "prefixe";
    private static final String SUFFIXE      = "suffixe";
    
    public Crypteur() {
        
    }
    
    public String crypter_mdp(String mot_de_passe) throws NoSuchAlgorithmException{
        
        Properties properties = new Properties();
        String prefixe ;
        String suffixe ;
        
         ClassLoader chargeur = Thread.currentThread().getContextClassLoader();
         InputStream fichier_config = chargeur.getResourceAsStream(FICHIER_CONFIG);
         
         if(fichier_config == null){
            throw new dao.DAOConfigurationException("Le fichier properties "+FICHIER_CONFIG+" est introuvable");
        }
         
         try{ //A tester avec un fichier mal écrit
            properties.load(fichier_config);
            prefixe = properties.getProperty(PREFIXE);
            suffixe = properties.getProperty(SUFFIXE);
            
        }
        catch(IOException e){
            throw new DAOConfigurationException("Le fichier de configuration est impossible à charger. Erreur de syntaxe");
        }
         
        String sha256 = "";
        String password = prefixe + mot_de_passe + suffixe  ;
    try
    {
        MessageDigest crypt = MessageDigest.getInstance("SHA-256");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        sha256 = byteToHex(crypt.digest());
    }
    catch(NoSuchAlgorithmException e)
    {
        e.printStackTrace();
    }
    catch(UnsupportedEncodingException e)
    {
        e.printStackTrace();
    }
    
    return sha256;
         
                
        
    }
    
    private String byteToHex(final byte[] hash)
{
    Formatter formatter = new Formatter();
    for (byte b : hash)
    {
        formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
}
    
}





