package it.francescoermini.zappa.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.dao.RegistroDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneFactory;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroFactory;


@Named
@ConversationScoped
public class OperazioneAddController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Conversation conversation;
	
	
	@Inject
	private OperazioneDAO operazioneDAO;

	@Inject
	private AziendaAgricolaDAO aziendaAgricolaDAO;
		
	@Inject
	private RegistroDAO registroDAO;

	private Registro registro;

	
	private OperazioneType operazioneType;
	
	private Operazione operazione;
	

	private List<Luogo> luoghiPerOperazione;
	private List<Oggetto> oggettiPerLuogo = new ArrayList<>();
	private List<Responsabile> responsabili;
	private List<Luogo> luoghi;
	
	private boolean typeValidation;
	private boolean validation2;
	private String validation2Message;
	
	private boolean view_flag;
	

//	Map<Date,Integer> hm;


	@PostConstruct
	public void createOnce(){
		
		responsabili = aziendaAgricolaDAO.getAllResponsabili();
		luoghi = aziendaAgricolaDAO.getAllLuoghi();
		
		initConversation();
		
//		hm = new IdentityHashMap<Date,Integer>();
//		hm.put(new Date(),1);
//		hm.put(new Date(),2);
		
	

	}
	
//	public String getDateOggi(){		
//	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//	Date date = new Date();
//	String sDate= sdf.format(date);
//	return sDate;
//	}
//	public String getOraOggi(){		
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//		Date date = new Date();
//		String sDate= sdf.format(date);
//		return sDate;
//		}
	/*
	 * Date x = new Date();
Date y = new Date();

Map<Date,Integer> hm = new IndentityHashMap<Date,Integer>();
hm.put(x,1);
hm.put(y,3);

	 */
//	public void init(){
//		if(conversation.isTransient()) {
//			conversation.begin();
//		}
//	}
	
	
	public void init(){ 
	
			operazione =  (Operazione) OperazioneFactory.createOperazione(this.operazioneType);
			luoghiPerOperazione=configuraLuoghiPerOperazioneType();

	}
	
	@Transactional
	public void add(){
		
			operazioneDAO.saveOperazione(getOperazione());
			aggiungiRegistro();
			
	}
	
	
	//TODO: add sposta dento a add? 
    public void aggiungiRegistro(){
			
		registro =  RegistroFactory.registroDaOperazione(operazione);
		if( registro != null ){
		registroDAO.saveRegistro(registro);
		}
	}
	
	
	public Operazione getOperazione(){
		if( operazione == null){
			init(); 
		}	
		return operazione;
	}
	
	public List<Luogo> configuraLuoghiPerOperazioneType(){
		
		List<Luogo> luoghiPerOperazione = new ArrayList<>();
		for( Luogo l : luoghi){
			if( operazioneType.getLuogoType() == l.getLuogoType()){
				luoghiPerOperazione.add(l);
			}
		}	
		return luoghiPerOperazione;
	}

	
	public void configuraOggettiPerLuogo(){
		oggettiPerLuogo = this.operazione.getLuogo().getOggetti();
	}
	
	public OperazioneType getOperazioneType() {
		return operazioneType;
	}

	public void setOperazioneType(OperazioneType operazioneType) {
		this.operazioneType = operazioneType;
	}

	public List<Luogo> getLuoghiPerOperazione() {
		return luoghiPerOperazione;
	}


	public List<Responsabile> getResponsabili() {
		
		return responsabili;
	}

	public void setOperazione(Operazione operazione) {
		this.operazione = operazione;
	}

	public void setLuoghiPerOperazione(List<Luogo> luoghiPerOperazione) {
		this.luoghiPerOperazione = luoghiPerOperazione;
	}

	public void setResponsabili(List<Responsabile> responsabili) {
		this.responsabili = responsabili;
	}


	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public OperazioneType[] getOperazioneTypeValues() {
	    return OperazioneType.values();
	  }


	public void initConversation(){ // event PreView rendr in operazione-select
		if (!FacesContext.getCurrentInstance().isPostback() 
			&& conversation.isTransient()) {
			
			conversation.begin();
		}
	}
	public String redirectOperazione(){ // step1. from operazione-select to operazione-add
		
		if(false == validateFirstRedirect()){ return null; }
				
		return "/operazione/operazione-add-2?faces-redirect=true";
	}
	
	
	private boolean validateFirstRedirect() {
		this.operazione = null; // in case, go back from step2 to step1 to change operaztionType
		this.luoghiPerOperazione=null;

		typeValidation = false;
		if(operazioneType == null ){
			typeValidation = true;		 //render error aside items menu	
			System.out.println("error, no operazione selected");	
			return false;
		}
		return true;
	}
	
	
	
	public String redirectSpecificOperation(){ //step2. from operazione-add to specif operazione page
		
		if(false == validateSecondRedirect()){ return null; }
		
		return "/operazione/add/"+operazioneType.getLabel()+"?cid="+conversation.getId()+"&faces-redirect=true";
	}
	
	
	private boolean validateSecondRedirect() {
		String msg="";
		boolean validField = true;
		
		if( operazione.getResponsabile() == null ){
			
			validField = false;
			msg += " Responsabile,";
		}
		
		if( operazione.getLuogo() == null ){
			
			validField = false;
			msg += " Luogo,";
		}
		if( operazione.getData() == null ){
			
			validField = false;
			msg += " Data,";
		}
		if ( operazione.getOra() == null ){
			
			validField = false;
			msg += " Ora,";
		}
		if( operazione.getOggetto() == null ){
			validField = false;
			msg += " Oggetto";
		}
		
		validation2Message = msg;
		validation2 = !validField;
		return validField;
	}
	public String saveAndCheck(){ 
		add();
		if(!conversation.isTransient()){
			conversation.end();
		}
		return "/operazione/view/"+operazione.getOperazioneType().getLabel()
				+"?operazioneID="+operazione.getId()
				+"&faces-redirect=true";
	}
	
	public String deleteAndExit(){ 
		this.operazione = null;
		if(!conversation.isTransient()){
			conversation.end();
		}
		return "/operazione/operazione-add-1?faces-redirect=true";
	}
	

	public boolean isTypeValidation() {
		return typeValidation;
	}
	public void setTypeValidation(boolean typeValidation) {
		this.typeValidation = typeValidation;
	}
	public List<Luogo> getLuoghi() {
		return luoghi;
	}
	public void setLuoghi(List<Luogo> luoghi) {
		this.luoghi = luoghi;
	}
	public List<Oggetto> getOggettiPerLuogo() {
		return oggettiPerLuogo;
	}
	public void setOggettiPerLuogo(List<Oggetto> oggettiPerLuogo) {
		this.oggettiPerLuogo = oggettiPerLuogo;
	}
	public boolean isView_flag() {
		return view_flag;
	}
	public void setView_flag(boolean view_flag) {
		this.view_flag = view_flag;
	}
	public boolean isValidation2() {
		return validation2;
	}
	public void setValidation2(boolean validation2) {
		this.validation2 = validation2;
	}
	public String getValidation2Message() {
		return validation2Message;
	}
	public void setValidation2Message(String validation2Message) {
		this.validation2Message = validation2Message;
	}

//
//
//	public Map<Date, Integer> getHm() {
//		return hm;
//	}
//
//	public void setHm(Map<Date, Integer> hm) {
//		this.hm = hm;
//	}



	
}

