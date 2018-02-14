package it.francescoermini.zappa.model.aziendaAgricola;

import it.francescoermini.zappa.model.type.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity(name="Botte")
@Table(name="botte")
@DiscriminatorValue("botte")
public class Botte extends Oggetto{
	  
	
//	@Column(name="botte")
//	private String botte;
//	
	
//
//	  @Enumerated(EnumType.STRING)
//		private TipologiaBotte tipologiaBotte;
//	  
		
		private int  capacita;
		@Lob
		private String noteBotte;
	
	  
	  
	  
	  public Botte() {

	  }
	  public Botte(OggettoType oggettoType){
			super(oggettoType);
	}
	  

//	public TipologiaBotte getTipologiaBotte() {
//		return tipologiaBotte;
//	}
//	public void setTipologiaBotte(TipologiaBotte tipologiaBotte) {
//		this.tipologiaBotte = tipologiaBotte;
//	}
	public int getCapacita() {
		return capacita;
	}
	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}
	public String getNoteBotte() {
		return noteBotte;
	}
	public void setNoteBotte(String noteBotte) {
		this.noteBotte = noteBotte;
	}

	


//	public String getBotte() {
//		return botte;
//	}
//
//
//	public void setBotte(String botte) {
//		this.botte = botte;
//	}
	
}
	