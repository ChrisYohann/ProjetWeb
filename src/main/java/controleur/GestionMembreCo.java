/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.GestionMembre;
import beans.UtilisateurCoBean;
import beans.UtilisateurInscrBean;
import dao.DAOManager;
import dao.UtilisateurCoDao;
import dao.UtilisateurInscrDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
@WebServlet(name = "GestionMembreCo", urlPatterns = {"/GestionMembre"})
public class GestionMembreCo extends HttpServlet {
        
        private static final String ATT_DAO_MANAGER = "daomanager";
        private static final String VUE = "/GestionMembre.jsp";
        private static final String VUE_FAILED = "/index.jsp" ;
        private UtilisateurInscrDao utilisateur ;
        
    public void init(){
        this.utilisateur = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getUtilisateurInscrDao();
    }    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       if( !((UtilisateurCoBean)request.getSession(true).getAttribute("utilisateur")).isAdmin() )
        { request.getServletContext().getRequestDispatcher(VUE_FAILED).forward(request, response);}
       
       
       List<UtilisateurInscrBean> members_list = new ArrayList();
       GestionMembre groupmanager = new GestionMembre(this.utilisateur) ;
       members_list = groupmanager.AfficherMembre(request) ;
       request.getSession(true).setAttribute("liste_membres", members_list) ;
       request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
