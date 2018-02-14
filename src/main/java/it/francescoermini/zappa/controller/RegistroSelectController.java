package it.francescoermini.zappa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.DiscriminatorValue;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.dao.RegistroDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroType;
import it.francescoermini.zappa.model.type.LuogoType;


@ViewScoped
@Named
public class RegistroSelectController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;


	@Inject
	private RegistroDAO registroDAO;


	@Inject
	private AziendaAgricolaDAO aziendaAgricolaDAO;
	
	
   private List<Registro> registri;
	
	
	private RegistroType registroType;
	
	private List<Luogo> luoghiPerRegistro;
	
	private List<Luogo> luoghi;
	
	private Luogo luogo;
	
	private Long luogoID;
	
	@PostConstruct
	public void start(){
		luoghi = aziendaAgricolaDAO.getAllLuoghi();
		
	}
	
	public RegistroType[] registroTypeValues(){
		return RegistroType.values();
	}
	
	public void configuraLuoghiPerRegistro(){
		luoghiPerRegistro = new ArrayList<>();
		for( Luogo l : luoghi){
			
			if( l.getLuogoType() == registroType.getLuogoType()){
				luoghiPerRegistro.add(l);
			}
		}
	}
	public String redirectRegistro(){
		if( registroType != null  && luogo != null ){
			return "/registro/"+registroType.getRegistroPage()
								+"?luogo="+luogo.getId()
								+"&type="+registroType
								+"&faces-redirect=true";
		}
		else{
			//TODO: qui puoi mostrare avvisso errore
			return null;
		}
		
	}
	public RegistroType getRegistroType() {
		return registroType;
	}

	public void setRegistroType(RegistroType registroType) {
		this.registroType = registroType;
	}

	public List<Luogo> getLuoghiPerRegistro() {
		return luoghiPerRegistro;
	}

	public void setLuoghiPerRegistro(List<Luogo> luoghiPerRegistro) {
		this.luoghiPerRegistro = luoghiPerRegistro;
	}

	public Long getLuogoID() {
		return luogoID;
	}

	public void setLuogoID(Long luogoID) {
		this.luogoID = luogoID;
	}
	
//	public String saveAction() {
//		
//		//get all existing value but set "editable" to false
//		for (Registro registro : registri){
//			
//			registro.setEditable(false);
//		}
//		//return to current page
//		return null;
//
//	}
//
//	public String editAction(Registro registro) {
//
//		registro.setEditable(true);
//		return null;
//	}

	
	public List<Registro> getRegistri() {

			if(registri == null ){
				registri = registroDAO.getRegistriOfTypePerLuogo(registroType, luogoID);
		
			}
			return registri;
		}
	public void setRegistri(List<Registro> registri) {
		this.registri = registri;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

	
}