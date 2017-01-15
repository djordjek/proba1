package jwd.wafepa.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.web.dto.ActivityDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/activities")
public class ApiActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ActivityDTO>> getActivities(
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="page", required=false) Integer page) {
		List<Activity> activities = null;
		int totalPages = 0;
		
		if (name == null) {
			Page<Activity> activitiesPage = activityService.findAll(page);
			activities = activitiesPage.getContent();
			totalPages = activitiesPage.getTotalPages();
		} else {
			activities = activityService.findByName(name);
		}
		
		List<ActivityDTO> activitiesDTO = new ArrayList<>();
		for (Activity activity : activities) {
			activitiesDTO.add(new ActivityDTO(activity));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("total-pages", "" + totalPages);

		return new ResponseEntity<>(activitiesDTO, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ActivityDTO> getActivity(@PathVariable Long id) {
		Activity activity = activityService.findOne(id);
		if (activity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new ActivityDTO(activity), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ActivityDTO> deleteActivity(@PathVariable Long id) {
		Activity activity = activityService.findOne(id);
		if (activity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		activityService.remove(id);

		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(new ActivityDTO(activity), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/JSON")
	public ResponseEntity<ActivityDTO> saveActivity(
			@RequestBody ActivityDTO activityDTO) {
		Activity activity = new Activity();
		activity.setName(activityDTO.getName());

		Activity activitySaved = activityService.save(activity);
		return new ResponseEntity<>(new ActivityDTO(activitySaved),
				HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/JSON")
	public ResponseEntity<ActivityDTO> editActivity(
			@RequestBody ActivityDTO activityDTO, @PathVariable Long id) {
		if (activityDTO.getId() == null || activityDTO.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Activity activity = activityService.findOne(id);
		if (activity != null) {
			activity.setName(activityDTO.getName());
			activity.setUpdated(new Date());

			Activity activitySaved = activityService.save(activity);
			return new ResponseEntity<>(new ActivityDTO(activitySaved), 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
