package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Deltager;
import database.DeltagerDAO;
import hjelpeklasser.DeltagerForm;

@WebServlet("/paamelding")
public class Paamelding extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DeltagerDAO dao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Deltager deltager = (Deltager)request.getSession().getAttribute("deltager");
		boolean feil = !Boolean.parseBoolean(request.getParameter("validert"));
		if(feil || deltager == null) {
			
			//request.getRequestDispatcher("WEB-INF/paameldingsskjema_MAL.jsp").forward(request, response);
			request.getRequestDispatcher("WEB-INF/paameldingsskjema_MAL2.jsp").forward(request, response);
		}
		
		else { //Alt stemmer
			
			request.getRequestDispatcher("WEB-INF/paameldingsbekreftelse_MAL.jsp").forward(request, response);
		}
	}		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		DeltagerForm form = new DeltagerForm();
		form.populerFraRequestOgSettOppEvtFeilmeldinger(request, dao);
		boolean validert = form.isAlleGyldig();
		
		HttpSession sesjon = request.getSession(false);
        if (sesjon != null) {
            sesjon.invalidate();
        }
        sesjon = request.getSession(true);
        sesjon.setMaxInactiveInterval(60); //Logges ut etter 60 sekunder med inaktivitet
        
        if(validert) {
        	 Deltager deltager = form.lagDeltagerFraForm();
             sesjon.setAttribute("deltager", deltager);
             sesjon.removeAttribute("form");
             dao.leggTilDeltager(deltager);
             
        }else {
        	sesjon.setAttribute("form", form);
        	sesjon.removeAttribute("deltager");
        	
        }
		response.sendRedirect("paamelding?validert=" + validert);
	}
}
