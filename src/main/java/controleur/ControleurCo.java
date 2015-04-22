/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.MetierCo;
import beans.UtilisateurCoBean;
import dao.UtilisateurCoDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAOManager ;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chris
 */
@WebServlet(urlPatterns = {"/CustomerCo"})
public class ControleurCo extends HttpServlet {
   
    
        private static final String ATT_DAO_MANAGER = "daomanager";
        public static final String USER         = "utilisateur";
        public static final String VUE              = "/LogSuccessful.jsp";
        public static final String VUE_FAILED   = "/Connection.jsp" ;
        
        
        private UtilisateurCoDao utilisateur ;
    
    
      @Override
    public void init(){
        this.utilisateur = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getUtilisateurCoDao();
        
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
            throws ServletException, IOException{
            HttpSession session1 ;
            MetierCo metier = new MetierCo(this.utilisateur) ;
            UtilisateurCoBean user = new UtilisateurCoBean();
            user = metier.connectUser(request);
            
            request.setAttribute(USER,user);
            if(user == null){
                session1 = request.getSession(true) ;
                session1.setAttribute("message_erreur","<FONT COLOR=\"red\" >Login et/ou Mot de passe incorrect</FONT>");
                request.getServletContext().getRequestDispatcher(VUE_FAILED).forward(request, response);
                 
           }
            else {session1 = request.getSession(true);
            session1.setAttribute(USER, user);
            if(request.getSession().getAttribute("message_erreur") != null){
                session1.removeAttribute("message_erreur");
            }
           request.getServletContext().getRequestDispatcher(VUE).forward(request, response);}
     
     
     
     
     }
    
    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControleurCo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControleurCo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }*/

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
