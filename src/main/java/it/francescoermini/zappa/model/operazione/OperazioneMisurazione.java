package it.francescoermini.zappa.model.operazione;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;

import it.francescoermini.zappa.model.aziendaAgricola.Botte;
import it.francescoermini.zappa.model.aziendaAgricola.Cantina;
import it.francescoermini.zappa.model.type.LuogoType;
/*
 * Operazione correzzione...ma anche la misurazione è carina
 * 
 * 
 */
@Entity(name="OperazioneMisurazione")
@DiscriminatorValue("misurazione")
public class OperazioneMisurazione extends Operazione {
     
	
	private float temperatura;
	
	@Lob
	private String notaDegustazione;
	
//	@Temporal()
//    private Date dateOfBirth;

	
	public OperazioneMisurazione(){
		
	}
	
	public OperazioneMisurazione(String uuid,  OperazioneType operazioneType){
		super(uuid, operazioneType);
	}

	public float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
	

	public String getNotaDegustazione() {
		return notaDegustazione;
	}

	public void setNotaDegustazione(String notaDegustazione) {
		this.notaDegustazione = notaDegustazione;
	}

	
	
	
}
