package it.francescoermini.zappa.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;

import it.francescoermini.zappa.bean.producer.HttpParam;
import it.francescoermini.zappa.dao.RegistroDAO;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroType;
import it.francescoermini.zappa.model.type.LuogoType;


//@Model se model l'edit non fa, se Session, non si resetta 'registro' e va in errore nel passare da RegistroCantina a RegistroVigna o viceversa
@ViewScoped
@Named
public class RegistroController implements Serializable{
	
	private static final long serialVersionUID = 5L;


	@Inject
	private RegistroDAO registroDAO;
//	@Inject
//	private AziendaAgricolaDAO aziendaAgricolaDAO;
	
//	private String nomeLuogo;
	//private Luogo luogo;
	
	
	
	private List<Registro> registri;

	@Inject @HttpParam("luogo")
	private String luogo;
	
	@Inject @HttpParam("type")
	private String type;
	
	
	
	private Long luogoID;
	private RegistroType registroType;
	
	private Date date;
	
	private String data;
	
	private Registro registroToChange;
//	@PostConstruct
//	public void getHttpParam(){
//		String _id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("luogoID");
//		luogoID = Long.valueOf(_id);
//		String _type = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("registroType");
//		
////		nomeLuogo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nomeLuogo");
//	}
	
	
	public void init(){

		registroType = RegistroType.valueOf(type);
		luogoID  =Long.valueOf(luogo);
		
	    registri = registroDAO.getRegistriOfTypePerLuogo(registroType, luogoID);

		
	}
	@Transactional
	public void saveRegistro(Registro registro){
		registroDAO.saveRegistro(registro);
	}
	
	
	public String saveAction() {
	
		//get all existing value but set "editable" to false
		for (Registro registro : registri){
			
			registro.setEditable(false);
			
		}
		if( registroToChange != null){
		saveRegistro(registroToChange);
		}
		//return to current page
		return null;

	}

	public String editAction(Registro registro) {

		registro.setEditable(true);
		registroToChange = registro; //solve performance issues
		return null;
	}

	
	public List<Registro> getRegistri() {

			if(registri == null ){
				init();					
			}
			return registri;
	}
	public void setRegistri(List<Registro> registri) {
		this.registri = registri;
	}


	

	

	public RegistroType getRegistroType() {
		return registroType;
	}

	public void setRegistroType(RegistroType registroType) {
		this.registroType = registroType;
	}




	public Long getLuogoID() {
		return luogoID;
	}




	public void setLuogoID(Long luogoID) {
		this.luogoID = luogoID;
	}

	public Date getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm"); // creo l'oggetto

		this.data = sdf.format(new Date());
		//this.date = new Date();
		
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getData() {
		 
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	
	

	
}