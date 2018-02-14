package it.francescoermini.zappa.bean.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;


@Named
@ApplicationScoped
public class LuogoConverter implements Converter {

	private static final long serialVersionUID = 8L;

    @Inject
    private AziendaAgricolaDAO azAgricolaDAO;
    
  

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if( value == null ){
			System.out.println(" luogo value null ");
			return null;
		}

		try {
               
              Long id = Long.valueOf(value);
     		  Luogo luogo = azAgricolaDAO.getLuogoByID(id);
     		  return luogo;
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
			System.out.println(" luogo  getAsString value null ");
			return null;
		}
		 try{
			Luogo luogo = (Luogo) value;
		    Long id = luogo.getId();
		    System.out.println("luogo id:"+id);
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
