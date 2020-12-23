package database;

import javax.persistence.Table;

import hjelpeklasser.Passord;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(schema = "oblig4", name = "Deltager")

public class Deltager {

	@Id
	private String mobil;
	private String fornavn;
	private String etternavn;
	private String kjonn;
	@Embedded
	private Passord passord;
	
	public Deltager() {}

	public Deltager(String fornavn, String etternavn, String mobil, Passord passord, String kjonn) {
		
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobil = mobil;
		this.passord = passord;
		this.kjonn = kjonn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}
	
	public String getMobil() {
		return mobil;
	}

	public Passord getPassord() {
		return passord;
	}

	public String getKjonn() {
		return kjonn;
	}
}
