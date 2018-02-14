//package it.francescoermini.zappa.dao.impl;
//
//import static org.junit.Assert.*;
//
//import java.util.Date;
//import java.util.Map;
//
//import org.apache.commons.lang3.reflect.FieldUtils;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runners.model.InitializationError;
//
//import it.francescoermini.zappa.controller.AziendaAgricolaConfigController;
//import it.francescoermini.zappa.dao.JpaTest;
//import it.francescoermini.zappa.dao.OperazioneDAO;
//import it.francescoermini.zappa.model.aziendaAgricola.AziendaAgricolaFactory;
//import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
//import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
//import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
//import it.francescoermini.zappa.model.operazione.Operazione;
//import it.francescoermini.zappa.model.operazione.OperazioneFactory;
//import it.francescoermini.zappa.model.operazione.OperazioneType;
//import it.francescoermini.zappa.model.registro.Registro;
//import it.francescoermini.zappa.model.registro.RegistroFactory;
//import it.francescoermini.zappa.model.type.LuogoType;
//import it.francescoermini.zappa.model.type.OggettoType;
//
//public class OperazioneDAOJpaTest extends JpaTest {
//	
//	private Operazione operazione;
//	
//	private Luogo luogo;
//	
//	private Oggetto oggetto;
//	
//	private Responsabile responsabile;
//	
//	private Registro registro;
//
//	private OperazioneDAO operazioneDAO;
//	
//	@Override
//	protected void init() throws InitializationError {
//			operazione = (Operazione) OperazioneFactory.createOperazione(OperazioneType.CONFERITURA);
//			operazione.setData(new Date());
//			operazione.setOra(new Date());
//			luogo = AziendaAgricolaFactory.luogo(LuogoType.CANTINA);
//			oggetto = AziendaAgricolaFactory.oggetto(AziendaAgricolaConfigController.associateLuogoObj().get(LuogoType.CANTINA));
//			responsabile = AziendaAgricolaFactory.responsabile();
//			operazione.setLuogo(luogo);
//			operazione.setOggetto(oggetto);
//			operazione.setResponsabile(responsabile);
//			
//			//registro =  RegistroFactory.registroDaOperazione(operazione);
//			
//			entityManager.persist(operazione);
//			//entityManager.persist(registro);
//			
//			operazioneDAO = new OperazioneDAOImpl();
//			
//			try {
//				FieldUtils.writeField(operazioneDAO, "em", entityManager, true);
//			} catch (IllegalAccessException e) {
//				throw new InitializationError(e);
//			}
//		
//	}
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testSaveOperazione() {
//		Operazione operazione2 = (Operazione) OperazioneFactory.createOperazione(OperazioneType.CONFERITURA);
//		operazione2.setData(new Date());
//		operazione2.setOra(new Date());
//		operazione2.setLuogo(luogo);
//		operazione2.setOggetto(oggetto);
//		operazione2.setResponsabile(responsabile);
//		
//		
//		
//		operazioneDAO.saveOperazione(operazione2);
//		//Registro registro2 =  RegistroFactory.registroDaOperazione(operazione2);
//
//		assertEquals(operazione2, entityManager
//				.createQuery("from Operazione where uuid = :uuid", Operazione.class)
//				.setParameter("uuid", operazione2.getUuid())
//				.getSingleResult());
//
//	
//	}
//
//	@Test
//	public void testGetOperazioneByID() {
//		operazione = (Operazione) OperazioneFactory.createOperazione(OperazioneType.CONFERITURA);
//
//		operazioneDAO.saveOperazione(operazione);
//
//		Operazione operazioneDB = operazioneDAO.getOperazioneByID(operazione.getId());
//		assertEquals(operazione.getId(), operazioneDB.getId() );
//		assertEquals( operazione.getUuid(), operazioneDB.getUuid() );
//		assertEquals(operazione.getLuogo(), operazioneDB.getLuogo());
//		assertEquals(operazione.getOggetto(), operazioneDB.getOggetto());
//		assertEquals(operazione.getRegistro(), operazioneDB.getRegistro());
//		assertEquals(operazione.getResponsabile(), operazioneDB.getResponsabile());
//	}
//
//	
//
//}
