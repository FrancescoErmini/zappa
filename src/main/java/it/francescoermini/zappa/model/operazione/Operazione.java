package it.francescoermini.zappa.model.operazione;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import it.francescoermini.zappa.model.aziendaAgricola.*;
import it.francescoermini.zappa.model.registro.Registro;
import it.francescoermini.zappa.model.type.LuogoType;



@Entity(name="Operazione")
@Table(name="OPERAZIONE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Operazione")
public abstract class Operazione  {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//private  boolean editable;
	
	@Column(unique = true)
	protected String uuid;
	
	
	@Enumerated(EnumType.STRING)
	 private OperazioneType operazioneType;
		
	@Transient
	boolean editable;

	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	
	
//	@Enumerated(EnumType.STRING)
//	protected LuogoType luogoType;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="luogo_id")
	protected Luogo luogo;// = new Luogo();
	 
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="oggetto_id")
	protected Oggetto oggetto;
	


	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="responsabile_id")
	protected Responsabile responsabile ; //= new Responsabile() ;

	 
	
//	 @OneToOne(fetch = FetchType.LAZY)
//	 @JoinColumn(name="registro_id")
	 @OneToOne(mappedBy="operazione",  fetch = FetchType.LAZY)
	 protected Registro registro;

	 @Temporal(TemporalType.DATE)
	 @Column(name = "DATE_FIELD")
	 private Date data;
	     
	 @Temporal(TemporalType.TIME)
	 @Column(name = "TIME_FIELD")
	 private Date ora;
	 
//	 protected String nomeLuogo;
//
//		protected String nomeResponsabile;
//
//		protected int numeroOggetto;	
	 
	// boolean editable;
	 
//		public Responsabile getResponsabile() {
//			System.out.println("\n\n Get resp \n\n");
//			return responsabile;
//		}
//		public void setResponsabile(Responsabile responsabile) {
//			System.out.println("\n\n set reso \n\n");
//			this.responsabile = responsabile;
//		}

	public OperazioneType getOperazioneType() {
		return operazioneType;
	}
	public void setOperazioneType(OperazioneType operazioneType) {
		this.operazioneType = operazioneType;
	}
	public Operazione(){
		
	}
	public Operazione(String uuid, OperazioneType operazioneType) {
		if(uuid == null) {
			throw new IllegalArgumentException("uuid cannot be null");
		}
		this.uuid = uuid;
		this.operazioneType = operazioneType;
		//this.luogoType=operazioneType.getLuogoType(); 
		
	}
//	public String getNomeLuogo() {
//		System.out.println("\n\n get luogo  \n\n");
//		return nomeLuogo;
//	}
//	public void setNomeLuogo(String nomeLuogo) {
//		System.out.println("\n\n  set lugoo \n\n");
//		this.nomeLuogo = nomeLuogo;
//	}
//	public String getNomeResponsabile() {
//		return nomeResponsabile;
//	}
//	public void setNomeResponsabile(String nomeResponsabile) {
//		this.nomeResponsabile = nomeResponsabile;
//	}
//
//	public int getNumeroOggetto() {
//		return numeroOggetto;
//	}
//	public void setNumeroOggetto(int numeroOggetto) {
//		this.numeroOggetto = numeroOggetto;
//	}

//
//	public LuogoType getLuogoType(){
//		return luogoType;
//	}
//	public void setLuogoType(LuogoType luogoType) {
//		this.luogoType = luogoType;
//	}
	
	
	public Luogo getLuogo() {
		return luogo;
	}
	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}
	public Oggetto getOggetto() {
		return oggetto;
	}
	public void setOggetto(Oggetto oggetto) {
		this.oggetto = oggetto;
	}

	

	public Date getData() {
		System.out.println("\n\n  get data  \n\n");
		return data;
	}
	public void setData(Date data) {
		System.out.println("\n\n set data  \n\n");
		this.data = data;
	}
	public java.util.Date getOra() {
		System.out.println("\n\n  get ora \n\n");
		return ora;
	}
	public void setOra(java.util.Date ora) {
		System.out.println("\n\n set ora  \n\n");
		this.ora = ora;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uuid.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Operazione)) {
			return false;
		}
		Operazione other = (Operazione) obj;
		return uuid.equals(other.getUuid());
	}
//	@Override public boolean equals(Object o) {
//	    if (!(o instanceof Operazione))
//	        return false;
//	    Operazione t = (Operazione) o;
//	    return this.x == t.x && this.y == t.y;
//	}
	public Object getUuid() {
		// TODO Auto-generated method stub
		return this.uuid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Registro getRegistro() {
		return registro;
	}
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
//	public boolean isEditable() {
//		return editable;
//	}
//	public void setEditable(boolean editable) {
//		this.editable = editable;
//	}
//	public boolean isEditable() {
//		return editable;
//	}
//	public void setEditable(boolean editable) {
//		this.editable = editable;
//	}
	public Responsabile getResponsabile() {
		return responsabile;
	}
	public void setResponsabile(Responsabile responsabile) {
		this.responsabile = responsabile;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	
}