package it.francescoermini.zappa.bean.startup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import it.francescoermini.zappa.controller.AziendaAgricolaConfigController;
import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.dao.RegistroDAO;
import it.francescoermini.zappa.model.aziendaAgricola.AziendaAgricolaFactory;
import it.francescoermini.zappa.model.aziendaAgricola.Botte;
import it.francescoermini.zappa.model.aziendaAgricola.Cantina;
import it.francescoermini.zappa.model.aziendaAgricola.Filare;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.aziendaAgricola.Vigna;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneConferitura;
import it.francescoermini.zappa.model.operazione.OperazioneCorrezioniTagli;
import it.francescoermini.zappa.model.operazione.OperazioneFactory;
import it.francescoermini.zappa.model.operazione.OperazioneFermentazione;
import it.francescoermini.zappa.model.operazione.OperazioneMisurazione;
import it.francescoermini.zappa.model.operazione.OperazionePigiaturaDiraspatura;
import it.francescoermini.zappa.model.operazione.OperazioneRimontaggio;
import it.francescoermini.zappa.model.operazione.OperazioneSvinatura;
import it.francescoermini.zappa.model.operazione.OperazioneTrattamento;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.operazione.OperazioneVendemmia;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroCantina;
import it.francescoermini.zappa.model.registro.RegistroFactory;
import it.francescoermini.zappa.model.type.Denominazione;
import it.francescoermini.zappa.model.type.EsposizioneVigna;
import it.francescoermini.zappa.model.type.Etichetta;
import it.francescoermini.zappa.model.type.FaseFenologica;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.OggettoType;
import it.francescoermini.zappa.model.type.TipologiaTrattamento;
import it.francescoermini.zappa.model.type.TipologiaVendemmia;
import it.francescoermini.zappa.model.type.Vitigno;

@Singleton
@Startup
public class Test {


	
	
	@Inject 
	private AziendaAgricolaDAO aziendaAgricolaDAO;
	
	@Inject 
	private OperazioneDAO operazioneDAO;
	
	@Inject
	private RegistroDAO registroDAO;
	
	private List<Luogo> luoghi;
	private List<Responsabile> responsabili;
	private Registro registro;
	
	static final Map<LuogoType, OggettoType > MAP_Luogo_Obj = AziendaAgricolaConfigController.associateLuogoObj ();	
	
	static String tipologiaBotte [] = {
			"Accaio",
			"Cemento",
	};
	static String tipologiaFilare [] = {
			"San giovese",
			"Colorinino",
	};
	
	
	
	
	
	String[] trattamenti = {
			  "zolfo", 
			  "rame", 
			  "bupirimate", 
			  "spiroxamina",
			  "cimoxanil"  
	};
	
	float carico_uva ;
	float scarico_uva;
	float carico_raspi;
	float scarico_raspi;
	float carico_vinaccia;
	float scarico_vinaccia;
	float carico_feccia;
	float scarico_feccia;
	float carico_mosto;
	float scarico_mosto;
	float carico_taglio;
	float resa;
	
	private Operazione operazione;
	
	@PostConstruct
	public void testAziendaAgricola(){
		
		creaAziendaAgricola();
		
		luoghi = aziendaAgricolaDAO.getAllLuoghi();
		responsabili = aziendaAgricolaDAO.getAllResponsabili();
		
		if( luoghi == null || responsabili == null ){
			System.out.println("ERRORE creaAziendaAgricola non funziona");
		}
		else {
			createRegisters();
		}
		
	}
    
	
	public void createRegisters(){
		
		
		
		for(Luogo luogo : luoghi){
		
			for(Oggetto oggetto : luogo.getOggetti()) {
				  
				Responsabile responsabile = responsabili.get((int)(Math.random()*responsabili.size()));
			
		
								for(OperazioneType o : OperazioneType.values()){
								//	registro = RegistroFactory.registro(oggetto);
									if( o.getLuogoType() == luogo.getLuogoType()){
												//operazione = (Operazione) getOperazioneTest(o.getClasse());
												operazione = (Operazione) OperazioneFactory.createOperazione(o);
												
												operazione.setOperazioneType(o);
												operazione.setData(new Date());
												operazione.setOra(new Date());
												operazione.setLuogo(luogo);
											//	operazione.setNomeLuogo(luogo.getNome());
											//	operazione.setLuogoType(o.getLuogoType());
												operazione.setOggetto(oggetto);
											//	operazione.setNumeroOggetto(oggetto.getNumero());
												operazione.setResponsabile(responsabile);
											//	operazione.setNomeResponsabile(responsabile.getNome());
//												registro.setOperazione(operazione);
												//registro.setData(new Date());
												//registro.setOggetto(oggetto);
												
												
												if( operazione instanceof OperazioneVendemmia){
													((OperazioneVendemmia)operazione).setQuintaliUVA((int)(20+Math.random()*10));
													((OperazioneVendemmia)operazione).setVitigno(Vitigno.values()[(int)(Math.random()*Vitigno.values().length)]);
													((OperazioneVendemmia)operazione).setTipologiaVendemmia(TipologiaVendemmia.values()[(int)(Math.random()*TipologiaVendemmia.values().length)]);
											
													
												}
												if( operazione instanceof OperazioneMisurazione){
													((OperazioneMisurazione)operazione).setTemperatura((float)25.0);
													
													
												}
												if( operazione instanceof OperazioneTrattamento){
													
													((OperazioneTrattamento)operazione).setTipologiaTrattamento(TipologiaTrattamento.values()[(int)(Math.random()*TipologiaTrattamento.values().length)]);
													((OperazioneTrattamento)operazione).setFaseFenologica(FaseFenologica.values()[(int)(Math.random()*FaseFenologica.values().length)]);
													((OperazioneTrattamento)operazione).setDescrizioneTrattamento(trattamenti[(int)(Math.random()*trattamenti.length)]);
													
												}
												if( operazione instanceof OperazioneRimontaggio){
													((OperazioneRimontaggio)operazione).setDurata((int)(Math.random()*20));
													
													
												}
												
												if( operazione instanceof OperazioneConferitura){
													carico_uva = (float)(20+(int)(Math.random()*10));
													
													((OperazioneConferitura)operazione).setCarico(carico_uva);
													((OperazioneConferitura)operazione).setDenominazione(Denominazione.values()[(int)(Math.random()*Denominazione.values().length)]);
													((OperazioneConferitura)operazione).setEtichetta(Etichetta.values()[(int)(Math.random()*Etichetta.values().length)]);
													
													
													//registro.getOperazione().add(operazione);
//													((RegistroCantina)registro).setCaricoUVA(carico_uva);
//													((RegistroCantina)registro).setDenominazione(Denominazione.values()[(int)(Math.random()*Denominazione.values().length)]);
//													((RegistroCantina)registro).setVitigno(Vitigno.values()[(int)(Math.random()*Vitigno.values().length)]);
												
												}
												
												if( operazione instanceof OperazionePigiaturaDiraspatura){
													carico_raspi = (float)(1+0.01*(int)(Math.random()*10));
													((OperazionePigiaturaDiraspatura)operazione).setCarico(carico_raspi);
//													((RegistroCantina)registro).setCaricoRaspi(carico_raspi);
													
													//registro.getOperazione().add(operazione);
												
												}
												if( operazione instanceof OperazioneSvinatura){
													carico_vinaccia = (float)(3+0.01*(int)(Math.random()*10));
													((OperazioneSvinatura)operazione).setCarico(carico_vinaccia);
//													((RegistroCantina)registro).setCaricoVinacce(carico_vinaccia);
													scarico_uva = carico_uva - carico_vinaccia - carico_raspi;
													scarico_vinaccia = carico_vinaccia;
													scarico_raspi = carico_raspi;
													carico_mosto = scarico_uva; 
													//
													//registro.getOperazione().add(operazione);

//													((RegistroCantina)registro).setCaricoMosto(carico_mosto);
//													((RegistroCantina)registro).setScaricoVinacce(scarico_vinaccia);
//													((RegistroCantina)registro).setScaricoRaspi(scarico_raspi);
//													((RegistroCantina)registro).setScaricoUVA(scarico_uva);
													
												}
												if( operazione instanceof OperazioneFermentazione){
													
													carico_feccia =(float)(0+0.01*(int)(Math.random()*10));
													((OperazioneFermentazione)operazione).setCarico(carico_feccia);
											
													
													
													scarico_feccia=carico_feccia;
													
												}
												
												if( operazione instanceof OperazioneCorrezioniTagli){
													carico_taglio = (float)(1+0.01*(int)(Math.random()*10));
													((OperazioneCorrezioniTagli)operazione).setCarico(carico_taglio);
													
													
												
													
													
													//((RegistroCantina)registro).setScaricoMosto(scarico_mosto);
													
													//
													//registro.getOperazione().add(operazione);
												} 
												
												//().setCarico(20);
												//(o.getClasse()).cast((Object)operazione).setCarico(20);
//												operazione = (operazione.getClass()).cast(operazione);												 Method[] method = operazione.getClass().getMethods();
//												 try {
//													method[0].invoke(operazione, 20);
//												} catch (IllegalAccessException | IllegalArgumentException
//														| InvocationTargetException e) {
//													// TODO Auto-generated catch block
//													e.printStackTrace();
//												}
												//((o.getClasse().getClass().newInstance()) cast().setName("ciao")).setCarico(20);
												//(Operazione<(o.getClasse())>operazione).setCarico(20);
												//? extends Operazione ((Object) operazione).setCarico();
//												Object a = (o.getClasse().cast(operazione));
//												a.setCarico(20);
												//(OperazioneFactory.casting(operazione)).setCarico(20);
												
												//operazione.setCarico(20);
												//Registro registro = RegistroFactory.registroDaOperazione(operazione);
												
												//registroDAO.saveRegistro(registro);
												
												
												operazioneDAO.saveOperazione(operazione);
												registro =  RegistroFactory.registroDaOperazione(operazione);
												//operazione.setRegistro(registro);
												
												
												if( registro != null ){
												
												registroDAO.saveRegistro(registro);
												}
												operazione = null;
									}	
									
								}
						}
			}
		

		
		
		
		
		
		
	}

	public void creaAziendaAgricola(){
		/*
		 * CANTINA: NIPOZZANO - VIGNETI: MORMORETO E MONTESODI
		 */
		Cantina nipozzano = creaCantina("Nipozzano");	
		Botte[] bottiNipozzano = new Botte[5+(int)(Math.random()*10)];	
		for(int i=0; i<bottiNipozzano.length; i++){
			bottiNipozzano[i] = creaBotte(nipozzano, i, (int)(Math.random()*10+1), tipologiaBotte[(int)(Math.random()*tipologiaBotte.length)]);
		}		
		Vigna mormoreto = creaVigna("Mormoreto", 330, 25, EsposizioneVigna.sud, "Terreni ricchi di sabbia, ben drenati. Discreta presenza di calcio. pH neutro o leggermente alcalino");
		Filare[] filariMormoreto = new Filare[5+(int)(Math.random()*10)];
		for(int i=0; i<filariMormoreto.length;i++){
			filariMormoreto[i] = creaFilare(mormoreto, i, tipologiaFilare[(int)(Math.random()*tipologiaFilare.length)]);
		}	
		Vigna montesodi = creaVigna("Montesodi", 400, 20, EsposizioneVigna.sud_ovest, "Arido e sassoso, di alberese, argilloso e calcareo, ben drenato, poco ricco di sostanza organica");
		Filare[] filariMontesodi = new Filare[5+(int)(Math.random()*10)];
		for(int i=0; i<filariMontesodi.length;i++){
			filariMontesodi[i] = creaFilare(montesodi, i, tipologiaFilare[(int)(Math.random()*tipologiaFilare.length)]);
		}
		/*
		 * CANTINA: POMINO - VIGNETO BENEFIZIO
		 */
		
		Cantina pomino = creaCantina("Pomino");
		Botte[] bottiPomino = new Botte[5+(int)(Math.random()*10)];	
		for(int i=0; i<bottiPomino.length; i++){
			bottiPomino[i] = creaBotte(pomino, i, (int)(Math.random()*10+1), tipologiaBotte[(int)(Math.random()*tipologiaBotte.length)]);
		}
		Vigna benefizio = creaVigna("Benefizio", 700, 9, EsposizioneVigna.sud_ovest, "Terreni ricchi di sabbia, forte presenza di scheletro. Molto drenanti. Ricchi in elementi minerali. PH acido o leggermente acido.");
		Filare[] filariBenefizio = new Filare[5+(int)(Math.random()*10)];
		for(int i=0; i<filariBenefizio.length;i++){
			filariBenefizio[i] = creaFilare(benefizio, i, tipologiaFilare[(int)(Math.random()*tipologiaFilare.length)]);
		}
		
		aziendaAgricolaDAO.saveLuogo(nipozzano);
		aziendaAgricolaDAO.saveLuogo(mormoreto);
		aziendaAgricolaDAO.saveLuogo(montesodi);
		
		aziendaAgricolaDAO.saveLuogo(pomino);
		aziendaAgricolaDAO.saveLuogo(benefizio);
		
		aziendaAgricolaDAO.saveResponsabile(responsabile("Giulio d'Afflitto"));
		aziendaAgricolaDAO.saveResponsabile(responsabile("Francesco Ermini"));

		
	}
	
	
	public Luogo luogo(String name, LuogoType luogoType){
		
		Luogo luogo = AziendaAgricolaFactory.luogo(luogoType);
		luogo.setNome(name);
		luogo.setLuogoType(luogoType);
		
		
		if(luogoType == LuogoType.VIGNA){ 
			((Vigna)luogo).setEsposizione(EsposizioneVigna.values()[(int)(Math.random()*EsposizioneVigna.values().length)]);
			((Vigna)luogo).setAltitudine(400);
			
		}
		
		
		Oggetto[] oggetti = new Oggetto[5];
		
		
		
		for(int i=0; i<5; i++){
			oggetti[i] = AziendaAgricolaFactory.oggetto(MAP_Luogo_Obj.get(luogoType));
			
			oggetti[i].setLuogo(luogo);
			oggetti[i].setNumero(i);
			if( luogoType == LuogoType.VIGNA){
				((Filare) oggetti[i]).setTipo(tipologiaFilare[(int)(Math.random()*tipologiaFilare.length)]);
				
			}
			if( luogoType == LuogoType.CANTINA){
				((Botte) oggetti[i]).setCapacita(1000);
				//((Botte) oggetti[i]).setTipo(tipologiaBotte[(int)(Math.random()*tipologiaBotte.length)]);

				((Botte) oggetti[i]).setTipo(tipologiaBotte[(int)(Math.random()*tipologiaBotte.length)]);
			}
			
			luogo.getOggetti().add(oggetti[i]);
			
		}
		
	return luogo;	
	}
	
	
	public Cantina creaCantina(String name){
		Cantina cantina = AziendaAgricolaFactory.cantina();
		cantina.setNome(name);
		cantina.setLuogoType(LuogoType.CANTINA);
		
		return cantina;
	}
	
	public Vigna creaVigna(String name,  int altitudine, int superficie, EsposizioneVigna esposizioneVigna , String descrizioneVigna){
		Vigna vigna = AziendaAgricolaFactory.vigna();
		
		vigna.setNome(name);
		vigna.setLuogoType(LuogoType.VIGNA);
		vigna.setEsposizione(esposizioneVigna);
		vigna.setAltitudine(altitudine);
		vigna.setSuperficie(superficie);
		vigna.setDescrizioneVigna(descrizioneVigna);
		return vigna;
	}
	
	public Filare creaFilare(Vigna vigna, int numeroFilare, String vitigno ){
		Filare filare = AziendaAgricolaFactory.filare();
		filare.setOggettoType(OggettoType.FILARE);
		filare.setLuogo(vigna);
		filare.setNumero(numeroFilare);
		filare.setTipo(vitigno);
		vigna.getOggetti().add(filare);
		return filare;
	}
	public Botte creaBotte(Cantina cantina, int numeroBotte, int capacitaBotte, String tipoBotte){
		Botte botte = AziendaAgricolaFactory.botte();
		botte.setOggettoType(OggettoType.BOTTE);
		botte.setLuogo(cantina);
		botte.setNumero(numeroBotte);
		botte.setCapacita(capacitaBotte);
		botte.setTipo(tipoBotte);
		cantina.getOggetti().add(botte);
		return botte;
	}
	
	
	
	

	public Responsabile responsabile(String name){
		Responsabile responsabile = AziendaAgricolaFactory.responsabile();
		responsabile.setNome(name);
		return responsabile;
	}

	
//	public  Object getOperazioneTest(Class<?> c){
//		Object obj = null;
//		try {
//			Constructor<?> cons  = c.getDeclaredConstructor();
//			obj = cons.newInstance();
//			c.cast(obj);
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| NoSuchMethodException | SecurityException e) {
//			System.out.print("FALLITO A ISTANZIARE OPERAZIONE SPECIFICA");
//			e.printStackTrace();
//		}
//		return obj;
//	}
	
	
	

}
