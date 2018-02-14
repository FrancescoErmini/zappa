package it.francescoermini.zappa.dao;

import java.util.List;

import it.francescoermini.zappa.model.aziendaAgricola.Botte;
import it.francescoermini.zappa.model.aziendaAgricola.Cantina;
import it.francescoermini.zappa.model.aziendaAgricola.Filare;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.aziendaAgricola.Vigna;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.registro.RegistroCantina;
import it.francescoermini.zappa.model.type.LuogoType;
import it.francescoermini.zappa.model.type.OggettoType;

public interface AziendaAgricolaDAO {
	
	public void saveLuogo(Luogo luogo);
	public void saveResponsabile(Responsabile responsabile);

	public List<Responsabile> getAllResponsabili();
	public List<Luogo> getAllLuoghi();

	public Responsabile getResponsabileByID(long id);
	public Luogo getLuogoByID(long luogoID);
	public Oggetto getOggettoByID(long oggettoID);	


}
