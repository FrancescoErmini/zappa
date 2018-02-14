package it.francescoermini.zappa.model.operazione;


import javax.persistence.Entity;

import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.type.LuogoType;

@Entity
public class OperazioneCorrezioniTagli extends Operazione{
	
	private float carico;
	
	private float scarico;
	
	
	
	
	public OperazioneCorrezioniTagli() {
		// TODO Auto-generated constructor stub
	}
	public OperazioneCorrezioniTagli(String uuid, OperazioneType operazioneType) {
		super(uuid, operazioneType);
	}
	public float getCarico() {
		return carico;
	}
	public void setCarico(float f) {
		this.carico = f;
	}
	public float getScarico() {
		return scarico;
	}
	public void setScarico(float scarico) {
		this.scarico = scarico;
	}

}
