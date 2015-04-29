/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Representation;
import java.sql.SQLException;

/**
 *
 * @author jm
 */
public interface ManagementRepresDao {
    
    public void creer(Representation repres, String date);
    public String trouver_nom(int spect) throws SQLException;
}
