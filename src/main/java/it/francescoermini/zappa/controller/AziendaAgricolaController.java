package it.francescoermini.zappa.controller;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.OggettoType;
//TODO: test leva applicationScoped
@Model
public class AziendaAgricolaController implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private AziendaAgricolaDAO aziendaAgricolaDAO;
		
	private List<Responsabile> responsabili;
	
	private List<Luogo> luoghi;
	
	

	@PostConstruct
	public void init(){
		System.out.println("Init ");

		//responsabili = new ArrayList<>();
		//luoghi = new ArrayList<>();
	}
	
	
	public AziendaAgricolaController() {
		System.out.println("costruttore Azienda Agricola");
	}
	


	public List<Responsabile> getResponsabili() {
		System.out.println("Get Responsabili");
		//System.out.println(responsabili.get(0).getNome());
		if ( responsabili == null ){
			dumpResponsabili();
		}
		else {
			System.out.println("Responsabili not null");
		}
		return responsabili;
	}

	public void setResponsabili(List<Responsabile> responsabili) {
		this.responsabili = responsabili;
	}

	public List<Luogo> getLuoghi() {
		System.out.println("Get Luoghi");
		//System.out.println(luoghi.get(0).getNome());

		if( luoghi == null ){
			dumpLuoghi();
		}
		else {
			System.out.println("Luoghi not null");
		}
		return luoghi;
	}

	public void setLuoghi(List<Luogo> luoghi) {
		this.luoghi = luoghi;
	}
	
	public void dumpLuoghi(){
		try {
			luoghi= aziendaAgricolaDAO.getAllLuoghi();
		}
		catch(Exception e){
			System.out.println("Eccezzione in Dump Luoghi");
		}
		
	}
	public void dumpResponsabili(){
		try {
			responsabili = aziendaAgricolaDAO.getAllResponsabili();
		}
		catch(Exception e){
			System.out.println("Eccezzione in Dump Responsabile");
		}
	}
	
	
}
