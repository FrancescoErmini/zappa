package it.francescoermini.zappa.model.aziendaAgricola;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.Table;

import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.OggettoType;



@Entity(name = "Cantina")
@Table(name = "cantina")
@DiscriminatorValue("cantina")
public class Cantina extends Luogo{
	
	@Column(name="canitna")
	private String cantina;
	
	
	
	
	public Cantina(){
		
	}
	public Cantina(LuogoType luogoType){
		super(luogoType);
	}
  
	
	public String getCantina() {
		return cantina;
	}

	public void setCantina(String cantina) {
		this.cantina = cantina;
	}



	
}