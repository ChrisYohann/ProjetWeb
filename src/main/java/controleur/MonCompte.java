/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.GestionCompte;
import Metier.GestionSpectacle;
import beans.Compte;
import beans.Spectacle;
import dao.CompteDAO;
import dao.DAOManager;
import dao.SpectacleDao;
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
 * @author matylde
 */
@WebServlet(name = "MonCompte", urlPatterns = {"/MonCompte"})
public class MonCompte extends HttpServlet {
    
        private static final String ATT_DAO_MANAGER = "daomanager";
        private static final String VUE = "/MonComte.jsp";
        private static final String VUE_FAILED = "/index.jsp" ;
        private CompteDAO compte ;
        
        public void init(){
        this.compte = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getCompteDao();
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
        response.setContentType("text/html;charset=UTF-8");
        
        List<Compte> compte_list = new ArrayList();
        GestionCompte groupmanager = new GestionCompte(this.compte) ;
        compte_list = groupmanager.AfficherCompte(request);
        request.getSession(true).setAttribute("liste_spectacles", compte_list) ;
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
