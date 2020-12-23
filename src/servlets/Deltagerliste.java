package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Deltager;
import database.DeltagerDAO;

@WebServlet("/deltagerliste")
public class Deltagerliste extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private DeltagerDAO dao;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesjon = request.getSession(false);

        if (sesjon == null || sesjon.getAttribute("deltager") == null) {
            response.sendRedirect("logginn?ikkeInnlogget");
        }
        
        List<Deltager> deltagere = dao.hentDeltagerListe();
        Collections.sort(deltagere, (a,b) -> compare(a, b));
        
        request.setAttribute("deltagere", deltagere);
        request.getRequestDispatcher("WEB-INF/deltagerliste_MAL.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	private int compare(Deltager a, Deltager b) { //Siden det er to kriterier lages det en hjelpemetode
		
		int fornavnResultat = a.getFornavn().compareTo(b.getFornavn());
		return fornavnResultat == 0 ? a.getEtternavn().compareTo(b.getEtternavn()) : fornavnResultat;
	}
}
