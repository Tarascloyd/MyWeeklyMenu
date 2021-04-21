package com.taras.MyWeeklyMenu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=3, message="Name must be at least 5 characters long")
	private String name;
	
	@OneToMany(mappedBy = "menu")
	private List<DailyMenu> days = new ArrayList<>();
	private Date startDate;
	private Date createdAt;
	private int count;
	
	public Menu() {
		 
	}
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	
	public List<DailyMenu> getDays() {
		return days;
	}

	public void setDays(List<DailyMenu> days) {
		this.days = days;
		count = calculateCount();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		count = calculateCount();
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int calculateCount() {
		int theCount = 0;
		for (DailyMenu day : days) {
			count += day.getBreakfast().size();
			count += day.getLunch().size();
			count += day.getDinner().size();
		}
		return theCount;
	}
	
}
