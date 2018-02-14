package it.francescoermini.zappa.bean.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;

@Named
@ApplicationScoped
public class ResponsabileConverter implements Converter {

	private static final long serialVersionUID = 6L;

    @Inject
    private AziendaAgricolaDAO azAgricolaDAO;
    


	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if( value == null ){
			System.out.println(" responsabile value null ");
			return null;
		}
		
		try {
               
              Long id = Long.valueOf(value);
     		  Responsabile  responsabile = azAgricolaDAO.getResponsabileByID(id);
     		  return responsabile;
               
           } catch (Exception e) {
               FacesMessage msg = new FacesMessage("Error in data conversion for Responsabile getAsObject.");
               msg.setSeverity(FacesMessage.SEVERITY_ERROR);
               FacesContext.getCurrentInstance().addMessage("info", msg);
           }
		  return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if( value == null ){
			System.out.println(" responsabile getAsSting value null ");
			return null;
		}
		 try{
			Responsabile responsabile = (Responsabile) value;
		    Long id = responsabile.getId();
		    System.out.println("resposnabile id:"+id);
		    return String.valueOf(id);
		 }
		 catch(Exception e){
		
             FacesMessage msg = new FacesMessage("Error in data conversion for Responsabile getAsString.");
             msg.setSeverity(FacesMessage.SEVERITY_ERROR);
             FacesContext.getCurrentInstance().addMessage("info", msg);
         }
		 return null;	 
	}

}
