package it.francescoermini.zappa.model.aziendaAgricola;




import javax.persistence.*;

import it.francescoermini.zappa.model.type.*;


@Entity(name = "Vigna")
@Table(name = "vigna")
@DiscriminatorValue("vigna")
public class Vigna extends Luogo{
	
	private String cantina;
	
	@Enumerated(EnumType.STRING)
	private EsposizioneVigna esposizione;
	
	private int altitudine;
	
	private int superficie;
	@Lob
	private String descrizioneVigna;
	
	public Vigna() {
		
	}
	public Vigna(LuogoType luogoType){
		super(luogoType);
	}
	

	
	public EsposizioneVigna getEsposizione() {
		return esposizione;
	}
	public void setEsposizione(EsposizioneVigna esposizione) {
		this.esposizione = esposizione;
	}
	public int getAltitudine() {
		return altitudine;
	}
	public void setAltitudine(int altitudine) {
		this.altitudine = altitudine;
	}
	public String getDescrizioneVigna() {
		return descrizioneVigna;
	}
	public void setDescrizioneVigna(String descrizioneVigna) {
		this.descrizioneVigna = descrizioneVigna;
	}

	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	public String getCantina() {
		return cantina;
	}
	public void setCantina(String cantina) {
		this.cantina = cantina;
	}
	


    
    
    
    
}