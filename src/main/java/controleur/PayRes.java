/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Metier.GestionAchat;
import Metier.GestionSuppr;
import beans.PreReservation;
import beans.Representation;
import beans.Spectacle;
import dao.DAOManager;
import dao.PayerDao;
import dao.RepresentationDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igierm
 */
@WebServlet(name = "PayRes", urlPatterns = {"/PayRes"})
public class PayRes extends HttpServlet {
    public static final String ATT_DAO_MANAGER = "daomanager";
    public static final String ATT_PANIER = "panier";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/reserver.jsp";

    private PayerDao payerdao;
    private RepresentationDao representant ;
    
     public void init(){
        this.payerdao = ((DAOManager)this.getServletContext().getAttribute(ATT_DAO_MANAGER)).getPayerDao();
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
        response.setContentType("text/html;charset=UTF-8");
        List<Representation> repres = new ArrayList<Representation>();

    
        GestionAchat groupmanager = new GestionAchat(this.payerdao,this.representant) ;
        groupmanager.gerer( request, response);
        
       // GestionSuppr gestionnaire = new GestionSuppr();
       // if(request.getParameter("reserver")==null&&request.getParameter("payer")==null){
       //     gestionnaire.gerer_suppr(request, response);
            
       //     gestionnaire.afficherpage(response);
        //}
        
               
               request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
               
        /*
        
        
        
        
        
        String payer = request.getParameter("payer");
        String reserver = request.getParameter("reserver");
        String spect1 = request.getParameter("spect 1");
        String spect2 = request.getParameter("spect 2");
        int nbplace1 = Integer.valueOf(request.getParameter("nbmplace1"));
        int nbplace2 = Integer.valueOf(request.getParameter("nbmplace2"));
        String cat1 = request.getParameter("categorie de spect1");
        String cat2 = request.getParameter("categorie de spect2");

        
        try (PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PayRes</title>");            
            out.println("</head>");
            out.println("<body>");
            if(payer!=null){
            out.println("<h1>Vous avez acheté :</h1>");
            if(spect1!=null) out.println("<h1>"+ nbplace1 +" place(s) pour le spectacle 1 en catégorie " + cat1 + "</h1>");
            if(spect2!=null) out.println("<h1>"+ nbplace2 +" place(s) pour le spectacle 2 en catégorie " + cat2 + "</h1>");
//ajouter ce spect à la BD
                }else if(reserver!=null){
            out.println("<h1>Vous avez réservé :</h1>");
            if(spect1!=null) out.println("<h1>"+ nbplace1 +" place(s) pour le spectacle 1 en catégorie " + cat1 + "</h1>");
            if(spect2!=null) out.println("<h1>"+ nbplace2 +" place(s) pour le spectacle 2 en catégorie " + cat2 + "</h1>");
            }else{out.println("<h1>Le serveur est actuelement indisponible veuillez reessayer plus tard</h1>");
}
            out.println("</body>");
            out.println("</html>");
        }*/
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
