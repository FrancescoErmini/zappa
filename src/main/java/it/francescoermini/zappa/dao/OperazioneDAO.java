package it.francescoermini.zappa.dao;

import java.util.List;

import it.francescoermini.zappa.model.operazione.OperazioneTrattamento;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.operazione.OperazioneVendemmia;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneMisurazione;
import it.francescoermini.zappa.model.operazione.OperazioneRimontaggio;

public interface OperazioneDAO {
	
	public void saveOperazione(Operazione operazione);
	
	
	public List<Operazione> getAllOperazioni();

	public Operazione getOperazioneByID(long operazioneID);	
	
	public Operazione getOperazionePerRegistroCantina(long luogoID, long oggettoID, OperazioneType operazioneType);

//	public List<Operazione> getOperazionePerLuogo(String nomeLuogo, String type);


	public List<Operazione> getOperazionePerLuogoOfType(long luogoID, OperazioneType op);
}

