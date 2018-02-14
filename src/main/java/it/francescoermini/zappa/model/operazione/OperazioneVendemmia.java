package it.francescoermini.zappa.model.operazione;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import it.francescoermini.zappa.model.aziendaAgricola.Filare;
import it.francescoermini.zappa.model.aziendaAgricola.Vigna;
import it.francescoermini.zappa.model.type.Denominazione;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.TipologiaVendemmia;
import it.francescoermini.zappa.model.type.Vitigno;


/*
 * TODO rinominare in Conferimento oppure IntroduzioneUVA
 */


@Entity(name="OperazioneVendemmia")
@DiscriminatorValue("vendemmia")
public class OperazioneVendemmia extends Operazione {

	@Enumerated(EnumType.STRING)
	private Vitigno vitigno;
	
	

	private int quintaliUVA;
	
	@Enumerated(EnumType.STRING)
	private TipologiaVendemmia tipologiaVendemmia;
	
	public OperazioneVendemmia(){
		
	}
	public OperazioneVendemmia(String uuid,  OperazioneType operazioneType){
		super(uuid, operazioneType);
	}
	public Vitigno getVitigno() {
		return vitigno;
	}
	public void setVitigno(Vitigno vitigno) {
		this.vitigno = vitigno;
	}

	public int getQuintaliUVA() {
		return quintaliUVA;
	}
	public void setQuintaliUVA(int quintaliUVA) {
		this.quintaliUVA = quintaliUVA;
	}
	public Vitigno[] getVitignoValues(){
		return Vitigno.values();
	}
	public TipologiaVendemmia[] getTipologiaVendemmiaValues(){
		return TipologiaVendemmia.values();
	}
	public TipologiaVendemmia getTipologiaVendemmia() {
		return tipologiaVendemmia;
	}
	public void setTipologiaVendemmia(TipologiaVendemmia tipologiaVendemmia) {
		this.tipologiaVendemmia = tipologiaVendemmia;
	}
}
