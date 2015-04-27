/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import beans.PreReservation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
        
        ArrayList<PreReservation> panier = ( ArrayList<PreReservation>) request.getSession(true).getAttribute("monpanier");
               
               int i=0;
               int j = panier.size();  
               
               if (panier.size()==1){
               panier=null;
               request.getSession().setAttribute("monpanier", panier);
               }
               
               else {
               for(i=1;i<panier.size()-1;i++) {
                   
               
               String pos = request.getParameter("position "+ panier.get(i).getPos() );
               
               if(pos!=null) {                                     
                   panier.remove(i);
                   this.agencement(panier, i+1);
                   panier.trimToSize();
                   request.getSession().setAttribute("monpanier", panier);
                   this.gerer_suppr(request, response);
                   
               }               
                  
               }
                       
               
               String pos = request.getParameter("position "+ panier.get(i-1).getPos() );
               
               if(pos!=null) {                                     
                   panier.remove(i);   
                   this.agencement(panier, i+1);
                   panier.trimToSize();
                   request.getSession().setAttribute("monpanier", panier);
                   this.gerer_suppr(request, response);
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
    
    public void agencement(ArrayList<PreReservation> panier, int index) {
        
        Iterator<PreReservation> it = panier.iterator();
        PreReservation pre = new PreReservation();
        
        int i=0;
        for(i=0;i<index;i++){
            pre = it.next();
        }
        
        while(it.hasNext()){
            
            pre = it.next();
            pre.setPos(pre.getPos()-1);
                        
        }
        
     
        
    }
}
