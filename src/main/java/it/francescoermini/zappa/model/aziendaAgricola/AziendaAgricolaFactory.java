package it.francescoermini.zappa.model.aziendaAgricola;


import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroCantina;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.OggettoType;

public class AziendaAgricolaFactory {
	
//	
//	public static Cantina cantina(){
//		
//		return new Cantina();
//		
//	}
//	
//	public static Vigna vigna(){
//		return new Vigna();
//	}
//	
	
	public static Responsabile responsabile(){
		return new Responsabile();
	}
	

	public static Luogo luogo(LuogoType luogoType) {
		if( luogoType == LuogoType.VIGNA){
			return new Vigna(luogoType);
		}
		if( luogoType == LuogoType.CANTINA){
			return new Cantina(luogoType);
		}
		return null;
	}

	public static Oggetto oggetto(OggettoType oggettoType) {
//		OggettoType oggettoType = null;
//		if(luogoType ==LuogoType.CANTINA ){
//			oggettoType = OggettoType.BOTTE;
//		}
//		if(luogoType ==LuogoType.VIGNA){
//			oggettoType = OggettoType.FILARE;
//		}
//		
//		
		
		if( oggettoType == OggettoType.FILARE){
			return new Filare(oggettoType);
		}
		if( oggettoType == OggettoType.BOTTE){
			return new Botte(oggettoType);
		}
		return null;
	}
	
	public static Cantina cantina(){
		return new Cantina(LuogoType.CANTINA);
	}
	public static Vigna vigna(){
		return new Vigna(LuogoType.VIGNA);
	}
	public static Botte botte(){
		return new Botte(OggettoType.BOTTE);
	}
	public static Filare filare(){
		return new Filare(OggettoType.FILARE);
	}
	
	

}
