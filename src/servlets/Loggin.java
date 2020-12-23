package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Deltager;
import database.DeltagerDAO;
import hjelpeklasser.Passordhjelper;

@WebServlet("/logginn")
public class Loggin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private DeltagerDAO dao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String feilmelding = null;
		
		if(request.getParameter("ikkeInnlogget") != null) {
			feilmelding = "Du er ikke innlogget";
		}
		else if(request.getParameter("feilInput") != null){
			feilmelding = "Ugyldig brukernavn og/eller passord";
		}
		request.setAttribute("feilmelding", feilmelding);
		request.getRequestDispatcher("WEB-INF/logginn_MAL.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mobil = request.getParameter("mobil");
		String passordStreng = request.getParameter("passord");
		Deltager deltager = dao.hentDeltager(mobil);
		
		boolean riktig = valider(deltager, passordStreng);
		
		if(riktig) {
			request.getSession().setAttribute("deltager", deltager);
			response.sendRedirect("deltagerliste");
		}
		else {
			response.sendRedirect("?feilInput");
		}
	}
	
	private boolean valider(Deltager deltager, String passord) {
		
		boolean finnes = deltager != null;
		
		if(finnes) {
			
			String deltagerPassord = deltager.getPassord().getHashPassord();
			String deltagerSalt = deltager.getPassord().getSalt();
			return Passordhjelper.validerMedSalt2(passord, deltagerSalt, deltagerPassord);
			
		}else {
			return false;
		}
	}
}
