/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.MetierSetRole;
import beans.UtilisateurCoBean;
import dao.DAOManager;
import dao.SetRoleDao;
import dao.UtilisateurInscrDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
@WebServlet(name = "SetRoleCo", urlPatterns = {"/SetRole"})
public class SetRole extends HttpServlet {

        private static final String ATT_DAO_MANAGER = "daomanager";
        private static final String VUE = "/GestionMembre.jsp";
        private static final String VUE_FAILED = "/index.jsp" ;
        private SetRoleDao utilisateur ;
    
    public void init(){
    this.utilisateur = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getSetRoleDao();
    
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
        
        MetierSetRole metier = new MetierSetRole(this.utilisateur);
        String deroulement = metier.changer_role(request) ;
        request.setAttribute("deroulement",deroulement) ;
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
