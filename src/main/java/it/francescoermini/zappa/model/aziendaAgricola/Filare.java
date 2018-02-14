package it.francescoermini.zappa.model.aziendaAgricola;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import javax.persistence.Table;

import it.francescoermini.zappa.model.type.OggettoType;
import it.francescoermini.zappa.model.type.Vitigno;




@Entity(name="Filare")
@Table(name="filare")
@DiscriminatorValue("filare")
public class Filare extends Oggetto{
	  
//
//	  
//	@Column(name="filare")
//	private String filare;
//	
	  
//
//	 @Enumerated(EnumType.STRING)
//	 private Vitigno vitigno;
//	

	public Filare(){
	}
	
	public Filare(OggettoType oggettoType){
		super(oggettoType);
}
  



//
//
//	public Vitigno getVitigno() {
//		return vitigno;
//	}
//
//	public void setVitigno(Vitigno vitigno) {
//		this.vitigno = vitigno;
//	}





//
//
//	public String getFilare() {
//		return filare;
//	}
//
//
//
//
//
//
//
//	public void setFilare(String filare) {
//		this.filare = filare;
//	}
	
	
	
}
	
	