package it.francescoermini.zappa.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import it.francescoermini.zappa.bean.producer.HttpParam;
import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.model.operazione.OperazioneTrattamento;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.operazione.OperazioneVendemmia;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneMisurazione;
import it.francescoermini.zappa.model.operazione.OperazioneRimontaggio;

@Model
public class OperazioneListController {
	
	@Inject
	private OperazioneDAO operazioneDAO;
	
	private  List<Operazione> operazioni;
	private Operazione operazione;
	private OperazioneType operazioneType;
	
	private final boolean True  = true;
	private final  boolean False = false;
	private boolean avviso;
	private boolean bool;
//	private List<OperazioneVendemmia> operazioneVendemmia;
	//private String luogo;
//	private String type;
//	private OperazioneType type;
//	private List<OperazioneTrattamento> operazioneTrattamento;
	
	
	private long id;
	
	
	
	
	@Inject @HttpParam("type")
	private String type;
	@Inject @HttpParam("luogo")
	private String luogo;
	
	
	
	
	
	
	
	public void init(){
	//luogo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("luogo");
//		type  =   FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		if( luogo != null && type != null ){
			Long luogoID = Long.valueOf(luogo);
			
			OperazioneType op = OperazioneType.valueOf(type);
			
			operazioni = operazioneDAO.getOperazionePerLuogoOfType(luogoID, op);
		}
		 
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public void setOperazioneVendemmia(List<OperazioneVendemmia> operazioneVendemmia) {
//		this.operazioneVendemmia = operazioneVendemmia;
//	}
//
//	public void setOperazioneTrattamento(List<OperazioneTrattamento> operazioneTrattamento) {
//		this.operazioneTrattamento = operazioneTrattamento;
//	}

	
	
	
	
	public List<Operazione> getOperazioni() {
		if( operazioni == null ){
			init();
			avviso = true;
		}
		return operazioni;
	}

	public void setOperazioni(List<Operazione> operazioni) {
		this.operazioni = operazioni;
	}

	
//	public List<Operazione> getOperazioneByType(OperazioneType operazioneTyoe){
//		
//	}
	
//	public List<OperazioneVendemmia> getOperazioneVendemmia() {
//		if( operazioneVendemmia == null){
//			operazioneVendemmia=operazioneDAO.getAllOperazioniVendemmia();
//		}
//		return operazioneVendemmia;
//	}
//
//
//
//	public List<OperazioneTrattamento> getOperazioneTrattamento() {
//		if( operazioneTrattamento == null ){
//			operazioneTrattamento = operazioneDAO.getAllOperazioniTrattamento();
//		}
//		return operazioneTrattamento;
//	}
//	
//	
//	
//	
//	public OperazioneVendemmia getOperazioneVendemmiaByID(){
//		return operazioneDAO.getOperazioneVendemmiaByID(id);
//	}
//
//	public OperazioneMisurazione getOperazioneMisurazioneByID(){
//		return operazioneDAO.getOperazioneMisurazioneByID(id);
//	}
//
//	public OperazioneRimontaggio getOperazioneRimontaggioByID(){
//		return operazioneDAO.getOperazioneRimontaggioByID(id);
//	}
//	
//	public OperazioneTrattamento getOperazioneTrattamentoByID(){
//		return operazioneDAO.getOperazioneTrattamentoByID(id);
//	}
//	
//	public Operazione getOperazioneByID(){
//		if( operazione == null ){
//		operazione =  operazioneDAO.getOperazioneByID(id);
//		}
//		return operazione;
//	}

	public Operazione getOperazione() {
		return operazione;
	}

	public void setOperazione(Operazione operazione) {
		this.operazione = operazione;
	}

	public boolean isTrue() {
		return True;
	}

	
	public boolean isFalse() {
		return False;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public OperazioneType getOperazioneType() {
		return operazioneType;
	}

	public void setOperazioneType(OperazioneType operazioneType) {
		this.operazioneType = operazioneType;
	}

	public boolean isAvviso() {
		return avviso;
	}

	public void setAvviso(boolean avviso) {
		this.avviso = avviso;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	

	
	

}
