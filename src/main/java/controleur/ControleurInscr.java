/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.MetierInscr;
import beans.UtilisateurInscrBean;
import dao.DAOManager;
import dao.UtilisateurInscrDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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
@WebServlet(name = "ControleurInscr", urlPatterns = {"/ControleurInscr"})
public class ControleurInscr extends HttpServlet {
    
        private static final String ATT_DAO_MANAGER = "daomanager";
        public static final String USER         = "newuser";
        public static final String VUE              = "/index.jsp";
        public static final String VUE_FAILED   = "/Connection.jsp" ;
        public static final String SIGNUP = "signup";
    
    private UtilisateurInscrDao utilisateur ;
    private Crypteur crypteur;
    
    
    @Override
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
            throws ServletException, IOException, NoSuchAlgorithmException {
          HttpSession session1 ;
        UtilisateurInscrBean membre = new UtilisateurInscrBean();
        Crypteur crypte = new Crypteur();
        MetierInscr objet_metier = new MetierInscr(this.utilisateur,crypte) ;
        membre = objet_metier.inscrireUtilisateur(request) ;
        
             request.setAttribute(USER,membre);
            if(!membre.getInscrit()){
                session1 = request.getSession(true);
                session1.setAttribute(SIGNUP, membre.getErreur());
                request.getServletContext().getRequestDispatcher(VUE_FAILED).forward(request, response);
                 
           }
            else {session1 = request.getSession(true);
                session1.setAttribute(USER, membre);
            if(request.getSession().getAttribute(SIGNUP) != null){
               // session1.removeAttribute(SIGNUP);
            }
           request.getServletContext().getRequestDispatcher(VUE).forward(request, response);}
            
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControleurInscr</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControleurInscr at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");}*/
        
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
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControleurInscr.class.getName()).log(Level.SEVERE, null, ex);
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
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControleurInscr.class.getName()).log(Level.SEVERE, null, ex);
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
