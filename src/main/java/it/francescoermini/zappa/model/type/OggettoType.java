package it.francescoermini.zappa.model.type;

public enum OggettoType {
	FILARE("filare"),
	BOTTE("botte");
	private String nomeOggetto;
	
	
	OggettoType(String nomeOggetto){
		this.nomeOggetto=nomeOggetto;
	}
	public String getNomeOggetto(){
		return nomeOggetto;
	}
	
}
