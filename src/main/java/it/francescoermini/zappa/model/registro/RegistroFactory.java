package it.francescoermini.zappa.model.registro;

import java.util.Date;

import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneConferitura;
import it.francescoermini.zappa.model.operazione.OperazioneCorrezioniTagli;
import it.francescoermini.zappa.model.operazione.OperazioneFermentazione;
import it.francescoermini.zappa.model.operazione.OperazionePigiaturaDiraspatura;
import it.francescoermini.zappa.model.operazione.OperazioneSvinatura;
import it.francescoermini.zappa.model.operazione.OperazioneTrattamento;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.operazione.OperazioneVendemmia;
import it.francescoermini.zappa.model.type.Denominazione;
import it.francescoermini.zappa.model.type.Etichetta;
import it.francescoermini.zappa.model.type.FaseFenologica;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.TipologiaTrattamento;
import it.francescoermini.zappa.model.type.TipologiaVendemmia;
import it.francescoermini.zappa.model.type.Vitigno;





public class RegistroFactory {

private static float carico_uva;
private  static float scarico_uva;
private  static float carico_raspi;
private  static float scarico_raspi;
private  static float carico_vinaccia;
private  static float scarico_vinaccia;
private static  float carico_feccia;
private static   float scarico_feccia;
private static  float carico_mosto;
private static  float scarico_mosto;
private static float carico_taglio;
private static float scarico_taglio;
private static  float resa;


	
	
	
	
	
	
	public static Registro registroDaOperazione(Operazione operazione){
		
	
		
		
		if(operazione.getOperazioneType().getRegistroType() == RegistroType.NOREGISTRO){
			return null;
		}
		
		Registro registro = null;;
		if( operazione.getOperazioneType().getRegistroType() == RegistroType.REGISTROCANTINA){
		
			 registro = new RegistroCantina(operazione);
//			 System.out.println("creato registro cantina");
		}
		if( operazione.getOperazioneType().getRegistroType() == RegistroType.REGISTROVENDEMMIA ){
		  registro = new RegistroVendemmia(operazione);
		  
		  
		 
		}
		if( operazione.getOperazioneType().getRegistroType() == RegistroType.REGISTROTRATTAMENTI){
			registro = new RegistroTrattamenti(operazione);
		}
		
		
		//registro.setOperazione(operazione);
		registro.setData(new Date());
		registro.setNomeRegistro(operazione.getLuogo().getNome());
		registro.setRegistroType(operazione.getOperazioneType().getRegistroType());
		//registro.setOggetto(operazione.getOggetto());
		registro.setLuogo(operazione.getLuogo());
		

		
				
				if( operazione.getOperazioneType() == OperazioneType.VENDEMMIA){
				int q =((OperazioneVendemmia)operazione).getQuintaliUVA();
				((RegistroVendemmia)registro).setQuintaliUVA(q);
				Vitigno vitigno = ((OperazioneVendemmia)operazione).getVitigno();
				((RegistroVendemmia)registro).setVitigno(vitigno);
				TipologiaVendemmia tipologiaVendemmia = ((OperazioneVendemmia)operazione).getTipologiaVendemmia();
				((RegistroVendemmia)registro).setTipologiaVendemmia(tipologiaVendemmia);
					
				}
				
				if( operazione.getOperazioneType() == OperazioneType.TRATTAMENTO){
				TipologiaTrattamento tipologiaTrattamento = ((OperazioneTrattamento)operazione).getTipologiaTrattamento();
				FaseFenologica faseFenologica = ((OperazioneTrattamento)operazione).getFaseFenologica();
				String descrizioneTrattamento = ((OperazioneTrattamento)operazione).getDescrizioneTrattamento();
				((RegistroTrattamenti)registro).setTipologiaTrattamento(tipologiaTrattamento);
				((RegistroTrattamenti)registro).setFaseFenologica(faseFenologica);
				((RegistroTrattamenti)registro).setDescrizioneTrattamento(descrizioneTrattamento);
				}	
				
				
				if( operazione.getOperazioneType() == OperazioneType.CONFERITURA){
					 carico_uva = ((OperazioneConferitura)operazione).getCarico();
					 Denominazione  denominazione  =  ((OperazioneConferitura)operazione).getDenominazione();
					Etichetta etichetta =  ((OperazioneConferitura)operazione).getEtichetta();
					 //registro.getOperazione().add(operazione);
					((RegistroCantina)registro).setCaricoUVA(carico_uva);
					((RegistroCantina)registro).setDenominazione(denominazione);
					((RegistroCantina)registro).setEtichetta(etichetta);
					
					System.out.println("settati dati registro conferitura");
				
				}
				
				if( operazione.getOperazioneType() == OperazioneType.PIGIATURADIRASPATURA){
					carico_raspi = ((OperazionePigiaturaDiraspatura)operazione).getCarico();
					((RegistroCantina)registro).setCaricoRaspi(carico_raspi);
					
					//registro.getOperazione().add(operazione);
				
				}
				if( operazione.getOperazioneType() == OperazioneType.SVINATURA){
					carico_vinaccia = ((OperazioneSvinatura)operazione).getCarico();
					
					((RegistroCantina)registro).setCaricoVinacce(carico_vinaccia);
					scarico_uva = carico_uva - carico_vinaccia - carico_raspi;
					scarico_vinaccia = carico_vinaccia;
					scarico_raspi = carico_raspi;
					carico_mosto = scarico_uva; 
					//
					//registro.getOperazione().add(operazione);
	
					((RegistroCantina)registro).setCaricoMosto(carico_mosto);
					((RegistroCantina)registro).setScaricoVinacce(scarico_vinaccia);
					((RegistroCantina)registro).setScaricoRaspi(scarico_raspi);
					((RegistroCantina)registro).setScaricoUVA(scarico_uva);
					
				}
				if( operazione.getOperazioneType() == OperazioneType.FERMENTAZIONE){
					
					carico_feccia = ((OperazioneFermentazione)operazione).getCarico();
					((RegistroCantina)registro).setCaricoFeccia(carico_feccia);
					//
					//registro.getOperazione().add(operazione);
					scarico_feccia=carico_feccia;
					((RegistroCantina)registro).setScaricoFeccia(scarico_feccia);
					
				}
				
				if( operazione.getOperazioneType() == OperazioneType.CORREZIONITAGLI){
					 carico_taglio = ((OperazioneCorrezioniTagli)operazione).getCarico();
					 ((RegistroCantina)registro).setCaricoTaglio(carico_taglio);
					 
					 scarico_taglio = 0;// giusto?
					 ((RegistroCantina)registro).setScaricoTaglio(scarico_taglio);
					 
					 
					 scarico_mosto = carico_mosto - carico_feccia + carico_taglio;
					((RegistroCantina)registro).setScaricoMosto(scarico_mosto);

					 
					 resa = (scarico_mosto*100) /  carico_uva;
						((RegistroCantina)registro).setPercentualeResa((int)resa);
									} 
	
				
		return registro;
	}
			
			
			
			
			
			
		
		
		

			
			
			

	
}
