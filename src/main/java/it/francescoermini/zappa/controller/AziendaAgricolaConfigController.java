package it.francescoermini.zappa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import it.francescoermini.zappa.bean.producer.HttpParam;
import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.model.aziendaAgricola.AziendaAgricolaFactory;
import it.francescoermini.zappa.model.aziendaAgricola.Botte;
import it.francescoermini.zappa.model.aziendaAgricola.Cantina;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.OggettoType;






@Named
@SessionScoped
public class AziendaAgricolaConfigController implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AziendaAgricolaDAO aziendaAgricolaDAO;
	
//	@Inject
//	private AziendaAgricolaController aziendaAgricolaController;
	
	@Inject 
	private Conversation conversation;
	
//	
//	@Inject @HttpParam("type")
//	private String type;
//	
//	
//	

	
	static final Map<LuogoType, OggettoType > MAP_Luogo_Obj = associateLuogoObj ();	
	
	public static Map<LuogoType, OggettoType> associateLuogoObj(){
		Map<LuogoType,OggettoType> MAP_res = new HashMap<LuogoType, OggettoType>();
		MAP_res.put(LuogoType.CANTINA, OggettoType.BOTTE);
		MAP_res.put(LuogoType.VIGNA, OggettoType.FILARE);
		return Collections.unmodifiableMap(MAP_res);
		
	}
	
	
	
	private Responsabile responsabile;
	
	private Luogo luogo;
	
	private Oggetto oggetto;
	
	private List<Oggetto> oggetti;
	
	private LuogoType luogoType;// = LuogoType.CANTINA;
	
	private boolean hideDetails;
	
	private String messageDetailsChanger;
	
	
	
	

	@PostConstruct
	public void start() {
		
		
		messageDetailsChanger="Nascondi dettagli";
	}
	
	
	public void init(){
		//String  _luogoType = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("luogoType");
		
		//luogoType = LuogoType.valueOf(type);
		
		luogo = AziendaAgricolaFactory.luogo(luogoType);
		oggetto = AziendaAgricolaFactory.oggetto(MAP_Luogo_Obj.get(luogoType));
		oggetti=new ArrayList<Oggetto>();
		

	}
	
	
	public void initConversation(){ // event PreView rendr in operazione-select
		if (!FacesContext.getCurrentInstance().isPostback() 
			&& conversation.isTransient()) {
			
			conversation.begin();
		}
	}
	
	public String configAzienda(String type){
		this.luogo = null;
		this.oggetti=null;
		LuogoType luogoType = LuogoType.valueOf(type);
		this.luogoType = luogoType;
		

		switch(luogoType) {
		case VIGNA: 
			
			return "/azienda/azienda-config-vigna"				
					+"?faces-redirect=true";
			
		case CANTINA: 
			
			return "/azienda/azienda-config-cantina"					
					+"?faces-redirect=true";
			
		default:
			return null;
		}
	
	}
	
	public String  deleteAndExit(){ 
		
		
		
		//if(!conversation.isTransient()){
			conversation.end();
		//}
		
		return "/index?faces-redirect=true";
	}
	public String  saveAndCheck(){ 
		
		saveLuogo();
		
		//if(!conversation.isTransient()){
			conversation.end();
		//}
		
		return "/azienda/azienda-view?faces-redirect=true";
	}
	
	
	
	public String addOggetto(){

		this.oggetti.add(oggetto);
		this.oggetto = AziendaAgricolaFactory.oggetto(MAP_Luogo_Obj.get(luogoType));
		return "";
	}

	public String deleteOggetto(Oggetto oggetto){
		this.oggetti.remove(oggetto);
		return "";
	}
	

	
	@Transactional
	public void saveLuogo(){
		
		for( Oggetto ogg : oggetti ){
			ogg.setLuogo(luogo);
			luogo.getOggetti().add(ogg);
		}
		
		if(luogo.getNome().isEmpty() ){
			System.out.println("devi inserire un nome");
		}
		else{
		aziendaAgricolaDAO.saveLuogo(luogo);
		}
	
	}
	@Transactional
	public void saveResponsabile(){
		aziendaAgricolaDAO.saveResponsabile(responsabile);
	}
	

	public Luogo getLuogo() {
		if(luogo == null ){
			init();
		}
		return luogo;
	}
	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}
	public Oggetto getOggetto() {
		
		return oggetto;
	}
	public void setOggetto(Oggetto oggetto) {
		this.oggetto = oggetto;
	}
	public List<Oggetto> getOggetti() {
		return oggetti;
	}
	public void setOggetti(List<Oggetto> oggetti) {
		this.oggetti = oggetti;
	}
	public LuogoType getLuogoType() {
		return luogoType;
	}
	public void setLuogoType(LuogoType luogoType) {
		this.luogoType = luogoType;
	}
	public boolean isHideDetails() {
		return hideDetails;
	}
	public void setHideDetails(boolean hideDetails) {
		this.hideDetails = hideDetails;
	}
	public void hideDetailsChanger(){
		if( hideDetails == false ){
			
			messageDetailsChanger="Vedi dettagli";
			this.hideDetails = true;
		}
		else {
			messageDetailsChanger="Nascondi dettagli";
			this.hideDetails = false;
		}
	}
	public String getMessageDetailsChanger() {
		return messageDetailsChanger;
	}
	public void setMessageDetailsChanger(String messageDetailsChanger) {
		this.messageDetailsChanger = messageDetailsChanger;
	}


	public Responsabile getResponsabile() {
		if( responsabile == null){
			responsabile = new Responsabile();
		}
		return responsabile;
	}


	public void setResponsabile(Responsabile responsabile) {
		this.responsabile = responsabile;
	}



}
