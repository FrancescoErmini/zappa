package it.francescoermini.zappa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.operazione.OperazioneType;

@ViewScoped
@Named
public class OperazioneListSelectController implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	@Inject
	private OperazioneDAO operazioneDAO;
	
	@Inject
	private AziendaAgricolaDAO aziendaAgricolaDAO;
	
	
	private OperazioneType operazioneType;
	
	private Luogo luogo;
	
	private List<Luogo> luoghiPerOperazione;
	
	private List<Luogo> luoghi;
	
	@PostConstruct
	public void init() {
		
		luoghi = aziendaAgricolaDAO.getAllLuoghi();
		luoghiPerOperazione = new ArrayList<>();
	}
	
	
	public void configuraLuoghiPerOperazioneType(){
		
		for( Luogo l : luoghi){
			
			if( l.getLuogoType() == operazioneType.getLuogoType()){
				luoghiPerOperazione.add(l);
			}
		}
	}

	


	public String redirectOperazioneList(){
		if( operazioneType != null  && luogo != null ){
			
			return "/operazione/operazione-list"
								+"?luogo="+luogo.getId()
								+"&type="+operazioneType
								+"&faces-redirect=true";
		}
		else{
			//TODO: qui puoi mostrare avvisso errore
			return null;
		}
	}
	public OperazioneType getOperazioneType() {
		return operazioneType;
	}




	public void setOperazioneType(OperazioneType operazioneType) {
		this.operazioneType = operazioneType;
	}




	public Luogo getLuogo() {
		return luogo;
	}




	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}
	
	public OperazioneType[] operazioneTypeValues(){
		return OperazioneType.values();
	}




	public List<Luogo> getLuoghiPerOperazione() {
		return luoghiPerOperazione;
	}




	public void setLuoghiPerOperazione(List<Luogo> luoghiPerOperazione) {
		this.luoghiPerOperazione = luoghiPerOperazione;
	}
	
	
}
