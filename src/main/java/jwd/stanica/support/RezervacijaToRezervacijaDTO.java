package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Rezervacija;
import jwd.stanica.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDTO implements Converter<Rezervacija, RezervacijaDTO> {

	@Override
	public RezervacijaDTO convert(Rezervacija rezervacija) {

		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(rezervacija.getId());
		dto.setLinijaId(rezervacija.getLinija().getId());

		return dto;
	}

	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije) {
		List<RezervacijaDTO> ret = new ArrayList<>();

		for (Rezervacija rezervacija : rezervacije) {
			ret.add(convert(rezervacija));
		}

		return ret;
	}

}
