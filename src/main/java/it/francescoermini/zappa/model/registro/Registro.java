package it.francescoermini.zappa.model.registro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.francescoermini.zappa.model.aziendaAgricola.Luogo;
import it.francescoermini.zappa.model.aziendaAgricola.Oggetto;
import it.francescoermini.zappa.model.operazione.Operazione;
import it.francescoermini.zappa.model.type.Vitigno;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "registro")
@Entity(name = "Registro")
@Table(name = "REGISTRO")
public abstract class Registro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )//, generator="registro_id_seq"
    private Long id;
	/*
	 * TODO: rimuovere oggetto e data ??
	 * O lascio solo operazione, oppure duplico anche responabile e luogo...
	 */

	private String nomeRegistro;

	 @Enumerated(EnumType.STRING)
	 private RegistroType registroType;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	

	@OneToOne(  fetch = FetchType.EAGER)// @JoinColumn(name="operazione_id")
	private Operazione operazione;
	

	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="luogo_id")
	private Luogo luogo;// = new Luogo();
	 
	
	boolean editable;

	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public Registro() {
		
	}

	public Registro(Operazione operazione) {
		this.operazione = operazione;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Operazione getOperazione() {
		return operazione;
	}
	public void setOperazione(Operazione operazione) {
		this.operazione = operazione;
	}
	public String getNomeRegistro() {
		return nomeRegistro;
	}
	public void setNomeRegistro(String nomeRegistro) {
		this.nomeRegistro = nomeRegistro;
	}
	public RegistroType getRegistroType() {
		return registroType;
	}
	public void setRegistroType(RegistroType registroType) {
		this.registroType = registroType;
	}
	public Luogo getLuogo() {
		return luogo;
	}
	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

}
