package it.francescoermini.zappa.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.junit.runners.model.InitializationError;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JpaTest {
	
	private static EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

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
		entityManager.createNativeQuery("TRUNCATE SCHEMA public AND COMMIT").executeUpdate();
		entityManager.getTransaction().commit();
		
		entityManager.getTransaction().begin();
		init();
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

	
	
	protected abstract void init() throws InitializationError;


}
