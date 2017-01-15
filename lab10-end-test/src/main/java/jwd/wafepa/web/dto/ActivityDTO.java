package jwd.wafepa.web.dto;

import java.util.Date;

import jwd.wafepa.model.Activity;

public class ActivityDTO {
	private Long id;
	private String name;
	private Date updated;
	
	public ActivityDTO() {
		super();
	}

	public ActivityDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ActivityDTO(Activity activity){
		super();
		this.id = activity.getId();
		this.name = activity.getName();
		this.updated = activity.getUpdated();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
