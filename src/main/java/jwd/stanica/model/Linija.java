package jwd.stanica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Linija {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false)
	private Integer brojMesta;

	@Column
	private Integer cenaKarte;

	@Column
	private String vremePolaska;

	@Column(nullable = false)
	private String destinacija;

	@ManyToOne(fetch = FetchType.EAGER)
	private Prevoznik prevoznik;

	@OneToMany(mappedBy = "linija", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Rezervacija> rezervacije = new ArrayList<>();

	public Linija() {
		super();
	}

	public Linija(Integer brojMesta, Integer cenaKarte, String vremePolaska, String destinacija) {
		super();
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
	}

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

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if (!prevoznik.getLinije().contains(this)) {
			prevoznik.getLinije().add(this);
		}
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public void addRezervacija(Rezervacija rezervacija) {
		this.rezervacije.add(rezervacija);
		if (!this.equals(rezervacija.getLinija())) {
			rezervacija.setLinija(this);
		}
	}

}
