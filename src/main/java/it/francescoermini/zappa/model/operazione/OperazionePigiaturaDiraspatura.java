package it.francescoermini.zappa.model.operazione;

import javax.persistence.Entity;

import it.francescoermini.zappa.model.type.LuogoType;

@Entity
public class OperazionePigiaturaDiraspatura extends Operazione{
	
	private float carico;
	
	private float scarico;
    
	
	private float differenza;
	
	public OperazionePigiaturaDiraspatura() {
		// TODO Auto-generated constructor stub
	}

	public OperazionePigiaturaDiraspatura(String uuid,  OperazioneType operazioneType) {
		super(uuid,operazioneType);
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

	public float getDifferenza() {
		return differenza;
	}

	public void setDifferenza(float differenza) {
		this.differenza = differenza;
	}

}
