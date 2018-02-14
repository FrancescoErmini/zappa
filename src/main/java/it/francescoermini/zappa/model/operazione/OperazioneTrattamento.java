package it.francescoermini.zappa.model.operazione;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import it.francescoermini.zappa.model.type.FaseFenologica;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.TipologiaTrattamento;

@Entity(name="OperazioneTrattamento")
@DiscriminatorValue("trattamento")
public class OperazioneTrattamento extends Operazione {

	

	@Lob
	private String descrizioneTrattamento;
	
	@Enumerated(EnumType.STRING)
	 private TipologiaTrattamento tipologiaTrattamento;
	 
	 
	 @Enumerated(EnumType.STRING)
	 private FaseFenologica faseFenologica;

	
	
	public OperazioneTrattamento(){
		
	}
	public OperazioneTrattamento(String uuid,  OperazioneType operazioneType){
		super(uuid, operazioneType);
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
	public TipologiaTrattamento[] getTipologiaTrattamentoValues(){
		return TipologiaTrattamento.values();
	}
	public FaseFenologica[] getFaseFenologicaValues(){
		return FaseFenologica.values();
	}

}
