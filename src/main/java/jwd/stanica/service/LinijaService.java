package jwd.stanica.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.stanica.model.Rezervacija;
import jwd.stanica.model.Linija;

public interface LinijaService {

	Linija findOne(Long id);

	Page<Linija> findAll(int pageNum);

	Page<Linija> search(

			@Param("destinacija") String destinacija,

			@Param("prevoznikId") Long prevoznikId,

			@Param("maxCena") Integer maxCena, int pageNum);

	Linija save(Linija linija);

	Linija delete(Long id);

	Rezervacija rezervisi(Long id);

}
