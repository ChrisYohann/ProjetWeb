/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.PreTicket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jm
 */
public class GestionSuppr {
    
    public GestionSuppr() {
        
    }
    
    public void gerer_suppr (HttpServletRequest request, HttpServletResponse response) {
        
        ArrayList<PreTicket> panier = ( ArrayList<PreTicket>) request.getSession(true).getAttribute("monpanier");
               
               int i=0;
               int j = panier.size();         
               
               for(i=1;i<j+1;i++) {
                   
               if(i==1) {    
               if (panier.size()==1){
               panier=null;
               }    
                                                
               else {
               
               if(panier.get(i)!=null){
               String pos = request.getParameter("position "+ panier.get(i).getPos() );
               
               if(pos!=null) {                                     
                   panier.remove(i);                   
                   
               }               
               }   
               }
                       }
               
               else {
               
                   if(panier.get(i)!=null){
               String pos = request.getParameter("position "+ panier.get(i).getPos() );
               
               if(pos!=null) {                                     
                   panier.remove(i);                   
                   
               }               
               }
               
               }
               }
               request.getSession().setAttribute("monpanier", panier);
    }
    
    public void afficherpage(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Suppression</title>");            
            out.println("</head>");
            out.println("<body>");
            
          
            out.println("<div><h6><meta http-equiv=\"refresh\" content=\"0; URL=Panier.jsp\"></h6></div>");
            
            out.println("</body>");
            out.println("</html>");
    }
    
}
}
