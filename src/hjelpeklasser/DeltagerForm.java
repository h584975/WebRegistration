package hjelpeklasser;

import javax.servlet.http.HttpServletRequest;

import database.Deltager;
import database.DeltagerDAO;

public class DeltagerForm {

	private String fornavn;
	private String fornavnMelding;
	
	private String etternavn;
	private String etternavnMelding;
	
	private String mobil;
	private String mobilMelding;
	
	private String passord;
	private String passordMelding;
	
	private String passordRepetert;
	private String passordRepetertMelding;
	
	private String kjonn;
	private String kjonnMelding;
	
	private Validator validator = new Validator();
	private boolean alleGyldig;
	
	public Deltager lagDeltagerFraForm() {
		
		Passord passordHash = Passord.lagPassord(passord);
		return new Deltager(fornavn, etternavn, mobil, passordHash, kjonn);
	}

	public void populerFraRequestOgSettOppEvtFeilmeldinger(HttpServletRequest request, DeltagerDAO deltagerDao) {
		populerFraRequest(request);
		settOppEvtFeilmeldinger(deltagerDao);
	}

	private void populerFraRequest(HttpServletRequest request) {
		fornavn = request.getParameter("fornavn");
		etternavn = request.getParameter("etternavn");
		mobil = request.getParameter("mobil");
		passord = request.getParameter("passord");
		passordRepetert = request.getParameter("passordRepetert");
		kjonn = request.getParameter("kjonn");
	}

	private void settOppEvtFeilmeldinger(DeltagerDAO deltagerDao) {

		boolean fornavnOk = validator.fornavnValid(fornavn);
		if (!fornavnOk) {
			fornavn = "";
			fornavnMelding = "Ugyldig fornavn";
		}

		boolean etternavnOk = validator.etternavnValid(etternavn);
		if (!etternavnOk) {
			etternavn = "";
			etternavnMelding = "Ugyldig etternavn";
		}

		boolean mobilOk = validator.mobilValid(mobil);
		boolean paameldt = deltagerDao.hentDeltager(mobil) != null;
		if (!mobilOk) {
			mobil = "";
			mobilMelding = "Ugyldig mobil";
		} else if (paameldt) {
			mobil = "";
			mobilMelding = "Bruker allerede påmeldt";
		}

		boolean passordOk = validator.passordValid(passord);
		if (!passordOk) {
			passord = ""; //Kanskje passord burde vært blanket ut uansett?!
			passordRepetert = "";
			passordMelding = "Ugyldig passord";
		}
		
		boolean passordRepOk = passordOk && passord.equals(passordRepetert);
		if (!passordRepOk) {
			passordRepetert = ""; //Kanskje passord burde vært blanket ut uansett?!
			passordRepetertMelding = "Passordene må være like";
		}
		
		boolean kjonnOk = validator.kjonnValid(kjonn);
		if (!kjonnOk) {
			kjonn = "";
			kjonnMelding = "Du må oppgi kjonn";
		}

		alleGyldig = fornavnOk && etternavnOk && mobilOk && !paameldt 
				&& passordOk && passordRepOk && kjonnOk;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getFornavnMelding() {
		return fornavnMelding;
	}

	public void setFornavnMelding(String fornavnMelding) {
		this.fornavnMelding = fornavnMelding;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getEtternavnMelding() {
		return etternavnMelding;
	}

	public void setEtternavnMelding(String etternavnMelding) {
		this.etternavnMelding = etternavnMelding;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getMobilMelding() {
		return mobilMelding;
	}

	public void setMobilMelding(String mobilMelding) {
		this.mobilMelding = mobilMelding;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getPassordMelding() {
		return passordMelding;
	}

	public void setPassordMelding(String passordMelding) {
		this.passordMelding = passordMelding;
	}

	public String getPassordRepetert() {
		return passordRepetert;
	}

	public void setPassordRepetert(String passordRepetert) {
		this.passordRepetert = passordRepetert;
	}

	public String getPassordRepetertMelding() {
		return passordRepetertMelding;
	}

	public void setPassordRepetertMelding(String passordRepetertMelding) {
		this.passordRepetertMelding = passordRepetertMelding;
	}

	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

	public String getKjonnMelding() {
		return kjonnMelding;
	}

	public void setKjonnMelding(String kjonnMelding) {
		this.kjonnMelding = kjonnMelding;
	}

	public boolean isAlleGyldig() {
		return alleGyldig;
	}

	public void setAlleGyldig(boolean alleGyldig) {
		this.alleGyldig = alleGyldig;
	}

}