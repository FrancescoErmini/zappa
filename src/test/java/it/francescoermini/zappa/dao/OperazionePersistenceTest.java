package it.francescoermini.zappa.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.francescoermini.zappa.dao.impl.OperazioneDAOImpl;
import it.francescoermini.zappa.model.aziendaAgricola.AziendaAgricolaFactory;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneFactory;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroFactory;
import it.francescoermini.zappa.model.type.*;
import it.francescoermini.zappa.controller.AziendaAgricolaConfigController;
import it.francescoermini.zappa.model.operazione.*;
public class OperazionePersistenceTest {
	
	static EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	private Operazione operazione;
	
	private OperazioneDAO operazioneDAO;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("zappaTest");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManagerFactory.close();
	}

	@Before
	public void setUp() throws Exception {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		operazione = (Operazione) OperazioneFactory.createOperazione(OperazioneType.CONFERITURA);
		
		operazione.setData(new Date());
		operazione.setOra(new Date());
		Luogo luogo = AziendaAgricolaFactory.luogo(LuogoType.CANTINA);
		Oggetto oggetto = AziendaAgricolaFactory.oggetto(AziendaAgricolaConfigController.associateLuogoObj().get(LuogoType.CANTINA));
		Responsabile responsabile = AziendaAgricolaFactory.responsabile();
		operazione.setLuogo(luogo);
		operazione.setOggetto(oggetto);
		operazione.setResponsabile(responsabile);	
	
		entityManager.persist(luogo);
		entityManager.persist(oggetto);
		entityManager.persist(responsabile);
		entityManager.persist(operazione);
		
		Registro registro =  RegistroFactory.registroDaOperazione(operazione);
//		entityManager.persist(registro); --> non inerente al test di operazione

		
		operazioneDAO = new OperazioneDAOImpl();
		try {
			FieldUtils.writeField(operazioneDAO, "em", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
		
		
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		entityManager.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		if ( entityManager.getTransaction().isActive() ) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
	}

	@Test
	public void test() {
		System.out.println("operazione test id:"+operazione.getId());
		Operazione operazioneDB = operazioneDAO.getOperazioneByID(operazione.getId());
		System.out.println("operazioneDB test id:"+operazioneDB.getId());
		assertEquals(operazione.getId(), operazioneDB.getId() );
		assertEquals( operazione.getUuid(), operazioneDB.getUuid() );
		assertEquals(operazione.getLuogo(), operazioneDB.getLuogo());
		assertEquals(operazione.getOggetto(), operazioneDB.getOggetto());
		assertEquals(operazione.getResponsabile(), operazioneDB.getResponsabile());
		assertEquals(operazione.getRegistro(), operazioneDB.getRegistro());

	}
	@Test
	public void testSaveUpdate() throws ParseException {
		Date modified_date = new SimpleDateFormat("dd-MM-yyyy").parse("07-07-2017");
		operazione.setData(modified_date);

		operazioneDAO.saveOperazione(operazione);

		assertEquals(operazione.getData(), entityManager
									.createQuery("from Operazione where uuid = :uuid", Operazione.class)
									.setParameter("uuid", operazione.getUuid())
									.getSingleResult()
									.getData());

	}
	

}
