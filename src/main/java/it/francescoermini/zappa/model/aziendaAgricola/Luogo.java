package it.francescoermini.zappa.model.aziendaAgricola;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.francescoermini.zappa.model.type.LuogoType;




@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "luogo")// discriminatorType = DiscriminatorType.STRING)
//@MappedSuperclass
@Entity(name="Luogo")
@Table(name="LUOGO")
public abstract class Luogo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//, generator="luogo_id_seq" 
    protected Long id;

	@Column(name="nome")
	protected String nome;
	
	@Column(name="tipo")
	protected LuogoType luogoType;
	
	@OneToMany(mappedBy="luogo",cascade = CascadeType.PERSIST, orphanRemoval = true, fetch=FetchType.EAGER)
	 protected List<Oggetto> oggetti = new ArrayList<Oggetto>();
	
	
	
	
public Luogo(){
		
	}

public Luogo(LuogoType luogoType){
	this.luogoType = luogoType;
}
	
	
	
	
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public List<Oggetto> getOggetti() {
		return oggetti;
	}

	public void setOggetti(List<Oggetto> oggetti) {
		this.oggetti = oggetti;
	}
	
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LuogoType getLuogoType() {
		return luogoType;
	}

	public void setLuogoType(LuogoType luogoType) {
		this.luogoType = luogoType;
	}
	@Override
	public boolean equals(Object obj) {
	    if(this.id == ((Luogo) obj).id) {
	        return true;
	    }else {
	        return false;
	    }
	}


	
}

//	//@Column(name="luogo")
//	public abstract void setLuogo(String luogo);
//	
////	
////	 @Column(name="luogo")
////	 private String luogo;
////	 
////	 
////	 protected List<Oggetto> oggetti;
//	 
//	public abstract void setName(String name);
//
//	public abstract List<Oggetto> getOggetti();
//
//	public abstract void setOggetti(List<Oggetto> oggetti);
//
//	
//	
//	
//}
