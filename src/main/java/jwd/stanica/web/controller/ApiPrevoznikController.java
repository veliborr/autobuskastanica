package jwd.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.PrevoznikService;
import jwd.stanica.support.PrevoznikDTOToPrevoznik;
import jwd.stanica.support.PrevoznikToPrevoznikDTO;
import jwd.stanica.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value = "/api/prevoznici")
public class ApiPrevoznikController {
	@Autowired
	private PrevoznikService prevoznikService;

	@Autowired
	private PrevoznikToPrevoznikDTO toDTO;

	@Autowired
	private PrevoznikDTOToPrevoznik toPrevoznik;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PrevoznikDTO>> getPrevoznici() {
		List<Prevoznik> prevoznici = prevoznikService.findAll();

		if (prevoznici == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(prevoznici), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PrevoznikDTO> add(@Validated @RequestBody PrevoznikDTO newPrevoznikDTO) {

		Prevoznik savedPrevoznik = prevoznikService.save(toPrevoznik.convert(newPrevoznikDTO));

		return new ResponseEntity<>(toDTO.convert(savedPrevoznik), HttpStatus.CREATED);
	}

}
