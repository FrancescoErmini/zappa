package it.francescoermini.zappa.model.aziendaAgricola;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.type.OggettoType;


@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "oggetto")// , discriminatorType = DiscriminatorType.STRING)
//@MappedSuperclass
@Entity(name="Oggetto")
@Table(name="OGGETTO")
public abstract class Oggetto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//, generator="oggetto_id_seq" 
    protected Long id;

	@Enumerated(EnumType.STRING)
	protected OggettoType oggettoType;
	
	private String tipo;
	
	@Column(name="numero")
	protected int numero;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name="luogo_id")
	  protected Luogo luogo;
	/*
	 * TODO: rimuovere??
	 */
//	  
//	  @OneToOne( fetch = FetchType.EAGER)
//	  @JoinColumn(name="registro_id")
//	  private Registro registro;
//	 
	 
	 public Oggetto(){
			
	}
	 
	 public Oggetto(OggettoType oggettoType){
			this.oggettoType = oggettoType;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	

	public OggettoType getOggettoType() {
		return oggettoType;
	}

	public void setOggettoType(OggettoType oggettoType) {
		this.oggettoType = oggettoType;
	}
	@Override
	public boolean equals(Object obj) {
	    if(this.id == ((Oggetto) obj).id) {
	        return true;
	    }else {
	        return false;
	    }
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}	
//	private int numero;
//	
//	 @Column(name="oggetto")
//	  private String oggetto;
//
//	public abstract void setLuogo(Luogo luogo);
//
//	public abstract void setNumero(int i);
//
//
//	 
//
//	
//	
//}
