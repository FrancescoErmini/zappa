package it.francescoermini.zappa.model.operazione;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.type.Denominazione;
import it.francescoermini.zappa.model.type.Etichetta;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.Vitigno;


@Entity
public class OperazioneConferitura extends Operazione{
	
	private float carico;
	
	private float scarico;

	
	@Enumerated(EnumType.STRING)
	private Denominazione denominazione;

	@Enumerated(EnumType.STRING)
	private Etichetta etichetta;
	
	
	
	
	
	
	
	public OperazioneConferitura() {
		// TODO Auto-generated constructor stub
	}

	public OperazioneConferitura(String uuid,  OperazioneType operazioneType) {
		super(uuid, operazioneType);
	}
	
	public Etichetta[] getEtichettaValues(){
		return Etichetta.values();
	}
	public Denominazione[] getDenominazioneValues(){
		return Denominazione.values();
   }

	public float getCarico() {
		System.out.println("\n\n Get carico \n\n");
		return carico;
	}

	public void setCarico(float carico) {
		System.out.println("\n\nSet carico\n\n");
		
		
		this.carico = carico;
	}

	public float getScarico() {
		return scarico;
	}

	public void setScarico(float scarico) {
		this.scarico = scarico;
	}

	public Denominazione getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(Denominazione denominazione) {
		this.denominazione = denominazione;
	}

	public Etichetta getEtichetta() {
		return etichetta;
	}

	public void setEtichetta(Etichetta etichetta) {
		this.etichetta = etichetta;
	}


}
