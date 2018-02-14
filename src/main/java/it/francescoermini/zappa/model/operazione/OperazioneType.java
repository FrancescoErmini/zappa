package it.francescoermini.zappa.model.operazione;

import it.francescoermini.zappa.model.registro.RegistroType;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.OggettoType;

public enum OperazioneType {
	
	VENDEMMIA("vendemmia", OperazioneVendemmia.class , LuogoType.VIGNA, RegistroType.REGISTROVENDEMMIA , OggettoType.FILARE), 
	TRATTAMENTO("trattamento",OperazioneTrattamento.class, LuogoType.VIGNA, RegistroType.REGISTROTRATTAMENTI, OggettoType.FILARE),

	CONFERITURA("conferitura", OperazioneConferitura.class, LuogoType.CANTINA, RegistroType.REGISTROCANTINA, OggettoType.BOTTE),
	PIGIATURADIRASPATURA("pigiaturadiraspatura", OperazionePigiaturaDiraspatura.class, LuogoType.CANTINA, RegistroType.REGISTROCANTINA, OggettoType.BOTTE),
	SVINATURA("svinatura",OperazioneSvinatura.class,  LuogoType.CANTINA, RegistroType.REGISTROCANTINA, OggettoType.BOTTE),
	FERMENTAZIONE("fermentazione", OperazioneFermentazione.class, LuogoType.CANTINA, RegistroType.REGISTROCANTINA, OggettoType.BOTTE),
	CORREZIONITAGLI("correzionitagli", OperazioneCorrezioniTagli.class, LuogoType.CANTINA, RegistroType.REGISTROCANTINA, OggettoType.BOTTE),
	
	RIMONTAGGIO("rimontaggio", OperazioneRimontaggio.class, LuogoType.CANTINA, RegistroType.NOREGISTRO,  OggettoType.BOTTE),
	MISURAZIONE("misurazione", OperazioneMisurazione.class, LuogoType.CANTINA, RegistroType.NOREGISTRO, OggettoType.BOTTE);
	
	private Class classe;
	private String label;
	private LuogoType luogoType;
	private RegistroType registroType;
	private OggettoType oggettoType; //TODO: messo per motivi 'pratici' nel render in jsf
	/*
	 * todo: change Label in operaztionPage or page or xhtmlpage
	 */
	private OperazioneType(String label, Class classe, LuogoType luogoType, RegistroType registroType, OggettoType oggettoType) {
		this.classe = classe;
        this.label = label;
        this.luogoType=luogoType;
        this.registroType=registroType;
        this.oggettoType=oggettoType;
    }

    public String getLabel() {
        return label;
    }
    public LuogoType getLuogoType(){
    	return luogoType;
    }

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLuogoType(LuogoType luogoType) {
		this.luogoType = luogoType;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}

	public RegistroType getRegistroType() {
		return registroType;
	}

	public void setRegistroType(RegistroType registroType) {
		this.registroType = registroType;
	}

	public OggettoType getOggettoType() {
		return oggettoType;
	}

	public void setOggettoType(OggettoType oggettoType) {
		this.oggettoType = oggettoType;
	}
	
}
