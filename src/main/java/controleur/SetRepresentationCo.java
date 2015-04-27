/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.SetRepresentation;
import beans.Representation;
import dao.DAOManager;
import dao.RepresentationDao;
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
@WebServlet(name = "SetRepresentation", urlPatterns = {"/SetRepresentation"})
public class SetRepresentationCo extends HttpServlet {
        private static final String ATT_DAO_MANAGER = "daomanager";
        private static final String VUE_FAILED = "/SetRepresentation.jsp" ;    
        private static final String VUE="/Complete.jsp" ;
        private static final String ERREUR ="message_erreur" ;
        private static final String REPRESENTATION = "representation" ;
    
         private RepresentationDao representant ;
         

    @Override
    public void init(){
               this.representant = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getRepresentationDao();
    
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
        SetRepresentation metier = new SetRepresentation(this.representant);
        Representation festival = metier.creer_representation(request);
        if(festival==null || festival.getErreur() != null)
        {   request.getSession(true).setAttribute(REPRESENTATION, festival);
            request.getServletContext().getRequestDispatcher(VUE_FAILED).forward(request,response) ;}
        else{
            if(!((String)request.getParameter("terminer")).equals("Terminer"))
            request.getServletContext().getRequestDispatcher(VUE_FAILED).forward(request,response);      
            request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
        
    
    
    
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