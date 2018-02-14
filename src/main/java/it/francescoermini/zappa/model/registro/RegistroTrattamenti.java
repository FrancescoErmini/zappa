package it.francescoermini.zappa.model.registro;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.type.FaseFenologica;
import it.francescoermini.zappa.model.type.TipologiaTrattamento;
import it.francescoermini.zappa.model.type.TipologiaVendemmia;
import it.francescoermini.zappa.model.type.Vitigno;


@Entity(name="registroTrattamenti")
@Table(name = "registroTrattamenti")
@DiscriminatorValue("RegistroTrattamenti")
public class RegistroTrattamenti extends Registro {


	@Lob
	private String descrizioneTrattamento;

	 
	 @Enumerated(EnumType.STRING)
	 private TipologiaTrattamento tipologiaTrattamento;
	 
	 @Enumerated(EnumType.STRING)
	 private FaseFenologica faseFenologica;
	 
	
	public RegistroTrattamenti() {
		// TODO Auto-generated constructor stub
	}

	public RegistroTrattamenti(Operazione operazione) {
		super(operazione);
	}
	
	public TipologiaTrattamento getTipologiaTrattamento() {
		return tipologiaTrattamento;
	}
	public void setTipologiaTrattamento(TipologiaTrattamento tipologiaTrattamento) {
		this.tipologiaTrattamento = tipologiaTrattamento;
	}
	public FaseFenologica getFaseFenologica() {
		return faseFenologica;
	}
	public void setFaseFenologica(FaseFenologica faseFenologica) {
		this.faseFenologica = faseFenologica;
	}

	public String getDescrizioneTrattamento() {
		return descrizioneTrattamento;
	}

	public void setDescrizioneTrattamento(String descrizioneTrattamento) {
		this.descrizioneTrattamento = descrizioneTrattamento;
	}
	
}
