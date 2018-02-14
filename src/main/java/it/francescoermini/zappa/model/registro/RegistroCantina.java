package it.francescoermini.zappa.model.registro;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.operazione.*;
import it.francescoermini.zappa.model.type.Denominazione;
import it.francescoermini.zappa.model.type.Etichetta;
import it.francescoermini.zappa.model.type.Vitigno;


@Entity(name="registroCantina")
@Table(name = "registroCantina")
@DiscriminatorValue("RegistroCantina")
public class RegistroCantina extends Registro {
	

	
	

	
	private Etichetta etichetta;
	private Denominazione denominazione;

	private float caricoUVA;
	private float scaricoUVA;	
	private float caricoVinacce;
	private float scaricoVinacce;
	private float caricoRaspi;
	private float scaricoRaspi;
	private float caricoFeccia;
	private float scaricoFeccia;
	private float caricoMosto;
	private float scaricoMosto;
	private float caricoTaglio;
	private float scaricoTaglio;

	private int percentualeResa;
	
	public RegistroCantina() {
		
	}
	
	

	
	public RegistroCantina(Operazione operazione) {
		super(operazione);
	}
	


	public Denominazione getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(Denominazione denominazione) {
		this.denominazione = denominazione;
	}

	
	public int getPercentualeResa() {
		return percentualeResa;
	}

	public void setPercentualeResa(int percentualeResa) {
		this.percentualeResa = percentualeResa;
	}


	public float getCaricoUVA() {
		return caricoUVA;
	}


	public void setCaricoUVA(float caricoUVA) {
		this.caricoUVA = caricoUVA;
	}


	public float getScaricoUVA() {
		return scaricoUVA;
	}


	public void setScaricoUVA(float scaricoUVA) {
		this.scaricoUVA = scaricoUVA;
	}


	public float getCaricoVinacce() {
		return caricoVinacce;
	}


	public void setCaricoVinacce(float caricoVinacce) {
		this.caricoVinacce = caricoVinacce;
	}


	public float getScaricoVinacce() {
		return scaricoVinacce;
	}


	public void setScaricoVinacce(float scaricoVinacce) {
		this.scaricoVinacce = scaricoVinacce;
	}


	public float getCaricoRaspi() {
		return caricoRaspi;
	}


	public void setCaricoRaspi(float caricoRaspi) {
		this.caricoRaspi = caricoRaspi;
	}


	public float getScaricoRaspi() {
		return scaricoRaspi;
	}


	public void setScaricoRaspi(float scaricoRaspi) {
		this.scaricoRaspi = scaricoRaspi;
	}


	public float getCaricoFeccia() {
		return caricoFeccia;
	}


	public void setCaricoFeccia(float caricoFeccia) {
		this.caricoFeccia = caricoFeccia;
	}


	public float getScaricoFeccia() {
		return scaricoFeccia;
	}


	public void setScaricoFeccia(float scaricoFeccia) {
		this.scaricoFeccia = scaricoFeccia;
	}


	public float getCaricoMosto() {
		return caricoMosto;
	}


	public void setCaricoMosto(float caricoMosto) {
		this.caricoMosto = caricoMosto;
	}


	public float getScaricoMosto() {
		return scaricoMosto;
	}


	public void setScaricoMosto(float scaricoMosto) {
		this.scaricoMosto = scaricoMosto;
	}




	public Etichetta getEtichetta() {
		return etichetta;
	}




	public void setEtichetta(Etichetta etichetta) {
		this.etichetta = etichetta;
	}




	public float getCaricoTaglio() {
		return caricoTaglio;
	}




	public void setCaricoTaglio(float caricoTaglio) {
		this.caricoTaglio = caricoTaglio;
	}




	public float getScaricoTaglio() {
		return scaricoTaglio;
	}




	public void setScaricoTaglio(float scaricoTaglio) {
		this.scaricoTaglio = scaricoTaglio;
	}


	



	
}