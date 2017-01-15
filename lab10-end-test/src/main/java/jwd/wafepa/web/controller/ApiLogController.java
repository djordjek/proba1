package jwd.wafepa.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Log;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.LogService;
import jwd.wafepa.web.dto.ActivityDTO;
import jwd.wafepa.web.dto.LogDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/logs")
public class ApiLogController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LogDTO>> getLogs(
			@RequestParam(value="logDate", required=false) String logDate,
			@RequestParam(value="from", required=false) String from,
			@RequestParam(value="to", required=false) String to
			
			) {
		
		List<Log> logs = null;
		
		
		Date date = null;
		if(logDate != null){
			SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = format.parse(logDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		if (date != null && from != null && to != null) {
			
			logs = logService.findLogsByDateDuration(date, Integer.parseInt(from), Integer.parseInt(to));

		} else {

			logs = logService.findAll();
		}
		
		List<LogDTO> logsDTO = new ArrayList<>();
		for (Log log : logs) {
			logsDTO.add(new LogDTO(log));
		}

		return new ResponseEntity<>(logsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LogDTO> getLog(@PathVariable Long id) {
		Log log = logService.findOne(id);
		if (log == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new LogDTO(log), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/JSON")
	public ResponseEntity<LogDTO> saveLog(
			@RequestBody LogDTO logDTO) {
		Log log = new Log();
		log.setDate(logDTO.getDate());
		log.setDuration(logDTO.getDuration());
		Activity activity 
		= activityService.findOne(logDTO.getActivity().getId());
		log.setActivity(activity);

		Log logSaved = logService.save(log);
		return new ResponseEntity<>(new LogDTO(logSaved),
				HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LogDTO> deleteLog(@PathVariable Long id) {
		Log log = logService.findOne(id);
		if (log == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		logService.remove(id);

		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 return new ResponseEntity<>(new LogDTO(log),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/JSON")
	public ResponseEntity<LogDTO> editLog(
			@RequestBody LogDTO logDTO, @PathVariable Long id) {
		if (logDTO.getId() == null || logDTO.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (logService.findOne(id) != null) {
			Log log = new Log();
			log.setId(logDTO.getId());
			log.setDate(logDTO.getDate());
			log.setDuration(logDTO.getDuration());
			Activity activity 
			= activityService.findOne(logDTO.getActivity().getId());
			log.setActivity(activity);

			Log logSaved = logService.save(log);
			return new ResponseEntity<>(new LogDTO(logSaved),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
