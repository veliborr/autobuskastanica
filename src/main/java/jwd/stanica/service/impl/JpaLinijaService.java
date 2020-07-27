package jwd.stanica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.stanica.model.Linija;
import jwd.stanica.model.Rezervacija;
import jwd.stanica.repository.LinijaRepository;
import jwd.stanica.repository.RezervacijaRepository;
import jwd.stanica.service.LinijaService;

@Service
public class JpaLinijaService implements LinijaService {

	@Autowired
	private LinijaRepository linijaRepository;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Override
	public Linija findOne(Long id) {
		return linijaRepository.findOne(id);
	}

	@Override
	public Page<Linija> findAll(int pageNum) {
		return linijaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Linija> search(String destinacija, Long prevoznikId, Integer maxCena, int pageNum) {

		if (destinacija != null) {
			destinacija = '%' + destinacija + '%';
		}

		return linijaRepository.search(destinacija, prevoznikId, maxCena, new PageRequest(pageNum, 5));
	}

	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Linija linija = linijaRepository.findOne(id);
		if (linija == null) {
			throw new IllegalArgumentException("Tried to delete" + "non-existant linija");
		}
		linijaRepository.delete(linija);
		return linija;
	}

	@Override
	public Rezervacija rezervisi(Long id) {
		Linija l = findOne(id);
		
		if (l != null && l.getBrojMesta() > 0) {
			Rezervacija novaRezervacija = new Rezervacija();
			novaRezervacija.setLinija(l);
			rezervacijaRepository.save(novaRezervacija);
			
			l.addRezervacija(novaRezervacija);
			l.setBrojMesta(l.getBrojMesta()-1);
			linijaRepository.save(l);
			

			return novaRezervacija;
		} else {
			throw new IllegalArgumentException("Tried to reserve a ticket for non-existant line");
		}
	}

	
}
