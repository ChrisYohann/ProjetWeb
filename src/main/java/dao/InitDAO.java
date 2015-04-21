/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author chris
 */
public class InitDAO implements ServletContextListener {
private static final String ATT_DAO_MANAGER = "daomanager";

    private DAOManager manager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        /* Instanciation du Manager*/
        this.manager = DAOManager.getInstance();
        /* Enregistrement dans un attribut ayant pour portée toute l'application */
        servletContext.setAttribute( ATT_DAO_MANAGER, this.manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}


