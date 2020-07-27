package jwd.stanica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Linija;
import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.LinijaService;
import jwd.stanica.service.PrevoznikService;

@Component
public class TestData {

	@Autowired
	private PrevoznikService prevoznikService;

	@Autowired
	private LinijaService linijaService;

	@PostConstruct
	public void init() {

		Prevoznik prevoznik1 = prevoznikService.save(new Prevoznik("Stup Vršac", "Bulevar Miloša Obilića BB", "459688"));
		Prevoznik prevoznik2 = prevoznikService.save(new Prevoznik("Niš Ekspres", "Nikole Tesle BB", "456996"));

		Linija linija1 = new Linija(50, 550, "12:00", "Beograd");
		linija1.setPrevoznik(prevoznik1);
		linijaService.save(linija1);
		
		Linija linija2 = new Linija(60, 700, "12:45", "Zrenjanin");
		linija2.setPrevoznik(prevoznik1);
		linijaService.save(linija2);
		
		Linija linija3 = new Linija(65, 500, "19:00", "Novi Sad");
		linija3.setPrevoznik(prevoznik1);
		linijaService.save(linija3);
		
		Linija linija4 = new Linija(45, 450, "07:20", "Pirot");
		linija4.setPrevoznik(prevoznik2);
		linijaService.save(linija4);
		
		Linija linija5 = new Linija(70, 500, "12:45", "Valjevo");
		linija5.setPrevoznik(prevoznik2);
		linijaService.save(linija5);
		
		Linija linija6 = new Linija(50, 300, "16:45", "PAncevo");
		linija6.setPrevoznik(prevoznik2);
		linijaService.save(linija6);

	}
}
