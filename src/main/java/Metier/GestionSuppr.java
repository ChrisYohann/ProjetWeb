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

    public void gerer_suppr(HttpServletRequest request) {

        ArrayList<PreReservation> panier = (ArrayList<PreReservation>) request.getSession(true).getAttribute("monpanier");
        int index = trouver_elt(request);
        if (index == 0) {
            panier = null;
        } else {
            panier.remove(index - 1);
        }

        request.getSession().setAttribute("monpanier", panier);

    }

    public int trouver_elt(HttpServletRequest request) {

        int elt = -1;
        ArrayList<PreReservation> panier = (ArrayList<PreReservation>) request.getSession(true).getAttribute("monpanier");

        int i = 0;
        int j = panier.size();

        if (panier.size() == 1) {
            elt = 0;

        } else {

            boolean dernier_element = true;
            for (i = 1; i < j + 1; i++) {

                if (i != j) {
                    String pos = request.getParameter("position " + panier.get(i - 1).getPos());
                    if (pos != null) {

                        dernier_element = false;
                        elt = i;

                    }
                }

            }
            if (dernier_element) {

                elt = panier.size();
            }

        }

        return elt;

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

        int i = 0;
        for (i = index; i < panier.size() - 1; i++) {
            pre = it.next();
            pre.setPos(pre.getPos() - 1);

        }

    }
}
