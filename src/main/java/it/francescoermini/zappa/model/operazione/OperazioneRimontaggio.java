package it.francescoermini.zappa.model.operazione;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import it.francescoermini.zappa.model.type.LuogoType;

/*
 * Anche chiamabile Operazioni Preliminari
 * Operazione Pesatura su sottoprodotti aggiunti, primo termine o rimossi, secondo temina di ogni vettore 
 */



@Entity(name="OperazioneRimontaggio")
@DiscriminatorValue("rimontaggio")
public class OperazioneRimontaggio extends Operazione{
	
	private int durata;
	
	public OperazioneRimontaggio(){
		
	}
	public OperazioneRimontaggio(String uuid ,  OperazioneType operazioneType){
		super(uuid, operazioneType);
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	
	
	
//	public int[] getQuintaliVinacce() {
//		return quintaliVinacce;
//	}
//	public void setQuintaliVinacce(int[] quintaliVinacce) {
//		this.quintaliVinacce = quintaliVinacce;
//	}
//	public int[] getQuintaliRaspi() {
//		return quintaliRaspi;
//	}
//	public void setQuintaliRaspi(int[] quintaliRaspi) {
//		this.quintaliRaspi = quintaliRaspi;
//	}
//	public int[] getQuintaliFeccia() {
//		return quintaliFeccia;
//	}
//	public void setQuintaliFeccia(int[] quintaliFeccia) {
//		this.quintaliFeccia = quintaliFeccia;
//	}
	

}
