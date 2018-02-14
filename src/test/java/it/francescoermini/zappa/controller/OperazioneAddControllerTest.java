package it.francescoermini.zappa.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.reflect.FieldUtils;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;
import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.dao.RegistroDAO;
import it.francescoermini.zappa.model.aziendaAgricola.AziendaAgricolaFactory;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneFactory;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.type.LuogoType;

public class OperazioneAddControllerTest {
	
	private OperazioneAddController  operazioneAddController;
	private OperazioneDAO operazioneDAO;
	private AziendaAgricolaDAO aziendaAgricolaDAO;
	private RegistroDAO registroDAO;
	
	private Luogo luogo;
	private Oggetto oggetto;
	private Operazione operazione;
	private Registro registro;
	private Responsabile responsabile;
	
	
	private Long operazioneID;
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	     operazioneAddController = new OperazioneAddController();
		
		operazioneDAO = mock(OperazioneDAO.class);
		aziendaAgricolaDAO = mock(AziendaAgricolaDAO.class);
		registroDAO= mock(RegistroDAO.class);
		
		operazione = (Operazione) OperazioneFactory.createOperazione(OperazioneType.CONFERITURA);

		luogo = AziendaAgricolaFactory.luogo(LuogoType.CANTINA);
		oggetto = AziendaAgricolaFactory.oggetto(AziendaAgricolaConfigController.associateLuogoObj().get(LuogoType.CANTINA));
		responsabile = AziendaAgricolaFactory.responsabile();
		
		operazione.setLuogo(luogo);
		operazione.setOggetto(oggetto);
		operazione.setResponsabile(responsabile);	
		
		
		
	
		try {
			//FieldUtils.writeField(operazione, "id", operazioneID, true); io uso strategy = AUTO
			//FieldUtils.writeField(user, "id", userId, true);//luogo resposnabile
			FieldUtils.writeField(operazioneAddController, "operazioneDAO", operazioneDAO, true);
			FieldUtils.writeField(operazioneAddController, "aziendaAgricolaDAO", aziendaAgricolaDAO, true);
			FieldUtils.writeField(operazioneAddController, "registroDAO", registroDAO, true);
			FieldUtils.writeField(operazioneAddController, "operazioneType", OperazioneType.CONFERITURA, true);
			
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
