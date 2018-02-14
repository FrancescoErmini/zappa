package it.francescoermini.zappa.model.registro;

import it.francescoermini.zappa.model.type.LuogoType;

public enum RegistroType {
	
	
	REGISTROTRATTAMENTI("registro-trattamenti", RegistroTrattamenti.class, LuogoType.VIGNA),
	REGISTROVENDEMMIA("registro-vendemmia", RegistroVendemmia.class, LuogoType.VIGNA),
	REGISTROCANTINA("registro-cantina", RegistroCantina.class, LuogoType.CANTINA),
	NOREGISTRO("", null, null);
	
	private String registroPage;
	
	private Class classe;
	
	private LuogoType luogoType;
	
	private RegistroType(String registroPage, Class classe, LuogoType luogoType){
		this.registroPage=registroPage;
		this.classe = classe;
		this.luogoType = luogoType;
		
	}

	public String getRegistroPage() {
		return registroPage;
	}

	public void setRegistroPage(String registroPage) {
		this.registroPage = registroPage;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}

	public LuogoType getLuogoType() {
		return luogoType;
	}

	public void setLuogoType(LuogoType luogoType) {
		this.luogoType = luogoType;
	}

}
