package it.francescoermini.zappa.model.type;

public enum LuogoType {
	VIGNA("Vigna"),
	CANTINA("Cantina");
	
	public String pagina;
	
	private LuogoType(String pagina){
		this.pagina = pagina;
	}
	public String getPagina(){
		return pagina;
	}
	
}
