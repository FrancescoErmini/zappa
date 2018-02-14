package it.francescoermini.zappa.dao;

import java.util.List;

import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroType;
import it.francescoermini.zappa.model.type.LuogoType;

public interface RegistroDAO {

	public void saveRegistro(Registro registro);

	public List<Registro> getAllRegistri();
	
	public List<Registro> getRegistriPerNomeLuogo(String nomeLuogo);

	public List<Registro> getRegistriOfTypePerLuogo(RegistroType registroType, Long luogoID);
	

}
