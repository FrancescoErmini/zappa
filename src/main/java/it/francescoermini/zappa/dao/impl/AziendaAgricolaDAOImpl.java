package it.francescoermini.zappa.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.francescoermini.zappa.dao.AziendaAgricolaDAO;

import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.aziendaAgricola.Responsabile;


@Stateless
public class AziendaAgricolaDAOImpl implements AziendaAgricolaDAO {

	
	@PersistenceContext(name = "azienda")
	private EntityManager em;
	
	
	@Override
	public void saveLuogo(Luogo luogo) {
		
		em.persist(luogo);
	}
	

	@Override
	public void saveResponsabile(Responsabile responsabile) {
		if( responsabile.getId() != null){
		em.persist(responsabile);
		}
		else {
			em.merge(responsabile);
		}
		
		
		
	}
	// https://dzone.com/articles/saving_detatched_entities
	
//	public Responsabile findResponsabileByID(Long id){
//		return em.find(Responsabile.class, id);
//		
//	}
	
	@Override
	public List<Luogo> getAllLuoghi() {	
		javax.persistence.Query query = em.createQuery("SELECT e FROM Luogo e");
	    return (List<Luogo>) query.getResultList();
	}
	
	
	@Override
	public List<Responsabile> getAllResponsabili() {
		javax.persistence.Query query = em.createQuery("SELECT e FROM Responsabile e");
	    return (List<Responsabile>) query.getResultList();
	}


	@Override
	public Responsabile getResponsabileByID(long id) {
		return em.find(Responsabile.class, id);
	}


	@Override
	public Luogo getLuogoByID( long luogoID) {
		return em.find(Luogo.class, luogoID);
	}

	
	@Override
	public Oggetto getOggettoByID(long oggettoID) {
		return em.find(Oggetto.class, oggettoID);
		
	}

	
}
