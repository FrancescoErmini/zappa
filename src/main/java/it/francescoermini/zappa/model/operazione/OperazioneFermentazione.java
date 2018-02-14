package it.francescoermini.zappa.model.operazione;

import javax.persistence.Entity;

import it.francescoermini.zappa.model.type.LuogoType;

@Entity
public class OperazioneFermentazione extends Operazione{
	
	private float carico;
	
	private float scarico;
	
	
	
	public OperazioneFermentazione() {
		// TODO Auto-generated constructor stub
	}


	public OperazioneFermentazione(String uuid,  OperazioneType operazioneType) {
		super(uuid, operazioneType);
	}


	public float getCarico() {
		return carico;
	}


	public void setCarico(float carico) {
		this.carico = carico;
	}


	public float getScarico() {
		return scarico;
	}


	public void setScarico(float scarico) {
		this.scarico = scarico;
	}

}
