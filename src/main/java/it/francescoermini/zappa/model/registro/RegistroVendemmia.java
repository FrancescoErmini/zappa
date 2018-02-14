package it.francescoermini.zappa.model.registro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.type.FaseFenologica;
import it.francescoermini.zappa.model.type.TipologiaTrattamento;
import it.francescoermini.zappa.model.type.TipologiaVendemmia;
import it.francescoermini.zappa.model.type.Vitigno;


@Entity(name="registroVendemmia")
@Table(name = "registroVendemmia")
@DiscriminatorValue("RegistroVendemmia")
public class RegistroVendemmia extends Registro {

	
	private int quintaliUVA;
	
	
	
	 @Enumerated(EnumType.STRING)
	 private Vitigno vitigno;
	 

	 @Enumerated(EnumType.STRING)
	 private TipologiaVendemmia tipologiaVendemmia;
	 
	
	
	public RegistroVendemmia() {
		// TODO Auto-generated constructor stub
	}

	public RegistroVendemmia(Operazione operazione) {
		super(operazione);
	}
	public int getQuintaliUVA() {
		return quintaliUVA;
	}
	public void setQuintaliUVA(int quintaliUVA) {
		this.quintaliUVA = quintaliUVA;
	}
	public Vitigno getVitigno() {
		return vitigno;
	}
	public void setVitigno(Vitigno vitigno) {
		this.vitigno = vitigno;
	}
	
	public TipologiaVendemmia getTipologiaVendemmia() {
		return tipologiaVendemmia;
	}
	public void setTipologiaVendemmia(TipologiaVendemmia tipologiaVendemmia) {
		this.tipologiaVendemmia = tipologiaVendemmia;
	}
}
