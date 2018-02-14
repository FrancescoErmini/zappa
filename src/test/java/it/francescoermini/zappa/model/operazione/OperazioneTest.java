package it.francescoermini.zappa.model.operazione;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OperazioneTest {
	
	FakeOperazione o1, o2, o3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		String uuid1 = UUID.randomUUID().toString();
		String uuid2 = UUID.randomUUID().toString();
		
		o1 = new FakeOperazione(uuid1, OperazioneType.CONFERITURA);
		o2 = new FakeOperazione(uuid1,OperazioneType.CONFERITURA);
		o3 = new FakeOperazione(uuid1, OperazioneType.CONFERITURA);
		}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testNullUUID() {
		new FakeOperazione(null, null);
	}
	@Test
	public void testEquals() {
		assertEquals(o1, o1);
		assertEquals(o1, o3);
		assertEquals(o3, o3);
	}
	
	
	@Test
	public void testHashCode() {
		assertEquals(o1.hashCode(), o1.hashCode());
		assertEquals(o1.hashCode(), o3.hashCode());
		assertEquals(o3.hashCode(), o3.hashCode());
	}
	
	
//	@Test
//	public void testNotEquals() {
//		assertNotEquals(o1, o2);
//		assertNotEquals(o3, o2);
//		assertNotEquals(o1, null);
//		assertNotEquals(o1, "Ciao");
//	}

	
    class FakeOperazione extends Operazione{
		public FakeOperazione(String uuid, OperazioneType operazioneType ){
			super(uuid, operazioneType);
		}
	}

}
