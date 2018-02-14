package it.francescoermini.zappa.bean.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;


@Named
@ApplicationScoped
public class OggettoConverter implements Converter {

	private static final long serialVersionUID = 9L;

    @Inject
    private AziendaAgricolaDAO azAgricolaDAO;
    
  

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if( value == null ){
			System.out.println(" oggetto value null ");
			return null;
		}
		try {
               
              Long id = Long.valueOf(value);
     		  Oggetto oggetto = azAgricolaDAO.getOggettoByID(id);
     		 return oggetto;
           } catch (Exception e) {
               FacesMessage msg = new FacesMessage("Error in data conversion for Luogo getAsObject.");
               msg.setSeverity(FacesMessage.SEVERITY_ERROR);
               FacesContext.getCurrentInstance().addMessage("info", msg);
           }
		   return null;
		   
		
		  
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		

		if( value == null ){
			System.out.println(" loggetto  getAsString value null ");
			return null;
		}
		try{
			Oggetto oggetto = (Oggetto) value;
		    Long id = oggetto.getId();
		    System.out.println("oggetto id:"+id);
		    return String.valueOf(id);
		 }
		 catch(Exception e){
		
             FacesMessage msg = new FacesMessage("Error in data conversion for Luogo getAsString.");
             msg.setSeverity(FacesMessage.SEVERITY_ERROR);
             FacesContext.getCurrentInstance().addMessage("info", msg);
         }
		return null;	 
	}

}
