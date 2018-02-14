package it.francescoermini.zappa.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import it.francescoermini.zappa.bean.producer.HttpParam;
import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.model.operazione.Operazione;


@Model
public class OperazioneEditController {

	

	@Inject
	private OperazioneDAO operazioneDAO;
	
	
	private Operazione operazione;
	
	private Long operazioneID;
	
	
	@Inject @HttpParam("operazioneID")
	private String operazioneIDString;
	
	public void init(){
		 operazioneID = Long.valueOf(operazioneIDString);
		if (operazione == null && operazioneID != null ){
			operazione = operazioneDAO.getOperazioneByID(operazioneID);
		}
		else {
			System.out.println("error id is null");
		}
		
	}
	
//	
//	@PostConstruct
//	public void getHttpParam(){
////		String _view_flag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("view_flag");
////		view_flag = Boolean.valueOf(_view_flag);
//		String _id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("operazioneID");
//		operazioneID = Long.valueOf(_id);
//	}
//	
	
//	public void add(){
//			
//			operazioneDAO.save();
//	}



	public Operazione getOperazione() {
		if( operazione == null ){
			init();
		}
		return operazione;
	}



	public void setOperazione(Operazione operazione) {
		this.operazione = operazione;
	}



	public long getOperazioneID() {
		return operazioneID;
	}



	public void setOperazioneID(long operazioneID) {
		this.operazioneID = operazioneID;
	}
	
	
	
	@Transactional
	public void save(){
		if( operazione == null){
			init();
		}
		operazioneDAO.saveOperazione(operazione);
	}


	public OperazioneDAO getOperazioneDAO() {
		return operazioneDAO;
	}


	public void setOperazioneDAO(OperazioneDAO operazioneDAO) {
		this.operazioneDAO = operazioneDAO;
	}


	public String getOperazioneIDString() {
		return operazioneIDString;
	}


	public void setOperazioneIDString(String operazioneIDString) {
		this.operazioneIDString = operazioneIDString;
	}


	public void setOperazioneID(Long operazioneID) {
		this.operazioneID = operazioneID;
	}

}
