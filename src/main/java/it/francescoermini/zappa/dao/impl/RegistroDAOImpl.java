package it.francescoermini.zappa.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.francescoermini.zappa.dao.RegistroDAO;
import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.operazione.OperazioneType;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.registro.RegistroType;
import it.francescoermini.zappa.model.type.LuogoType;


@Stateless
public class RegistroDAOImpl implements RegistroDAO{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void saveRegistro(Registro registro) {
		
//		Registro exisitngRegistro = findRegistroByID(registro.getId());
//		if(exisitngRegistro == null ) {
//			em.persist(registro);
//		}
//		else {
//			///exisitngRegistro.se
//		}
		
		if( registro.getId() != null ){
		em.merge(registro);
		}
		else{
			em.persist(registro);
		}
		
	}
//	public Registro findRegistroByID(Long id){
//		  
//     return em.find(Registro.class, id);
//		    
//	}
/*
Order existingOrder = dao.findById(receivedOrder.getId());if(existingOrder == null) {dao.persist(receivedOrder);} else {existingOrder.setCustomerName(receivedOrder.getCustomerName());existingOrder.setDate(receivedOrder.getDate());}

 */
	@Override
	public List<Registro> getAllRegistri() {
		
		    javax.persistence.Query query = em.createQuery("SELECT e FROM Registro e");
		    return (List<Registro>) query.getResultList();
	}

	@Override
	public List<Registro> getRegistriPerNomeLuogo(String nomeLuogo) {
		
		
		javax.persistence.Query query = em.createQuery("SELECT e FROM Registro e WHERE e.nomeRegistro = :nomeLuogo");
		query.setParameter("nomeLuogo", nomeLuogo);
		return (List<Registro>) query.getResultList();
		
		
	}

	@Override
	public List<Registro> getRegistriOfTypePerLuogo(RegistroType registroType, Long luogoID) {

		javax.persistence.Query query = em.createQuery("SELECT e FROM Registro e WHERE e.registroType = :registroType AND e.luogo.id = :luogoID");
		query.setParameter("registroType", registroType);
		query.setParameter("luogoID", luogoID);
		return (List<Registro>) query.getResultList();
	}
	
	

}
