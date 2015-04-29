/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.GestionNewRepres;
import Metier.GestionSpectacle;
import beans.Representation;
import beans.Spectacle;
import dao.DAOManager;
import dao.ManagementRepresDao;
import dao.SpectacleDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author chris
 */
@WebServlet(name = "SpectacleManagementCo", urlPatterns = {"/SpectacleManagementCo"})
public class SpectacleManagementCo extends HttpServlet {
    
    private static final String ATT_DAO_MANAGER = "daomanager";
    private static final String VUE = "/SetNewRepresentation.jsp" ;
    private static final String REPRESENTATION = "representation2" ;
    
    private ManagementRepresDao stadier ;


   @Override
   public void init(){
       this.stadier = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getManagementRepresDao();
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
            throws ServletException, IOException, SQLException {
            Spectacle repres = new Spectacle();
            GestionNewRepres gerant = new GestionNewRepres(this.stadier);
            int num_spect = gerant.trouver_elt(request);
            String nom_spect =stadier.trouver_nom(num_spect);
            repres.setName(nom_spect);
            repres.setNumero(num_spect);
            
            
            HttpSession session1 = request.getSession(true);
            session1.setAttribute(REPRESENTATION, repres);
            request.getServletContext().getRequestDispatcher(VUE).forward(request,response);
            
        
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SpectacleManagementCo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SpectacleManagementCo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
