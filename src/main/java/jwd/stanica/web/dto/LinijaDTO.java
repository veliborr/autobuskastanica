package jwd.stanica.web.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class LinijaDTO {

	private Long id;
	
	@Min(0)
	private Integer brojMesta;

	private Integer cenaKarte;

	private String vremePolaska;
	
	@NotEmpty
	private String destinacija;

	private Long prevoznikId;

	private String prevoznikNaziv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Integer getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(Integer cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Long getPrevoznikId() {
		return prevoznikId;
	}

	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}

	public String getPrevoznikNaziv() {
		return prevoznikNaziv;
	}

	public void setPrevoznikNaziv(String prevoznikNaziv) {
		this.prevoznikNaziv = prevoznikNaziv;
	}
	
	

}
