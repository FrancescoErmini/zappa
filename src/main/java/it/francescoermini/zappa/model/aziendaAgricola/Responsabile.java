package it.francescoermini.zappa.model.aziendaAgricola;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import it.francescoermini.zappa.model.operazione.*;


@Entity(name="Responsabile")
@Table(name = "RESPONSABILE")
public class Responsabile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )//, generator="responsabile_id_seq"
    private Long id;
	
	private String nome;
	
	
	@OneToMany(mappedBy="responsabile",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Operazione> operazione = new ArrayList<Operazione>() ;
	

	public Responsabile() {
		
	}
	public Responsabile(String nome) {
		this.nome=nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Operazione> getOperazione() {
		return operazione;
	}
	public void setOperazione(List<Operazione> operazione) {
		this.operazione = operazione;
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id=id;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + uuid.hashCode();
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (!(obj instanceof Operazione)) {
//			return false;
//		}
//		Operazione other = (Operazione) obj;
//		return uuid.equals(other.getUuid());
//	}
	@Override
	public boolean equals(Object obj) {
	    if(this.id == ((Responsabile) obj).id) {
	        return true;
	    }else {
	        return false;
	    }
	}
}

