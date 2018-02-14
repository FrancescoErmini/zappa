package it.francescoermini.zappa.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.francescoermini.zappa.dao.OperazioneDAO;
import it.francescoermini.zappa.model.operazione.OperazioneType;

import it.francescoermini.zappa.model.operazione.Operazione;




@Stateless
public class OperazioneDAOImpl implements OperazioneDAO {

	
	@PersistenceContext(name = "zappa")
	private EntityManager em;
 
	@Override
	public void saveOperazione(Operazione operazione) {
		if(operazione.getId() != null ){
			em.merge(operazione);
		}
		else{
		 em.persist(operazione);
		}
	}


	@Override
	public List<Operazione> getAllOperazioni() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operazione> cq = cb.createQuery(Operazione.class);
        Root<Operazione> rootEntry = cq.from(Operazione.class);
        CriteriaQuery<Operazione> all = cq.select(rootEntry);
        javax.persistence.TypedQuery<Operazione> allQuery = em.createQuery(all);
        return allQuery.getResultList();	
	}
	

	@Override
	public Operazione getOperazioneByID(long id) {
		
		return em.find(Operazione.class, id);
		
	}
	
	@Override
	public Operazione getOperazionePerRegistroCantina(long luogoID, long oggettoID, OperazioneType operazioneType) {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em.createQuery("SELECT e FROM Operazione e WHERE e.luogo_id = :luogoID AND e.oggetto_id = :oggettoID AND e.operazioneType = :operazioneType");
		query.setParameter("oggettoID", oggettoID);
		query.setParameter("luogoID", luogoID);
		query.setParameter("operazioneType", operazioneType.toString());
		return (Operazione) query.getSingleResult();
	}


//	@Override
//	public List<Operazione> getOperazionePerLuogo(String nomeLuogo, String type) {
//		OperazioneType op = OperazioneType.valueOf(type);
////		OperazioneType[] op = OperazioneType.values();
////		for(int i=0; i<op.length; i++){
////			if( op[i].getLabel() == type ){}
////		}
//		javax.persistence.Query query = em.createQuery("SELECT e FROM Operazione e WHERE e.luogo.nome = :nomeLuogo AND e.operazioneType = :op");
//		query.setParameter("nomeLuogo", nomeLuogo);
//		query.setParameter("op", op);
//		return (List<Operazione>) query.getResultList();
//		
//	}
	
	@Override
	public List<Operazione> getOperazionePerLuogoOfType(long luogoID, OperazioneType op) {

		javax.persistence.Query query = em.createQuery("SELECT e FROM Operazione e WHERE e.luogo.id = :luogoID AND e.operazioneType = :op");
		query.setParameter("luogoID", luogoID);
		query.setParameter("op", op);
		return (List<Operazione>) query.getResultList();
		
	}

}
