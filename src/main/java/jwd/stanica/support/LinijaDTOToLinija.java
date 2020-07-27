package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Linija;
import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.LinijaService;
import jwd.stanica.service.PrevoznikService;
import jwd.stanica.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija implements Converter<LinijaDTO, Linija> {

	@Autowired
	private LinijaService linijaService;

	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Linija convert(LinijaDTO dto) {

		Prevoznik prevoznik = prevoznikService.findOne(dto.getPrevoznikId());

		if (prevoznik != null) {

			Linija linija = null;

			if (dto.getId() != null) {
				linija = linijaService.findOne(dto.getId());
			} else {
				linija = new Linija();
			}

			linija.setId(dto.getId());
			linija.setBrojMesta(dto.getBrojMesta());
			linija.setCenaKarte(dto.getCenaKarte());
			linija.setDestinacija(dto.getDestinacija());
			linija.setVremePolaska(dto.getVremePolaska());
			linija.setPrevoznik(prevoznik);

			return linija;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Linija> convert(List<LinijaDTO> linijaDTOs) {
		List<Linija> ret = new ArrayList<>();

		for (LinijaDTO linijaDTO : linijaDTOs) {
			ret.add(convert(linijaDTO));
		}

		return ret;
	}
}
