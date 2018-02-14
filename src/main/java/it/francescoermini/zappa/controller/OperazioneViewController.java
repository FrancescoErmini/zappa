package it.francescoermini.zappa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import it.francescoermini.zappa.bean.producer.HttpParam;
import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.registro.Registro;


//@Model


@Named
@SessionScoped
public class OperazioneViewController implements Serializable {
	
	private static final long serialVersionUID = 9L;

	@Inject
	private OperazioneDAO operazioneDAO;
	
	private Long operazioneID;
	
	private Operazione operazione;
	
	private Operazione operazioneToChange;
	
	private List<Operazione> operazioni = new ArrayList<Operazione>();


	@Inject @HttpParam("operazioneID")
	private String operazioneIDString;
	
	
	
	
	
//	private boolean view_flag;
	
//	String _view_flag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("view_flag");
//	view_flag = Boolean.valueOf(_view_flag);
//	String _id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

	
	//@PostConstruct
//public void getHttpParam(){
////	String _view_flag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("view_flag");
////	view_flag = Boolean.valueOf(_view_flag);
//	String _id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("operazioneID");
//	try{
//		operazioneID = Long.valueOf(_id);
//	}
//	catch(Exception e){
//		System.out.print("quin on fa");
//	}
//}
//	


public void init(){
	
	  operazioneID = Long.valueOf(operazioneIDString);
	  System.out.println("OperazioneID vale :" + operazioneID);
//		if( view_flag == true){

				if( operazione == null && operazioneID != null ){
		
					operazione = operazioneDAO.getOperazioneByID(operazioneID);
					System.out.println("Operazione ID:"+ operazioneID);
				}
				else{
				//error
					System.out.println("error id is null");
				}	
				operazioni = operazioneDAO.getOperazionePerLuogoOfType(operazione.getLuogo().getId(), operazione.getOperazioneType());
}
//	}
public String end(){
		return "/index?faces-redirect=true";
}
	

public String edit(){
	//operazioneID = null;
	System.out.println("chiamato edit su OperazioneViewController con operazione id");
	System.out.println(operazioneID);
	//System.out.println(operazioneIDString);
	//System.out.println(operazione.getId());
	
	
//	return "/operazione/edit/ciao.xhtml?operazioneID="
//			+ operazioneID
//			+ "&faces-redirect=true";
		
		return "/operazione/edit/"+operazione.getOperazioneType().getLabel()
				+"?operazioneID="+operazioneID
				+"&faces-redirect=true";
}


@Transactional
public void save(Operazione operazione){
	if( operazione == null){
		init();
	}
	operazioneDAO.saveOperazione(operazione);
}



public String saveAction(Operazione operazioneTmp) {
	operazioneTmp.setEditable(false);
	//get all existing value but set "editable" to false
	for (Operazione operazione : operazioni){
		
		operazione.setEditable(false);
		
	}
	if( operazioneToChange != null){
	save(operazioneToChange);
	}
	//return to current page
	return null;

}

public String editAction(Operazione operazione) {

	operazione.setEditable(true);
	operazioneToChange = operazione; //solve performance issues
	return null;
}






	

	public long getOperazioneID() {
		
		return operazioneID;
	}


	public void setOperazioneID(long operazioneID) {
		this.operazioneID = operazioneID;
	}



	public Operazione getOperazione() {
		if( operazione == null ){
			init(); 
		}
		return operazione;
	}


	public void setOperazione(Operazione operazione) {
		this.operazione = operazione;
	}
//	public boolean isView_flag() {
//		return view_flag;
//	}
//	public void setView_flag(boolean view_flag) {
//		this.view_flag = view_flag;
//	}



	public void setOperazioneID(Long operazioneID) {
		this.operazioneID = operazioneID;
	}
	public String getOperazioneIDString() {
		return operazioneIDString;
	}
	public void setOperazioneIDString(String operazioneIDString) {
		this.operazioneIDString = operazioneIDString;
	}
	public OperazioneDAO getOperazioneDAO() {
		return operazioneDAO;
	}
	public void setOperazioneDAO(OperazioneDAO operazioneDAO) {
		this.operazioneDAO = operazioneDAO;
	}
	public Operazione getOperazioneToChange() {
		return operazioneToChange;
	}
	public void setOperazioneToChange(Operazione operazioneToChange) {
		this.operazioneToChange = operazioneToChange;
	}
	public List<Operazione> getOperazioni() {
		return operazioni;
	}
	public void setOperazioni(List<Operazione> operazioni) {
		this.operazioni = operazioni;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}