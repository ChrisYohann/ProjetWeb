/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.GestionSpectacle;
import beans.Spectacle;
import dao.DAOManager;
import dao.SpectacleDao;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SetSpectacleCo", urlPatterns = {"/SetSpectacleCo"})
public class SetSpectacleCo extends HttpServlet {
    
    private static final String ATT_DAO_MANAGER = "daomanager";
    private static final String VUE = "/SetRepresentation.jsp" ;
    private static final String SPECTACLE = "newspectacle" ;
    
    private SpectacleDao stadier ;


   @Override
   public void init(){
       this.stadier = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getSpectacleDao();
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
            Spectacle theatre ;
            GestionSpectacle metier = new GestionSpectacle(this.stadier);
            theatre = metier.nouveauSpectacle(request);
            
            HttpSession session1 = request.getSession(true);
            session1.setAttribute(SPECTACLE, theatre);
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
